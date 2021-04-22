package com.goldensky.vip.activity.customer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

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

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        list.add(R.mipmap.my_pic_kefutanchuang1);
        list.add(R.mipmap.my_pic_kefutanchuang2);
        list.add(R.mipmap.my_pic_kefutanchuang3);
        list.add(R.mipmap.my_pic_kefutanchuang4);
        list.add(R.mipmap.my_pic_kefutanchuang5);
        adapter=new CircleFocusAdapter(list);
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