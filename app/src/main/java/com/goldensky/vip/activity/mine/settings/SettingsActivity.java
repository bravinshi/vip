package com.goldensky.vip.activity.mine.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.activity.mine.tools.adress.MyAddressActivity;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.DeleteUserAddressReqBean;
import com.goldensky.vip.databinding.ActivitySettingsBinding;
import com.goldensky.vip.enumerate.DefaultUrlEnum;
import com.goldensky.vip.event.VipUserChangeEvent;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.PublicViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SettingsActivity extends BaseActivity<ActivitySettingsBinding, PublicViewModel> implements View.OnClickListener {
    private AlertDialog dialog;

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mBinding.topBarSettings.setBackListener(new View.OnClickListener() {
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
            mBinding.tvUserNameSettings.setText(AccountHelper.getUserNick());
        }else {
            mBinding.tvUserNameSettings.setText(AccountHelper.getUserMobile());
        }
        if(AccountHelper.getUserPic()!=null&&!AccountHelper.getUserPic().equals("")){
            Glide.with(this).load(AccountHelper.getUserPic()).apply(new RequestOptions().circleCrop()).into(mBinding.ivHeadSettings);
        }else {
            Glide.with(this).load(DefaultUrlEnum.DEFAULTHEADPIC.value).apply(new RequestOptions().circleCrop()).into(mBinding.ivHeadSettings);
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

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cl_person_settings:
                Starter.startPersonDetailActivity(this,null);
                break;
            case R.id.cl_change_password_settings:
                Starter.startChangePWDActivity(this,null);
                break;
            case R.id.logout_settings:
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                View popView = LayoutInflater.from(SettingsActivity.this).inflate(R.layout.pop_logout_settings, null);
                TextView confirm = popView.findViewById(R.id.btn_confirm_logout);
                TextView cancel = popView.findViewById(R.id.btn_cancel_logout);
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AccountHelper.loginOut();
                        Starter.startLoginActivity(SettingsActivity.this,null);
                        dialog.dismiss();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                builder.setView(popView);
                dialog = builder.create();
                dialog.show();
                WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
                params.width=550;
                params.height=350;
                dialog.getWindow().setAttributes(params);
                break;
            case R.id.cl_current_version_settings:
                Starter.startAboutGoldenDaysActivity(this,null);
                break;

        }
    }
}