package com.goldensky.vip.activity.brandcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivitySjczBinding;
import com.goldensky.vip.fragment.main.HomeFragment;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class SjczActivity extends BaseActivity<ActivitySjczBinding, PublicViewModel> implements View.OnClickListener {
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.backV.setOnClickListener(this);
        mBinding.bottomIv.setOnClickListener(this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_sjcz;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_v:
                finish();
                break;
            case R.id.bottom_iv:
                HomeFragment.isTYST = false;
                finish();
                break;
        }
    }
}