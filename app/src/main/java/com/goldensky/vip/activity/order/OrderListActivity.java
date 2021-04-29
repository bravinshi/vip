package com.goldensky.vip.activity.order;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityOrderListBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends BaseActivity<ActivityOrderListBinding, PublicViewModel> implements View.OnClickListener {



    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarOrderList.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.tabOrderList.addTab(mBinding.tabOrderList.newTab().setText(getString(R.string.text_all)));
        mBinding.tabOrderList.addTab(mBinding.tabOrderList.newTab().setText(getString(R.string.text_obligation)));
        mBinding.tabOrderList.addTab(mBinding.tabOrderList.newTab().setText(getString(R.string.text_wait_for_receiving)));
//        mBinding.tabOrderList.addTab(mBinding.tabOrderList.newTab().setText(getString(R.string.text_finished)));
        mBinding.tabOrderList.addTab(mBinding.tabOrderList.newTab().setText(getString(R.string.text_wait_for_evaluated)));
        int type = getIntent().getIntExtra("orderType", 0);


        mBinding.setListener(this);
        mBinding.rvOrderList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_list;
    }

    @Override
    public void onClick(View v) {
        Starter.startCustomerServiceActivity(this,null);
    }
}