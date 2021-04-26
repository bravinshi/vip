package com.goldensky.vip.activity.accountManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityJyToJkBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class JyToJkActivity extends BaseActivity<ActivityJyToJkBinding, PublicViewModel> implements View.OnClickListener {

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.backV.setOnClickListener(this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_jy_to_jk;
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