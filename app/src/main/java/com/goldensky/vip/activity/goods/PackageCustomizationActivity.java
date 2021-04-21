package com.goldensky.vip.activity.goods;

import android.os.Bundle;

import com.goldensky.framework.viewmodel.BaseViewModel;
import com.goldensky.vip.R;
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
public class PackageCustomizationActivity extends BaseActivity<ActivityPackageCustomzationBinding, BaseViewModel> {
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        ImmersionBar.with(this).statusBarDarkFont(true)
                .statusBarView(mBinding.vStatusBar).init();

        mBinding.ivBack.setOnClickListener(v -> PackageCustomizationActivity.this.finish());
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_package_customzation;
    }
}
