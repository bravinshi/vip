package com.goldensky.vip.activity.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.CircleFocusAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityCustomerServiceBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceActivity extends BaseActivity<ActivityCustomerServiceBinding, PublicViewModel> {
    private CircleFocusAdapter adapter;
    private List<Integer> list=new ArrayList<>();
    private boolean isMore=false;
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        list.add(R.mipmap.kf1);
        list.add(R.mipmap.kf2);
        list.add(R.mipmap.kf3);
        adapter=new CircleFocusAdapter(list);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.btn_more:
                        if(isMore){
                            list.remove(1);
                            list.add(1,R.mipmap.kf2);
                        }else {
                            list.remove(1);
                            list.add(1,R.mipmap.kf4);
                        }
                        isMore=!isMore;
                        adapter.notifyDataSetChanged();
                        break;
                    case R.id.btn_mai:
                        if(isMore){
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_FAKE_DATA",25);
                           Starter.startConfirmOrderActivity(CustomerServiceActivity.this,bundle);
                        }
                        break;
                }
            }
        });
        mBinding.rvService.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvService.setAdapter(adapter);
        mBinding.topBarService.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_customer_service;
    }
}