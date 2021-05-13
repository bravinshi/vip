package com.goldensky.vip.activity.order;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.FragmentAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityOrderListBinding;
import com.goldensky.vip.fragment.orderlist.OrderListFragment;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends BaseActivity<ActivityOrderListBinding, PublicViewModel> implements View.OnClickListener {
    private List<Fragment> fragments=new ArrayList<>();


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarOrderList.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TabLayout.Tab allTab = mBinding.tabOrderList.newTab().setText(getString(R.string.text_all));
        TabLayout.Tab obligationTab = mBinding.tabOrderList.newTab().setText(getString(R.string.text_obligation));
        TabLayout.Tab waitReceivingTab = mBinding.tabOrderList.newTab().setText(getString(R.string.text_wait_for_receiving));
        TabLayout.Tab finishedTab = mBinding.tabOrderList.newTab().setText(getString(R.string.text_finished));
        mBinding.tabOrderList.addTab(allTab);
        mBinding.tabOrderList.addTab(obligationTab);
        mBinding.tabOrderList.addTab(waitReceivingTab);
//        mBinding.tabOrderList.addTab(mBinding.tabOrderList.newTab().setText(getString(R.string.text_finished)));
        mBinding.tabOrderList.addTab(finishedTab);
//      订单状态  0:未付款 1:待发货  2:待收货 3:已完成 4:关闭 5:已取消',
        int type = getIntent().getIntExtra("orderType", 0);
//      Fragment类型  0:全部 1:待付款 2:待收货 3:待评价
        fragments.add(new OrderListFragment(0));
        fragments.add(new OrderListFragment(1));
        fragments.add(new OrderListFragment(2));
        fragments.add(new OrderListFragment(3));
        mBinding.vpOrderList.setUserInputEnabled(false);
        mBinding.vpOrderList.setOffscreenPageLimit(4);
        mBinding.vpOrderList.setAdapter(new FragmentAdapter(getSupportFragmentManager(),getLifecycle(),fragments));
        switch (type){
            case 0:
                mBinding.tabOrderList.selectTab(allTab);
                break;
            case 1:
                mBinding.tabOrderList.selectTab(obligationTab);
                break;
            case 2:
                mBinding.tabOrderList.selectTab(waitReceivingTab);
                break;
            case 3:
                mBinding.tabOrderList.selectTab(finishedTab);
                break;
        }
        mBinding.vpOrderList.setCurrentItem(type,false);
        mBinding.tabOrderList.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mBinding.vpOrderList.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mBinding.setListener(this);

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