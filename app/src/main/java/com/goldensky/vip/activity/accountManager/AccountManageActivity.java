package com.goldensky.vip.activity.accountManager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityAccountManageBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class AccountManageActivity extends BaseActivity<ActivityAccountManageBinding, PublicViewModel> implements View.OnClickListener {

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.backV.setOnClickListener(this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_manage;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_v) {
            finish();
        }
    }
}