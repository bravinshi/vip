package com.goldensky.vip.activity.goods;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.goldensky.vip.R;
import com.goldensky.vip.adapter.NormalGoodsAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityHotTodayBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/21 16:33
 * 包名： com.goldensky.vip.activity.goods
 * 类说明：
 */
public class HotTodayActivity extends BaseActivity<ActivityHotTodayBinding, PublicViewModel> {
    private final NormalGoodsAdapter normalGoodsAdapter = new NormalGoodsAdapter();

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        // TODO 请求数据
    }

    @Override
    public void initListener() {
        normalGoodsAdapter.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                // TODO 跳转到商品详情
            }
        });

        mBinding.tabBar.setBackListener(v -> HotTodayActivity.this.finish());
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_hot_today;
    }
}
