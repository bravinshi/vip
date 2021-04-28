package com.goldensky.vip.activity.mine.company;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityPerformanceBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class PerformanceActivity extends BaseActivity<ActivityPerformanceBinding, PublicViewModel> {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.btnBack.setOnClickListener(new View.OnClickListener() {
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
        return R.layout.activity_performance;
    }
}