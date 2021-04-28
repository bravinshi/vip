package com.goldensky.vip.activity;

import android.os.Bundle;

import com.goldensky.vip.R;
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
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        checkLoginState();
    }

    /**
     * 检查登录状态
     */
    private void checkLoginState() {
        AccountHelper.
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }
}
