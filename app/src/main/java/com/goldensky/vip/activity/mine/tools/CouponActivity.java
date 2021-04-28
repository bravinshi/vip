package com.goldensky.vip.activity.mine.tools;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityCouponBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CouponActivity extends BaseActivity<ActivityCouponBinding, PublicViewModel> {

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarCoupon.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.tabCoupon.addTab(mBinding.tabCoupon.newTab().setText(getString(R.string.text_all)));
        mBinding.tabCoupon.addTab(mBinding.tabCoupon.newTab().setText("未使用"));
        mBinding.tabCoupon.addTab(mBinding.tabCoupon.newTab().setText("已过期"));
        mBinding.tabCoupon.addTab(mBinding.tabCoupon.newTab().setText("已使用"));

        mBinding.tabCoupon.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:

                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void refreshList(List<Integer> orderList){

    }
    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_coupon;
    }
}