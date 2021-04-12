package com.goldensky.vip;

import android.os.Bundle;

import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.base.viewmodel.NetWorkViewModel;
import com.goldensky.vip.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding> {
    private NetWorkViewModel netWorkViewModel;
    @Override
    public void onFinishInit(Bundle savedInstanceState) {

    }

    @Override
    public void observe() {

    }

    @Override
    public void initViewModel() {
        netWorkViewModel = getViewModelProvider().get(NetWorkViewModel.class);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}