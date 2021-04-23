package com.goldensky.vip.activity.goods;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.goldensky.framework.viewmodel.BaseViewModel;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.SearchAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.SearchItemBean;
import com.goldensky.vip.databinding.ActivitySearchBinding;
import com.goldensky.vip.entity.TabEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/21 17:25
 * 包名： com.goldensky.vip.activity.goods
 * 类说明：
 */
public class SearchActivity extends BaseActivity<ActivitySearchBinding, BaseViewModel> implements View.OnClickListener {

    private SearchAdapter searchAdapter;
    private List<SearchItemBean> goodsList = new ArrayList<>();
    private List<SearchItemBean> serviceList = new ArrayList<>();

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        ArrayList<CustomTabEntity> tabEntities = new ArrayList<>();
        tabEntities.add(new TabEntity("商品"));
        tabEntities.add(new TabEntity("服务"));
        searchAdapter = new SearchAdapter(null);
        mBinding.rv.setAdapter(searchAdapter);
        mBinding.rv.setLayoutManager(new GridLayoutManager(SearchActivity.this, 4));
        mBinding.setListener(this);
        mBinding.ctl.setTabData(tabEntities);

        mBinding.ctl.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 0) {
                    searchAdapter.setNewData(goodsList);
                } else {
                    searchAdapter.setNewData(serviceList);
                }
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mBinding.ctl.setCurrentTab(0);

        goodsList.add(new SearchItemBean(R.mipmap.img_gxgs, "干鲜果蔬"));
        goodsList.add(new SearchItemBean(R.mipmap.img_spjy, "食品酒饮"));
        goodsList.add(new SearchItemBean(R.mipmap.img_jksh, "营养滋补"));
        goodsList.add(new SearchItemBean(R.mipmap.img_mzhf, "美妆护肤"));
        goodsList.add(new SearchItemBean(R.mipmap.img_ryjf, "日用家纺"));
        goodsList.add(new SearchItemBean(R.mipmap.img_jjjz, "家居家装"));
        goodsList.add(new SearchItemBean(R.mipmap.img_myyp, "母婴用品"));
        goodsList.add(new SearchItemBean(R.mipmap.img_twwy, "图文文娱"));
        goodsList.add(new SearchItemBean(R.mipmap.img_hwyd, "户外运动"));
        goodsList.add(new SearchItemBean(R.mipmap.img_jydq, "家用电器"));
        goodsList.add(new SearchItemBean(R.mipmap.img_fsny, "服饰内衣"));
        goodsList.add(new SearchItemBean(R.mipmap.img_xxxb, "鞋靴箱包"));
        goodsList.add(new SearchItemBean(R.mipmap.img_xhyp, "洗护用品"));

        serviceList.add(new SearchItemBean(R.mipmap.img_xxyle, "休闲娱乐"));
        serviceList.add(new SearchItemBean(R.mipmap.img_jksh, "健康生活"));
        serviceList.add(new SearchItemBean(R.mipmap.img_lycx, "旅游出行"));
        serviceList.add(new SearchItemBean(R.mipmap.img_cwsh, "宠物生活"));
        serviceList.add(new SearchItemBean(R.mipmap.img_acfw, "爱车服务"));
        serviceList.add(new SearchItemBean(R.mipmap.img_hqsy, "婚庆摄影"));
        serviceList.add(new SearchItemBean(R.mipmap.img_swfw, "商务服务"));
        serviceList.add(new SearchItemBean(R.mipmap.img_xxpx, "学习培训"));
        serviceList.add(new SearchItemBean(R.mipmap.img_shfw, "售后服务"));
        serviceList.add(new SearchItemBean(R.mipmap.img_jzfw, "家政服务"));

        searchAdapter.setNewData(goodsList);
    }

    @Override
    public void observe() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_service:
                Starter.startCustomerServiceActivity(this,null);
                break;
        }
    }
}
