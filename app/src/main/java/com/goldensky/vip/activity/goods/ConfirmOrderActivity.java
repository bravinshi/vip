package com.goldensky.vip.activity.goods;

import android.os.Bundle;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityConfirmOrderBinding;
import com.goldensky.vip.viewmodel.goods.GoodsDetailViewModel;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/11 14:08
 * 包名： com.goldensky.vip.activity.goods
 * 类说明：
 */
public class ConfirmOrderActivity extends BaseActivity<ActivityConfirmOrderBinding, GoodsDetailViewModel> {

    @Override
    public void onFinishInit(Bundle savedInstanceState) {

    }

    @Override
    public void observe() {

    }

    @Override
    public void initListener() {
        super.initListener();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_order;
    }
}
