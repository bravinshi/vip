package com.goldensky.vip.activity.goods;

import android.os.Bundle;
import android.view.View;

import com.goldensky.framework.viewmodel.BaseViewModel;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityHotTodayBinding;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/21 16:33
 * 包名： com.goldensky.vip.activity.goods
 * 类说明：
 */
public class HotTodayActivity extends BaseActivity<ActivityHotTodayBinding, BaseViewModel> implements View.OnClickListener {
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.tabBar.setBackListener(v -> HotTodayActivity.this.finish());
        mBinding.setListener(this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_hot_today;
    }

    @Override
    public void onClick(View v) {
        Starter.startCustomerServiceActivity(this,null);
    }
}
