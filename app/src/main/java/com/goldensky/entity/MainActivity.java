package com.goldensky.entity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.goldensky.entity.base.activity.BaseActivity;
import com.goldensky.entity.base.viewmodel.NetWorkViewModel;
import com.goldensky.entity.databinding.ActivityMainBinding;

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