package com.goldensky.vip.activity.mine.focus;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityGoodsFocusBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class GoodsFocusActivity extends BaseActivity<ActivityGoodsFocusBinding, PublicViewModel> implements View.OnClickListener {

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

    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_focus;
    }

    @Override
    public void onClick(View v) {
        Starter.startCustomerServiceActivity(this,null);
    }
}