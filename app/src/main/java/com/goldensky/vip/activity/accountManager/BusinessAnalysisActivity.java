package com.goldensky.vip.activity.accountManager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityBusinessAnalysisBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class BusinessAnalysisActivity extends BaseActivity<ActivityBusinessAnalysisBinding, PublicViewModel> {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarBusinessAnalysis.setBackListener(new View.OnClickListener() {
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
        return R.layout.activity_business_analysis;
    }
}