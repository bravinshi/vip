package com.goldensky.vip.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivitySplashBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.PublicViewModel;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/28 13:26
 * 包名： com.goldensky.vip.activity
 * 类说明：
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding, PublicViewModel> {

    private CountDownTimer countDownTimer;
    private final long millisInFuture = 3000;
    private Integer timeRemain = (int)(millisInFuture / 1000 + 1);

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        countDownTimer = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setText();
            }

            @Override
            public void onFinish() {
                setText();
                countDownTimer.cancel();
                countDownTimer = null;
                checkLoginState();
            }
        };

        countDownTimer.start();
    }

    private void setText() {
        timeRemain--;
        String text = "跳过广告(" + timeRemain + ")";
        mBinding.tvJump.setText(text);
    }

    @Override
    public void initListener() {
        mBinding.tvJump.setOnClickListener(v -> {
            countDownTimer.cancel();
            countDownTimer = null;
            checkLoginState();
        });
    }

    /**
     * 检查登录状态
     */
    private void checkLoginState() {
        if (StringUtils.isTrimEmpty(AccountHelper.getToken())) {
            // 没有登录信息 跳转到登录界面
            Starter.startLoginActivity(SplashActivity.this, null);
        } else {
            Starter.startMainActivity(SplashActivity.this, null);
        }

        finish();
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }
}
