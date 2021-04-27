package com.goldensky.vip.activity.mine.vip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityVipHomeBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class VipHomeActivity extends BaseActivity<ActivityVipHomeBinding, PublicViewModel> implements View.OnClickListener{

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.backV.setOnClickListener(this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_vip_home;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.back_v) {
            finish();
        }
    }
}