package com.goldensky.vip.activity.goods;

import android.os.Build;
import android.os.Bundle;
import android.util.ArraySet;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldensky.framework.util.CollectionUtils;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.adapter.BannerImageAdapter;
import com.goldensky.vip.adapter.GoodsDetailAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.base.ui.view.FullyLinearLayoutManager;
import com.goldensky.vip.bean.CommodityBean;
import com.goldensky.vip.bean.CommodityPicBean;
import com.goldensky.vip.bean.CommodityResBean;
import com.goldensky.vip.bean.GoodsCommentResBean;
import com.goldensky.vip.databinding.ActivityGoodsDetailBinding;
import com.goldensky.vip.helper.ImageLoaderHelper;
import com.goldensky.vip.viewmodel.goods.GoodsDetailViewModel;
import com.youth.banner.Banner;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/14 14:21
 * 包名： com.goldensky.vip.activity.goods
 * 类说明：
 */
public class GoodsDetailActivity extends BaseActivity<ActivityGoodsDetailBinding,
        GoodsDetailViewModel> {

    public static final String KEY_GOODS_ID = "KEY_GOODS_ID";
    private String goodsId = "";
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
//        Bundle bundle = getIntent().getExtras();
//        if (bundle == null) {
//            return;
//        }
//
//        Integer goodsId = bundle.getInt(KEY_GOODS_ID, -1);
//        if (goodsId == -1) {
//            return;
//        }
        // 获取商品详情
        mViewModel.getGoodsDetail(140);
        // 获取评论信息
        mViewModel.getGoodsComment(1, 1, goodsId, null);
        // 获取地址信息
        mViewModel.
    }

    public void showGoodsDetail(CommodityResBean data) {
        CommodityBean detail = data.getCommodity();
        // 轮播图片
        Set<String> pics = new HashSet<>();
        if (!StringUtils.isTrimEmpty(detail.getCommodityIcon())) {
            pics.add(detail.getCommodityIcon());
        }
        if (!CollectionUtils.nullOrEmpty(detail.getCommodityPicList())) {
            for (CommodityPicBean picBean : detail.getCommodityPicList()) {
                if (!StringUtils.isTrimEmpty(picBean.getPicUrl())) {
                    pics.add(picBean.getPicUrl());
                }
            }
        }
        if (!pics.isEmpty()) {
            mBinding.vpImage.setAdapter(new BannerImageAdapter(new ArrayList<>(pics)))
            .addBannerLifecycleObserver(this)//添加生命周期观察者
            .setIndicator(new CircleIndicator(this));
        } else {
            mBinding.vpImage.setVisibility(View.GONE);
        }
        // 价格
        mBinding.tvPrice.setText("￥" + detail.getCommodityOldPrice());
        // 标题
        mBinding.tvTitle.setText(detail.getCommodityName());
        // 详情
        String content = detail.getCommodityDesc().replace("<img", "<img style=\"max-width:100%;height:auto\"");
        mBinding.wvDetail.loadData(content, "text/html", "utf-8");
    }

    @Override
    public void observe() {
        mViewModel.goodsCommentLive.observe(this, goodsCommentResBean -> {
            mBinding.tvCommentNum.setText("(" + goodsCommentResBean.getTotalCount() + ")");
        });

        mViewModel.goodsDetailLive.observe(this, this::showGoodsDetail);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_detail;
    }
}
