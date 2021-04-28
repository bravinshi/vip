package com.goldensky.vip.activity.goods;

import android.os.Bundle;
import android.view.View;

import com.goldensky.framework.viewmodel.BaseViewModel;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityPackageCustomzationBinding;
import com.gyf.immersionbar.ImmersionBar;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/21 17:16
 * 包名： com.goldensky.vip.activity.goods
 * 类说明：
 */
public class PackageCustomizationActivity extends BaseActivity<ActivityPackageCustomzationBinding, BaseViewModel> implements View.OnClickListener {
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        ImmersionBar.with(this).statusBarDarkFont(true)
                .statusBarView(mBinding.vStatusBar).init();

        mBinding.ivBack.setOnClickListener(v -> PackageCustomizationActivity.this.finish());
        mBinding.setListener(this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_package_customzation;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_service:
                Starter.startCustomerServiceActivity(this,null);
                break;
            case R.id.btn_back_package:
                finish();
                break;
            case R.id.btn_all_package:
                toast("尽请期待");
                break;
        }
    }
}
