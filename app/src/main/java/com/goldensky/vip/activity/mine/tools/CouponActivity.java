package com.goldensky.vip.activity.mine.tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.adapter.GoodsFocusAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityCouponBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class CouponActivity extends BaseActivity<ActivityCouponBinding, PublicViewModel> {
    private List<Integer> list=new ArrayList<>();
    private List<Integer> allList=new ArrayList<>();
    private List<Integer> unusedList=new ArrayList<>();
    private List<Integer> staleDatedList=new ArrayList<>();
    private List<Integer> usedList=new ArrayList<>();
    private GoodsFocusAdapter adapter;
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
        allList.add(R.mipmap.my_pic_youhuiquan1);
        allList.add(R.mipmap.my_pic_youhuiquan2);
        allList.add(R.mipmap.my_pic_youhuiquan3);
        allList.add(R.mipmap.my_pic_youhuiquan4);
        allList.add(R.mipmap.my_pic_youhuiquan5);
        unusedList.add(R.mipmap.my_pic_youhuiquan1);
        unusedList.add(R.mipmap.my_pic_youhuiquan2);
        unusedList.add(R.mipmap.my_pic_youhuiquan5);
        staleDatedList.add(R.mipmap.my_pic_youhuiquan4);
        usedList.add(R.mipmap.my_pic_youhuiquan3);
        adapter=new GoodsFocusAdapter(list);
        mBinding.rvCoupon.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvCoupon.setAdapter(adapter);
        refreshList(allList);
        mBinding.tabCoupon.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        refreshList(allList);
                        break;
                    case 1:
                        refreshList(unusedList);
                        break;
                    case 2:
                        refreshList(staleDatedList);
                        break;
                    case 3:
                        refreshList(usedList);
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
        list.clear();
        list.addAll(orderList);
        adapter.notifyDataSetChanged();
    }
    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_coupon;
    }
}