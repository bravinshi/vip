package com.goldensky.vip.activity.mine.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivitySettingsBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class SettingsActivity extends BaseActivity<ActivitySettingsBinding, PublicViewModel> implements View.OnClickListener {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarSettings.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.setListener(this);

    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cl_person_settings:
                Starter.startPersonDetailActivity(this,null);
                break;
            case R.id.cl_change_password_settings:
                Starter.startChangePWDActivity(this,null);
                break;
            case R.id.cl_current_version_settings:

                break;
        }
    }
}