package com.goldensky.vip.activity.mine.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityAboutGoldenDaysBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class AboutGoldenDaysActivity extends BaseActivity<ActivityAboutGoldenDaysBinding, PublicViewModel> {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarAbout.setBackListener(new View.OnClickListener() {
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
        return R.layout.activity_about_golden_days;
    }
}