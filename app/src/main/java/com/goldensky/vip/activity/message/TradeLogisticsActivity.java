package com.goldensky.vip.activity.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.adapter.CircleFocusAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityTradeLogisticsBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class TradeLogisticsActivity extends BaseActivity<ActivityTradeLogisticsBinding, PublicViewModel> {
    private CircleFocusAdapter adapter;
    private List<Integer> list=new ArrayList<>();
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarTradeLogistics.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list.add(R.mipmap.jywl1);
        list.add(R.mipmap.jywl2);
        list.add(R.mipmap.jywl3);
        adapter=new CircleFocusAdapter(list);
        mBinding.rvTradeLogistics.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvTradeLogistics.setAdapter(adapter);

    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_trade_logistics;
    }
}