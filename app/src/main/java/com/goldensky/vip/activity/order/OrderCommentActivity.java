package com.goldensky.vip.activity.order;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.goldensky.vip.R;
import com.goldensky.vip.adapter.CommentImageAdapter;
import com.goldensky.vip.adapter.CommentStartAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityOrderCommentBinding;
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

public class OrderCommentActivity extends BaseActivity<ActivityOrderCommentBinding, OrderCommentViewModel> implements View.OnClickListener {

    private CommentStartAdapter mCommentStartAdapter;
    private CommentImageAdapter mCommentImageAdapter;
    private int currentStartIndex = -1;
    private String imgUrl = ""; //图片url
    private String goodsId = "";//商品id

    public List<LocalMedia> selectList = new ArrayList<>();
    public List<String> selectUrlsList = new ArrayList<>();
    private PopupWindow mPopupWindow;

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarOrder.setBackListener( v -> { finish(); });
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
                            selectList.remove(position);
                            mCommentImageAdapter.notifyItemChanged(position);
                            mCommentImageAdapter.notifyItemRangeChanged(position, selectList.size());
                        }
                    }
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                images = PictureSelector.obtainMultipleResult(data);
                selectList.addAll(images);
                mCommentImageAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions, @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_comment;
    }
}