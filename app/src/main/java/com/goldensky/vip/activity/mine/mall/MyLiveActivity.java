package com.goldensky.vip.activity.mine.mall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityMyLiveBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class MyLiveActivity extends BaseActivity<ActivityMyLiveBinding, PublicViewModel> {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarLive.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.topBarLive.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_live;
    }
}