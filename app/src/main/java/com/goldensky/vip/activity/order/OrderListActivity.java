package com.goldensky.vip.activity.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.GoodsFocusAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityOrderListBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class OrderListActivity extends BaseActivity<ActivityOrderListBinding, PublicViewModel> implements View.OnClickListener {
    private List<Integer> list=new ArrayList<>();
    private List<Integer> allList=new ArrayList<>();
    private List<Integer> obligationList=new ArrayList<>();
    private List<Integer> receiveList=new ArrayList<>();
    private List<Integer> finishedList=new ArrayList<>();
    private List<Integer> evaluatedList =new ArrayList<>();
    private GoodsFocusAdapter adapter;

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
        mBinding.tabOrderList.addTab(mBinding.tabOrderList.newTab().setText(getString(R.string.text_finished)));
        mBinding.tabOrderList.addTab(mBinding.tabOrderList.newTab().setText(getString(R.string.text_wait_for_evaluated)));
        int type = getIntent().getIntExtra("orderType", 0);

        allList.add(R.mipmap.my_pic_dingdanguanli1);
        allList.add(R.mipmap.my_pic_dingdanguanli2);
        allList.add(R.mipmap.my_pic_dingdanguanli3);
        allList.add(R.mipmap.my_pic_dingdanguanli4);
        allList.add(R.mipmap.my_pic_dingdanguanli5);
        obligationList.add(R.mipmap.my_pic_dingdanguanli3);
        finishedList.add(R.mipmap.my_pic_dingdanguanli1);
        finishedList.add(R.mipmap.my_pic_dingdanguanli2);
        evaluatedList.add(R.mipmap.my_pic_dingdanguanli1);
        adapter=new GoodsFocusAdapter(list);
        mBinding.setListener(this);
        mBinding.rvOrderList.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvOrderList.setAdapter(adapter);
        if(type>=0&&type<=4){
            mBinding.tabOrderList.getTabAt(type).select();
            switch (type){
                case 0:
                    refreshList(allList);
                    break;
                case 1:
                    refreshList(obligationList);
                    break;
                case 2:
                    refreshList(receiveList);
                    break;
                case 3:
                    refreshList(finishedList);
                    break;
                case 4:
                    refreshList(evaluatedList);
                    break;
            }
        }else {
            mBinding.tabOrderList.getTabAt(0).select();
            refreshList(allList);
        }
        mBinding.tabOrderList.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position){
                    case 0:
                        refreshList(allList);
                        break;
                    case 1:
                        refreshList(obligationList);
                        break;
                    case 2:
                        refreshList(receiveList);
                        break;
                    case 3:
                        refreshList(finishedList);
                        break;
                    case 4:
                        refreshList(evaluatedList);
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
        if(orderList.size()==0){
            mBinding.clOrderList.setVisibility(View.VISIBLE);
        }else {
            mBinding.clOrderList.setVisibility(View.GONE);
        }
        adapter.notifyDataSetChanged();
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