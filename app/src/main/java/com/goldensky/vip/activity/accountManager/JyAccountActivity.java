package com.goldensky.vip.activity.accountManager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityJyAccountBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class JyAccountActivity extends BaseActivity<ActivityJyAccountBinding, PublicViewModel> implements View.OnClickListener {

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.backV.setOnClickListener(this);
        mBinding.zzV.setOnClickListener(this);
        mBinding.jkV.setOnClickListener(this);
        mBinding.fkV.setOnClickListener(this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_jy_account;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_v:
                finish();
                break;
            case R.id.zz_v:
                Intent zzIntent = new Intent(this, JyToAccountActivity.class);
                startActivity(zzIntent);
                break;
            case R.id.jk_v:
                Intent jkIntent = new Intent(this, JyToJkActivity.class);
                startActivity(jkIntent);
                break;
            case R.id.fk_v:
                Intent fkIntent = new Intent(this, JyToFkActivity.class);
                startActivity(fkIntent);
                break;
        }
    }
}