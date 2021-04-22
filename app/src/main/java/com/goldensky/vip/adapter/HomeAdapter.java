package com.goldensky.vip.adapter;

import android.database.DatabaseUtils;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.bean.HomeBean;
import com.goldensky.vip.databinding.ItemHomeJtyxBinding;
import com.goldensky.vip.databinding.ItemHomeLbBinding;
import com.goldensky.vip.databinding.ItemHomeLbBindingImpl;
import com.goldensky.vip.databinding.ItemHomeMsgBinding;
import com.goldensky.vip.databinding.ItemHomeMsgBindingImpl;
import com.goldensky.vip.databinding.ItemHomeProductBinding;
import com.goldensky.vip.databinding.ItemHomeYhzqBinding;

import java.util.List;

public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeBean, BaseViewHolder> {

    public static final int ITEM_TYPE_LB = 1; //轮播
    public static final int ITEM_TYPE_MSG = 2; //消息
    public static final int ITEM_TYPE_RMD = 3; //为你推荐
    public static final int ITEM_TYPE_JRBK = 4; //今日爆款
    public static final int ITEM_TYPE_YHZQ = 5; //优惠专区
    public static final int ITEM_TYPE_JTYX = 6; //金天优选

    private HomeProductAdapter mRmdProductAdapter;
    private HomeProductAdapter mTopProductAdapter;
    private HomeProductAdapter mMiddleProductAdapter;
    private HomeProductAdapter mBottomProductAdapter;
    private HomeProductAdapter mJrbkProductAdapter;

    public HomeAdapter(List<HomeBean> data) {
        super(data);
        addItemType(ITEM_TYPE_LB, R.layout.item_home_lb);
        addItemType(ITEM_TYPE_MSG, R.layout.item_home_msg);
        addItemType(ITEM_TYPE_RMD, R.layout.item_home_product);
        addItemType(ITEM_TYPE_JRBK, R.layout.item_home_product);
        addItemType(ITEM_TYPE_JTYX, R.layout.item_home_jtyx);
        addItemType(ITEM_TYPE_YHZQ, R.layout.item_home_yhzq);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {
        if (item.gethItemType() == ITEM_TYPE_LB) {
            ItemHomeLbBinding binding = DataBindingUtil.bind(helper.itemView);
            Glide.with(mContext).load(item.getSingleImgId()).into(binding.lbIv);
            binding.setBean(item);
        } else if (item.gethItemType() == ITEM_TYPE_MSG) {
            ItemHomeMsgBinding binding = DataBindingUtil.bind(helper.itemView);
            binding.setBean(item);
        } else if (item.gethItemType() == ITEM_TYPE_RMD) {
            helper.addOnClickListener(R.id.more_iv);
            ItemHomeProductBinding binding = DataBindingUtil.bind(helper.itemView);
            binding.recycleView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mRmdProductAdapter = new HomeProductAdapter(item.getProductList());
            mRmdProductAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    HomeBean.ProductBean productBean = mRmdProductAdapter.getItem(position);
                    startProdutDetailActivity(productBean.getProductId());
                }
            });
            binding.recycleView.setAdapter(mRmdProductAdapter);
            binding.setBean(item);
        } else if (item.gethItemType() == ITEM_TYPE_JRBK) {
            helper.addOnClickListener(R.id.more_iv);
            ItemHomeProductBinding binding = DataBindingUtil.bind(helper.itemView);
            binding.recycleView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mJrbkProductAdapter = new HomeProductAdapter(item.getProductList());
            mJrbkProductAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    HomeBean.ProductBean productBean = mJrbkProductAdapter.getItem(position);
                    startProdutDetailActivity(productBean.getProductId());
                }
            });
            binding.recycleView.setAdapter(mJrbkProductAdapter);
            binding.setBean(item);
        } else if (item.gethItemType() == ITEM_TYPE_JTYX) {
            ItemHomeJtyxBinding binding = DataBindingUtil.bind(helper.itemView);
            binding.recycleViewTop.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mTopProductAdapter = new HomeProductAdapter(item.getJtyxProductList().get(0));
            mTopProductAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    HomeBean.ProductBean productBean = mTopProductAdapter.getItem(position);
                    startProdutDetailActivity(productBean.getProductId());
                }
            });
            binding.recycleViewTop.setAdapter(mTopProductAdapter);

            binding.recycleViewMiddle.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mMiddleProductAdapter = new HomeProductAdapter(item.getJtyxProductList().get(1));
            mMiddleProductAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Log.d("onItemClick", "onItemClick: ");
                    HomeBean.ProductBean productBean = mMiddleProductAdapter.getItem(position);
                    startProdutDetailActivity(productBean.getProductId());
                }
            });
            binding.recycleViewMiddle.setAdapter(mMiddleProductAdapter);

            binding.recycleViewBottom.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            mBottomProductAdapter = new HomeProductAdapter(item.getJtyxProductList().get(2));
            mBottomProductAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    HomeBean.ProductBean productBean = mBottomProductAdapter.getItem(position);
                    startProdutDetailActivity(productBean.getProductId());
                }
            });
            binding.recycleViewBottom.setAdapter(mBottomProductAdapter);

            binding.setBean(item);
        } else if (item.gethItemType() == ITEM_TYPE_YHZQ) {
            ItemHomeYhzqBinding binding = DataBindingUtil.bind(helper.itemView);
            Glide.with(mContext).load(item.getSingleImgId()).into(binding.yhzqIv);
            binding.setBean(item);
        }
    }

    private void startProdutDetailActivity(Integer productId) {
        if (productId > 0) {
            Bundle bundle = new Bundle();
            bundle.putInt("KEY_FAKE_DATA", productId);
            Starter.startGoodsDetailActivity(mContext, bundle);
        }
    }
}
