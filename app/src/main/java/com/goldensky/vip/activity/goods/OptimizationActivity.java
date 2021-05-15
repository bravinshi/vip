package com.goldensky.vip.activity.goods;

import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;

import com.goldensky.framework.util.CollectionUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.NormalGoodsAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.CommodityBean;
import com.goldensky.vip.databinding.ActivityOptimizationBinding;
import com.goldensky.vip.entity.NormalGoodsEntity;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/13 10:07
 * 包名： com.goldensky.vip.activity.goods
 * 类说明：
 */
public class OptimizationActivity extends BaseActivity<ActivityOptimizationBinding, PublicViewModel> {
    private final NormalGoodsAdapter normalGoodsAdapter = new NormalGoodsAdapter();
    private Integer currentPage = 1;
    private final Integer pageSize = 10;
    private final List<NormalGoodsEntity> goodsList = new ArrayList<>();
    private boolean isRefresh = true;

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.tabBar.setCenterHint("金天优选");
        mBinding.rv.setAdapter(normalGoodsAdapter);
        normalGoodsAdapter.setNewInstance(goodsList);
        mBinding.rv.setLayoutManager(new GridLayoutManager(OptimizationActivity.this, 2));
        getData();
    }

    @Override
    public void initListener() {
        mBinding.smartRefresh.setOnRefreshListener(refreshLayout -> {
            isRefresh = true;
            currentPage = 1;
            getData();
        });
        mBinding.smartRefresh.setOnLoadMoreListener(refreshLayout -> {
            isRefresh = false;
            currentPage++;
            getData();
        });

        normalGoodsAdapter.setOnItemClickListener((adapter, view, position) -> {
            Bundle bundle = new Bundle();
            Integer goodsId = ((NormalGoodsEntity)adapter.getItem(position)).getGoodsId();
            bundle.putInt(GoodsDetailActivity.KEY_GOODS_ID, goodsId);
            Starter.startGoodsDetailActivity(OptimizationActivity.this, bundle);
        });

        mBinding.tabBar.setBackListener(v -> OptimizationActivity.this.finish());
    }

    private void getData() {
        mViewModel.optimization(currentPage, pageSize, AccountHelper.getUserId(), netResponse -> {
            mBinding.smartRefresh.finishLoadMore();
            mBinding.smartRefresh.finishRefresh();
            if (!isRefresh) {
                currentPage--;// 在发起加载更多时，currentPage会增1
            }
        });
    }

    @Override
    public void observe() {
        mViewModel.normalGoodsLive.observe(this, commodityBeans -> {
            mBinding.smartRefresh.finishLoadMore();
            mBinding.smartRefresh.finishRefresh();
            if (isRefresh) {
                goodsList.clear();
            }
            if (CollectionUtils.nullOrEmpty(commodityBeans)) {
                return;
            }
            List<NormalGoodsEntity> normalGoodsEntities = new ArrayList<>();
            for(CommodityBean commodityBean : commodityBeans) {
                normalGoodsEntities.add(NormalGoodsEntity.fromCommodity(commodityBean));
            }

            goodsList.addAll(normalGoodsEntities);
            normalGoodsAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_optimization;
    }
}
