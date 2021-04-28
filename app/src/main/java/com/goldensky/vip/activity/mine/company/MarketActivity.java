package com.goldensky.vip.activity.mine.company;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityMarketBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class MarketActivity extends BaseActivity<ActivityMarketBinding, PublicViewModel> {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarMarket.setOnClickListener(new View.OnClickListener() {
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
        return R.layout.activity_market;
    }
}