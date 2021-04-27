package com.goldensky.vip.activity.order;


import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityOrderBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class XsOrderActivity extends BaseActivity<ActivityOrderBinding, PublicViewModel> implements View.OnClickListener {

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.backV.setOnClickListener(this);
    }


    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
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