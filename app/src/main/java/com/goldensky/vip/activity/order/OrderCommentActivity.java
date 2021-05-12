package com.goldensky.vip.activity.order;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.TextureView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.R;
import com.goldensky.vip.adapter.CommentImageAdapter;
import com.goldensky.vip.adapter.CommentStartAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.base.error.FailCallback;
import com.goldensky.vip.databinding.ActivityOrderCommentBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.helper.GlideEngine;
import com.goldensky.vip.viewmodel.order.OrderCommentViewModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.goldensky.vip.viewmodel.order.OrderCommentViewModel.RESULT_CODE_KEY;
import static com.goldensky.vip.viewmodel.order.OrderCommentViewModel.RESULT_MSG_KEY;

public class OrderCommentActivity extends BaseActivity<ActivityOrderCommentBinding, OrderCommentViewModel> implements View.OnClickListener {

    private CommentStartAdapter mCommentStartAdapter;
    private CommentImageAdapter mCommentImageAdapter;
    private int currentStartIndex = -1;
    private String imgUrl = "https://file.jtmsh.com/data/jintianhezong/file/img/2021-01-21/KI1611208227205.png"; //图片url
    private String secondorderid = "38fd3f7a91504394a8a9326767180772";//secondorderid


    public List<LocalMedia> selectList = new ArrayList<>();
    public List<String> selectUrlsList = new ArrayList<>();
    private PopupWindow mPopupWindow;
    private LocalMedia lastMedia;

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarOrder.setBackListener( v -> { finish(); });
        mBinding.etComment.setFilters(new InputFilter[]{new EmojiExcludeFilter()});
        mBinding.tvCommit.setOnClickListener(this);
        Glide.with(this).load(imgUrl).into(mBinding.ivProduct);
    }

    @Override
    public void observe() {
        mViewModel.initImages();
        mCommentStartAdapter = new CommentStartAdapter(mViewModel.stars);
        mCommentStartAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter,  View view, int position) {
                if (position == currentStartIndex) return;
                currentStartIndex = position;
                mViewModel.updateStars(position);
                mCommentStartAdapter.notifyDataSetChanged();
            }
        });
        FlexboxLayoutManager lineLayoutManager = new FlexboxLayoutManager(this){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        lineLayoutManager.setFlexDirection(FlexDirection.ROW);
        mBinding.rvStar.setLayoutManager(lineLayoutManager);
        mBinding.rvStar.setAdapter(mCommentStartAdapter);


        mCommentImageAdapter = new CommentImageAdapter(selectList);
        mCommentImageAdapter.addChildClickViewIds(new int[]{R.id.iv_pic, R.id.iv_delete});
        mCommentImageAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (mCommentImageAdapter.getItemViewType(position) == CommentImageAdapter.TYPE_ADD) {
                    onAddPicClick();
                } else {
                    if (view.getId() == R.id.iv_pic) {
                        PictureSelector.create(OrderCommentActivity.this).externalPicturePreview(position, selectList, 0);
                    } else if (view.getId() == R.id.iv_delete) {
                        if (selectList.size() > position) {
                            selectUrlsList.remove(position);
                            selectList.remove(position);
                            mCommentImageAdapter.notifyItemChanged(position);
                            mCommentImageAdapter.notifyItemRangeChanged(position, selectList.size());
                        }
                    }
                }

                hideKeyBoard();
            }
        });
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        mBinding.rvPics.setLayoutManager(flexboxLayoutManager);
        mBinding.rvPics.setAdapter(mCommentImageAdapter);

        mViewModel.uploadPicLiveData.observe(this, url -> {
            Log.d("uploadPicLiveData", "observe: " + url);
            selectUrlsList.add(url);
            if (lastMedia != null) {
                selectList.add(lastMedia);
                mCommentImageAdapter.notifyDataSetChanged();
                lastMedia = null;
            }
        });

        mViewModel.commitResult.observe(this, map -> {
            if (map.get(RESULT_CODE_KEY) == "1") {
                //成功
            } else {
                Toast.makeText(this, map.get(RESULT_MSG_KEY), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void onAddPicClick() {
        showPop();
    }

    private void showPop() {
        View bottomView = View.inflate(this, R.layout.layout_bottom_dialog, null);
        TextView mAlbum = bottomView.findViewById(R.id.tv_album);
        TextView mCamera = bottomView.findViewById(R.id.tv_camera);
        TextView mCancel = bottomView.findViewById(R.id.tv_cancel);

        mPopupWindow = new PopupWindow(bottomView, -1, -2);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = 0.5f;
        getWindow().setAttributes(lp);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
        mPopupWindow.setAnimationStyle(R.style.main_menu_photo_anim);
        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);

        mAlbum.setOnClickListener(this);
        mCamera.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    private void closePopupWindow() {
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                commitComment();
                break;
            case R.id.tv_album:
                //相册
                PictureSelector.create(OrderCommentActivity.this)
                        .openGallery(PictureMimeType.ofImage())
                        .imageEngine(GlideEngine.createGlideEngine())
                        .maxSelectNum(1)
                        .imageSpanCount(4)
                        .isCompress(true)
                        .selectionMode(PictureConfig.SINGLE)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                closePopupWindow();
                break;
            case R.id.tv_camera:
                //拍照
                PictureSelector.create(OrderCommentActivity.this)
                        .openCamera(PictureMimeType.ofImage())
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                closePopupWindow();
                break;
            case R.id.tv_cancel:
                //取消
                closePopupWindow();
                break;
        }
    }


    private void commitComment() {
        if (currentStartIndex == -1) {
            Toast.makeText(this, "请对商品星级进行评分", Toast.LENGTH_SHORT).show();
            return;
        }

        String content = mBinding.etComment.getText().toString();
        if(TextUtils.isEmpty(content)) {
            Toast.makeText(this, "请输入评价内容", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < selectUrlsList.size(); i++) {
            if (i == 0) {
                stringBuilder.append(selectUrlsList.get(i));
            } else {
                stringBuilder.append(",").append(selectUrlsList.get(i));
            }
        }

        String urls = stringBuilder.toString();

        mViewModel.orderComment(currentStartIndex + 1 + "", content, urls, "365fc2e065164700a5b1b985e326a766", secondorderid);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                images = PictureSelector.obtainMultipleResult(data);
                if (images.size() > 0) {
                    LocalMedia media = images.get(0);
                    lastMedia = media;
                    String path;
                    if (media.isCompressed()) {
                        //压缩
                        path = media.getCompressPath();
                    } else {
                        //原图
                        path = media.getPath();
                    }
                    uploadImage(path);
                }
            }
        }
    }

    private void uploadImage(String path) {
        mViewModel.uploadPic(path, new FailCallback() {
            @Override
            public void onFail(NetResponse netResponse) {
                lastMedia = null;
                Toast.makeText(OrderCommentActivity.this, "图片上传失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_comment;
    }


    private void hideKeyBoard() {
        InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        if (manager != null && manager.isActive(mBinding.etComment)) {
            mBinding.etComment.clearFocus();
            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    public static class EmojiExcludeFilter implements InputFilter {
        private static final String TAG = "EmojiFilter";
        private Pattern mEmojiPattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
//       private Pattern mEmojiPattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]|[\ud83e\udd00-\ud83e\uddff]|[\u2300-\u23ff]|[\u2500-\u25ff]|[\u2100-\u21ff]|[\u0000-\u00ff]|[\u2b00-\u2bff]|[\u2d06]|[\u3030]");
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            Matcher emojiMatcher = mEmojiPattern.matcher (source) ;
            if (emojiMatcher.find( )) {
                return "";
            }
            return source;
        }

    }
}