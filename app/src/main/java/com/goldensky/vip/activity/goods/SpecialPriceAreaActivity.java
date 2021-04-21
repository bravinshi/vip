package com.goldensky.vip.activity.goods;

import android.os.Bundle;
import android.view.View;

import com.goldensky.framework.viewmodel.BaseViewModel;
import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivitySpecialPriceAreaBinding;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/21 15:07
 * 包名： com.goldensky.vip.activity.goods
 * 类说明：
 */
public class SpecialPriceAreaActivity extends BaseActivity<ActivitySpecialPriceAreaBinding, BaseViewModel> {
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.ivBack.setOnClickListener(v -> SpecialPriceAreaActivity.this.finish());
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_special_price_area;
    }
}
