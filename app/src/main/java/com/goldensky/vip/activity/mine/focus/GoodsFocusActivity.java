package com.goldensky.vip.activity.mine.focus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.goldensky.vip.R;
import com.goldensky.vip.adapter.GoodsFocusAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityGoodsFocusBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class GoodsFocusActivity extends BaseActivity<ActivityGoodsFocusBinding, PublicViewModel> {
    private GoodsFocusAdapter adapter;
    private List<Integer> list=new ArrayList<>();

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarGoodsFocus.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.tabGoodsFocus.addTab(mBinding.tabGoodsFocus.newTab().setText(getString(R.string.text_goods)));
        mBinding.tabGoodsFocus.addTab(mBinding.tabGoodsFocus.newTab().setText(getString(R.string.text_service)));
        list.add(R.mipmap.my_pic_tiaomu1);
        list.add(R.mipmap.my_pic_tiaomu2);
        list.add(R.mipmap.my_pic_tiaomu3);
        list.add(R.mipmap.my_pic_tiaomu4);
        adapter=new GoodsFocusAdapter(list);
        mBinding.rvGoodsFocus.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvGoodsFocus.setAdapter(adapter);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_focus;
    }
}