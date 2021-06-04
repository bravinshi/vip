package com.goldensky.vip.activity.accountManager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityJyAccountBinding;
import com.goldensky.vip.databinding.ActivityJyToAccountBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class JyToAccountActivity extends BaseActivity<ActivityJyToAccountBinding, PublicViewModel> implements View.OnClickListener {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.backV.setOnClickListener(this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_jy_to_account;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_v:
                finish();
                break;
        }
    }
}