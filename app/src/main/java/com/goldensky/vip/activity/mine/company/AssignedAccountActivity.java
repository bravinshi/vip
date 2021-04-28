package com.goldensky.vip.activity.mine.company;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityAssignedAccountBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.google.android.material.tabs.TabLayout;

public class AssignedAccountActivity extends BaseActivity<ActivityAssignedAccountBinding, PublicViewModel> {

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarAssigned.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.tabAssigned.addTab(mBinding.tabAssigned.newTab().setText("代理服务收益"));
        mBinding.tabAssigned.addTab(mBinding.tabAssigned.newTab().setText("主营推广收益"));
        mBinding.tabAssigned.addTab(mBinding.tabAssigned.newTab().setText("咨询服务收益"));
        mBinding.tabAssigned.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               switch (tab.getPosition()){
                   case 0:
                        mBinding.ivFpzh.setImageResource(R.mipmap.fpzh3);
                       break;
                   case 1:
                       mBinding.ivFpzh.setImageResource(R.mipmap.fpzh4);

                       break;
                   case 2:
                       mBinding.ivFpzh.setImageResource(R.mipmap.fpzh5);

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

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_assigned_account;
    }
}