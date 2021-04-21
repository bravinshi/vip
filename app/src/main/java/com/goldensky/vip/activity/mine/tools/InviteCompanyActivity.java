package com.goldensky.vip.activity.mine.tools;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.bumptech.glide.Glide;
import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityInviteCompanyBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class InviteCompanyActivity extends BaseActivity<ActivityInviteCompanyBinding, PublicViewModel> implements View.OnClickListener {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarInviteCompany.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.setListener(this);

    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_invite_company;
    }

    @Override
    public void onClick(View v) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.pop_share,null);
        ImageView ivShare = inflate.findViewById(R.id.iv_share);
        Glide.with(this).load(R.mipmap.my_pic_fenxiang).into(ivShare);
        PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha=0.1f;
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        window.setAttributes(attributes);
        window.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                attributes.alpha=1.0f;
                window.setAttributes(attributes);
            }
        });
        popupWindow.showAtLocation(mBinding.bottomView, Gravity.TOP, 0,0);
    }
}