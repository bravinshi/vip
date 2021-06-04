package com.goldensky.vip.activity.mine.mall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.CircleFocusAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityMallManageBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MallManageActivity extends BaseActivity<ActivityMallManageBinding, PublicViewModel> {
    private CircleFocusAdapter adapter;
    private List<Integer> list=new ArrayList<>();
    private List<Integer> guanlianList=new ArrayList<>();
    private List<Integer> distributionList=new ArrayList<>();
    private List<Integer> pushList=new ArrayList<>();

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarMallManage.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.topBarMallManage.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Starter.startInviteCompanyActivity(MallManageActivity.this,null);
            }
        });
        mBinding.tabMallManage.addTab(mBinding.tabMallManage.newTab().setText("关联"));
        mBinding.tabMallManage.addTab(mBinding.tabMallManage.newTab().setText("分销"));
        mBinding.tabMallManage.addTab(mBinding.tabMallManage.newTab().setText("推广"));
        distributionList.add(R.mipmap.my_pic_shangchengguanli2);
        distributionList.add(R.mipmap.my_pic_shangchengguanli3);
        distributionList.add(R.mipmap.my_pic_shangchengguanli4);
        distributionList.add(R.mipmap.my_pic_shangchengguanli5);
        pushList.add(R.mipmap.fenxiao1);
        pushList.add(R.mipmap.fenxiao2);
        pushList.add(R.mipmap.fenxiao3);
        guanlianList.add(R.mipmap.gl1);
        guanlianList.add(R.mipmap.gl2);
        guanlianList.add(R.mipmap.gl3);
        guanlianList.add(R.mipmap.gl4);
        adapter=new CircleFocusAdapter(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Starter.startPromoteActivity(MallManageActivity.this,null);
            }
        });
        mBinding.rvMallManage.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvMallManage.setAdapter(adapter);
        mBinding.clItemName.setVisibility(View.GONE);
        refreshAdapter(guanlianList);
        mBinding.tabMallManage.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        mBinding.clItemName.setVisibility(View.GONE);
                        refreshAdapter(guanlianList);
                        break;
                    case 1:
                        mBinding.clItemName.setVisibility(View.VISIBLE);
                        refreshAdapter(distributionList);
                        break;
                    case 2:
                        mBinding.clItemName.setVisibility(View.GONE);
                        refreshAdapter(pushList);
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

    private void refreshAdapter(List<Integer> data) {
        list.clear();
        list.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mall_manage;
    }
}