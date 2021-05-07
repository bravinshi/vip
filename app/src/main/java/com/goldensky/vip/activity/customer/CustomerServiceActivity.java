package com.goldensky.vip.activity.customer;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityCustomerServiceBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceActivity extends BaseActivity<ActivityCustomerServiceBinding, PublicViewModel> {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {

    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_customer_service;
    }
}