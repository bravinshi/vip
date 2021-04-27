package com.goldensky.vip.activity.mine.company;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.adapter.GoodsFocusAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityShenPiBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShenPiActivity extends BaseActivity<ActivityShenPiBinding, PublicViewModel> {
    private GoodsFocusAdapter adapter;
    private List<Integer> list=new ArrayList<>();

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarShenpi.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list.add(R.mipmap.sp2);
        list.add(R.mipmap.sp3);
        list.add(R.mipmap.sp4);
        adapter=new GoodsFocusAdapter(list);
        mBinding.rvShenpi.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvShenpi.setAdapter(adapter);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_shen_pi;
    }
}