package com.goldensky.vip.activity.goods;

import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.goldensky.framework.viewmodel.BaseViewModel;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityRecommendBinding;
import com.goldensky.vip.entity.TabEntity;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/21 10:34
 * 包名： com.goldensky.vip.activity.account
 * 类说明：
 */
public class RecommendActivity extends BaseActivity<ActivityRecommendBinding, BaseViewModel> implements View.OnClickListener {
    @Override
    public void onFinishInit(Bundle savedInstanceState) {

        ImmersionBar.with(this).statusBarDarkFont(true)
                .statusBarView(mBinding.vStatusBar).init();

        mBinding.ivBack.setOnClickListener(v -> RecommendActivity.this.finish());

        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("商品推荐"));
        tabEntities.add(new TabEntity("服务推荐"));

        mBinding.ctl.setTabData(tabEntities);
        mBinding.ctl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
                    mBinding.clGoods.setVisibility(View.VISIBLE);
                    mBinding.llService.setVisibility(View.GONE);
                } else {
                    mBinding.clGoods.setVisibility(View.GONE);
                    mBinding.llService.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mBinding.clGoods.setVisibility(View.VISIBLE);
        mBinding.llService.setVisibility(View.GONE);

        mBinding.ctl.setCurrentTab(0);
        mBinding.setListener(this);

    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recommend;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.iv_1:
                bundle.putInt("KEY_FAKE_DATA", 1);
                Starter.startGoodsDetailActivity(this, bundle);
                break;
            case R.id.iv_2:
                bundle.putInt("KEY_FAKE_DATA", 6);
                Starter.startGoodsDetailActivity(this, bundle);
                break;
            case R.id.iv_3:
                bundle.putInt("KEY_FAKE_DATA", 7);
                Starter.startGoodsDetailActivity(this, bundle);
                break;
            case R.id.iv_4:
                bundle.putInt("KEY_FAKE_DATA", 8);
                Starter.startGoodsDetailActivity(this, bundle);
                break;
            default:
                Starter.startCustomerServiceActivity(this,null);
                break;
        }
    }
}
