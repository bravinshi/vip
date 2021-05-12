package com.goldensky.vip.activity.mine.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

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
import com.bumptech.glide.request.RequestOptions;
import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.activity.order.OrderCommentActivity;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.base.error.FailCallback;
import com.goldensky.vip.bean.UpdateVipUserReqBean;
import com.goldensky.vip.databinding.ActivityPersonalDetailsBinding;
import com.goldensky.vip.enumerate.DefaultUrlEnum;
import com.goldensky.vip.event.VipUserChangeEvent;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.helper.GlideEngine;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.account.AccountViewModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class PersonalDetailsActivity extends BaseActivity<ActivityPersonalDetailsBinding, AccountViewModel> implements View.OnClickListener {
    private PopupWindow mPopupWindow;
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mBinding.topBarPersonDetail.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.setListener(this);
        setMSG();
    }

    private void setMSG() {
        if(AccountHelper.getUserNick()!=null){
            mBinding.tvNickPersonalDetail.setText(AccountHelper.getUserNick());
        }else {
            mBinding.tvNickPersonalDetail.setText(AccountHelper.getUserMobile());
        }
        if(AccountHelper.getUserPic()!=null&&!AccountHelper.getUserPic().equals("")){
            Glide.with(this).load(AccountHelper.getUserPic()).apply(new RequestOptions().circleCrop()).into(mBinding.ivHeadPersonalDetail);
        }else {
            Glide.with(this).load(DefaultUrlEnum.DEFAULTHEADPIC.value).apply(new RequestOptions().circleCrop()).into(mBinding.ivHeadPersonalDetail);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onVipUserChanged(VipUserChangeEvent event){
        if(event.getSuccess()){
            setMSG();
        }
    }
    @Override
    public void observe() {
        mViewModel.uploadPicLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                UpdateVipUserReqBean bean = new UpdateVipUserReqBean();
                bean.setUserId(AccountHelper.getUserId());
                bean.setUserpic(s);
                mViewModel.updateVipUser(bean);
            }
        });
        mViewModel.uploadPicLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                AccountHelper.setUserPic(s);
                toast("");
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_details;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cl_head_person_detail:
                showPop();
                break;
            case R.id.cl_nick_person_detail:
                Starter.startChangeNickActivity(this,null);
                break;
            case R.id.tv_album:
                //相册
                PictureSelector.create(this)
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
                PictureSelector.create(this)
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<LocalMedia> images;
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                images = PictureSelector.obtainMultipleResult(data);
                mViewModel.uploadPic(images.get(0).getPath(), new FailCallback() {
                    @Override
                    public void onFail(NetResponse netResponse) {
                        toast(netResponse.getMessage());
                    }
                });
            }
        }
    }
}