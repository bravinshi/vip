package com.goldensky.vip;

import android.os.Bundle;

import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityMainBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, PublicViewModel> {
    @Override
    public void onFinishInit(Bundle savedInstanceState) {

    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}