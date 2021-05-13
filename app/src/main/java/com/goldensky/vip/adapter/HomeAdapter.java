package com.goldensky.vip.adapter;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.bean.CommodityBean;
import com.goldensky.vip.bean.HomeBean;
import com.goldensky.vip.databinding.ItemHomeJtyxBinding;
import com.goldensky.vip.databinding.ItemHomeLbBinding;
import com.goldensky.vip.databinding.ItemHomeProductBinding;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.youth.banner.config.IndicatorConfig;
import com.youth.banner.indicator.CircleIndicator;
import com.youth.banner.util.BannerUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.goldensky.vip.activity.goods.GoodsDetailActivity.KEY_GOODS_ID;

public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeBean, BaseViewHolder> {

    public static final int ITEM_TYPE_LB = 1; //轮播
    public static final int ITEM_TYPE_MSG = 2; //消息
    public static final int ITEM_TYPE_RMD = 3; //为你推荐
    public static final int ITEM_TYPE_JRBK = 4; //今日爆款
    public static final int ITEM_TYPE_YHZQ = 5; //优惠专区
    public static final int ITEM_TYPE_JTYX = 6; //金天优选

    private BannerImageAdapter mBannerImageAdapter;
    private HomeRmdAdapter mHomeRmdAdapter;
    private HomeJrbkAdapter mHomeJrbkAdapter;

    private HomeJtyxAdapter mTopJtyxAdapter;
    private HomeJtyxAdapter mMiddleJtyxAdapter;
    private HomeJtyxAdapter mBottomJtyxAdapter;

    private LinearLayoutManager mTopLineLayoutManager;
    private LinearLayoutManager mMiddleLineLayoutManager;
    private LinearLayoutManager mBottomLineLayoutManager;



    public HomeAdapter(List<HomeBean> data) {
        super(data);
        addItemType(ITEM_TYPE_LB, R.layout.item_home_lb);
        addItemType(ITEM_TYPE_RMD, R.layout.item_home_product);
        addItemType(ITEM_TYPE_JRBK, R.layout.item_home_product);
        addItemType(ITEM_TYPE_JTYX, R.layout.item_home_jtyx);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeBean homeBean) {

        switch (homeBean.getItemType()) {
            case ITEM_TYPE_LB:
                ItemHomeLbBinding binding = DataBindingUtil.bind(baseViewHolder.itemView);
                if (mBannerImageAdapter == null) {
                    mBannerImageAdapter = new BannerImageAdapter(homeBean.getLbList()) {
                        @Override
                        public void onBindView(BannerViewHolder holder, String data, int position, int size) {
                            holder.imageView.setImageResource(R.mipmap.home_lb_banner);
                        }

                    };
                    binding.bannerLb.setAdapter(mBannerImageAdapter);
                    binding.bannerLb.setIndicator(new CircleIndicator(getContext()))
                            .setIndicatorNormalColor(0xFFFFFFFF)
                            .setIndicatorSelectedColor(0xFFE5392F)
                            .setIndicatorSelectedWidth((int) BannerUtils.dp2px(6))
                            .setIndicatorNormalWidth((int) BannerUtils.dp2px(6))
                            .setLoopTime(4000)
                            .setBannerRound(8)
                            .setBannerGalleryEffect(0, 0, 15, 1)
                            .setIndicatorMargins(new IndicatorConfig.Margins(0, 0, 0, (int) BannerUtils.dp2px(8)));
                } else {
                    binding.bannerLb.setDatas(homeBean.getLbList());
                }
                break;
            case ITEM_TYPE_RMD:
                ItemHomeProductBinding rmdBing = DataBindingUtil.bind(baseViewHolder.itemView);
                rmdBing.titleTv.setText(homeBean.getItemTitle());
                if (mHomeRmdAdapter == null) {
                    mHomeRmdAdapter = new HomeRmdAdapter(homeBean.getCommodityBeanList());
                    mHomeRmdAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                            startGoodsDetail((List<CommodityBean>) adapter.getData(), position);
                        }
                    });
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    rmdBing.recycleView.setLayoutManager(layoutManager);
                    rmdBing.recycleView.setAdapter(mHomeRmdAdapter);
                } else {
                    mHomeRmdAdapter.setNewInstance(homeBean.getCommodityBeanList());
                    mHomeRmdAdapter.notifyDataSetChanged();
                }
                break;
            case ITEM_TYPE_JRBK:
                ItemHomeProductBinding jrbkBing = DataBindingUtil.bind(baseViewHolder.itemView);
                jrbkBing.titleTv.setText(homeBean.getItemTitle());
                if (mHomeJrbkAdapter == null) {
                    mHomeJrbkAdapter = new HomeJrbkAdapter(homeBean.getCommodityBeanList());
                    mHomeJrbkAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                            startGoodsDetail((List<CommodityBean>) adapter.getData(), position);
                        }
                    });
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    jrbkBing.recycleView.setLayoutManager(layoutManager);
                    jrbkBing.recycleView.setAdapter(mHomeJrbkAdapter);
                } else {
                    mHomeJrbkAdapter.setNewInstance(homeBean.getCommodityBeanList());
                    mHomeJrbkAdapter.notifyDataSetChanged();
                }
                break;
            case ITEM_TYPE_JTYX:
                ItemHomeJtyxBinding jtyxBing = DataBindingUtil.bind(baseViewHolder.itemView);
                jtyxBing.titleTv.setText(homeBean.getItemTitle());

                if (mTopLineLayoutManager == null) {
                    mTopLineLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    jtyxBing.rvTop.setLayoutManager(mTopLineLayoutManager);
                }

                if (mMiddleLineLayoutManager == null) {
                    mMiddleLineLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    jtyxBing.rvMiddle.setLayoutManager(mMiddleLineLayoutManager);
                }

                if (mBottomLineLayoutManager == null) {
                    mBottomLineLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    jtyxBing.rvBottom.setLayoutManager(mBottomLineLayoutManager);
                }


//                List<CommodityBean> commodityBeans = homeBean.getCommodityBeanList();
                List<CommodityBean> tempBeans = new ArrayList<>();
                tempBeans.addAll(homeBean.getCommodityBeanList());
                List<CommodityBean> topBeans = new ArrayList<>();
                List<CommodityBean> middleBeans = new ArrayList<>();
                List<CommodityBean> bottomBeans = new ArrayList<>();
                if (tempBeans.size() <= 3) {
                    jtyxBing.rvMiddle.setVisibility(View.INVISIBLE);
                    jtyxBing.rvBottom.setVisibility(View.INVISIBLE);
                    topBeans.addAll(homeBean.getCommodityBeanList());
                    if (topBeans.size() == 1) {
                        topBeans.add(null);
                        topBeans.add(null);
                    } else if (topBeans.size() == 2) {
                        topBeans.add(null);
                    }
                } else if (tempBeans.size() <= 6) {
                    jtyxBing.rvMiddle.setVisibility(View.VISIBLE);
                    jtyxBing.rvBottom.setVisibility(View.INVISIBLE);

                    for (int i = 0; i < 3; i++) {
                        CommodityBean bean = tempBeans.get(0);
                        topBeans.add(bean);
                        tempBeans.remove(bean);
                    }

                    middleBeans.addAll(tempBeans);
                    if (middleBeans.size() == 1) {
                        middleBeans.add(null);
                        middleBeans.add(null);
                    } else if (topBeans.size() == 2) {
                        middleBeans.add(null);
                    }

                } else {
                    jtyxBing.rvMiddle.setVisibility(View.VISIBLE);
                    jtyxBing.rvBottom.setVisibility(View.VISIBLE);

                    for (int i = 0; i < 3; i++) {
                        CommodityBean bean = tempBeans.get(0);
                        topBeans.add(bean);
                        tempBeans.remove(bean);
                    }

                    for (int i = 0; i < 3; i++) {
                        CommodityBean bean = tempBeans.get(0);
                        middleBeans.add(bean);
                        tempBeans.remove(bean);
                    }

                    bottomBeans.addAll(tempBeans);
                    if (bottomBeans.size() == 1) {
                        bottomBeans.add(null);
                        bottomBeans.add(null);
                    } else if (topBeans.size() == 2) {
                        bottomBeans.add(null);
                    }
                }

                if (mTopJtyxAdapter == null) {
                    mTopJtyxAdapter = new HomeJtyxAdapter(topBeans);
                    mTopJtyxAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                            startGoodsDetail((List<CommodityBean>) adapter.getData(), position);
                        }
                    });
                    jtyxBing.rvTop.setAdapter(mTopJtyxAdapter);
                } else {
                    mTopJtyxAdapter.setNewInstance(topBeans);
                    mTopJtyxAdapter.notifyDataSetChanged();
                }

                if (mMiddleJtyxAdapter == null) {
                    mMiddleJtyxAdapter = new HomeJtyxAdapter(middleBeans);
                    mMiddleJtyxAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                            startGoodsDetail((List<CommodityBean>) adapter.getData(), position);
                        }
                    });
                    jtyxBing.rvMiddle.setAdapter(mMiddleJtyxAdapter);
                } else {
                    mMiddleJtyxAdapter.setNewInstance(middleBeans);
                    mMiddleJtyxAdapter.notifyDataSetChanged();
                }

                if (mBottomJtyxAdapter == null) {
                    mBottomJtyxAdapter = new HomeJtyxAdapter(bottomBeans);
                    mBottomJtyxAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                            startGoodsDetail((List<CommodityBean>) adapter.getData(), position);
                        }
                    });
                    jtyxBing.rvBottom.setAdapter(mBottomJtyxAdapter);
                } else {
                    mBottomJtyxAdapter.setNewInstance(bottomBeans);
                    mBottomJtyxAdapter.notifyDataSetChanged();
                }
                break;
        }
    }


    private void startGoodsDetail(List<CommodityBean> beans, int position) {
        if (beans != null && beans.size() > position) {
            CommodityBean bean = beans.get(position);
            if (bean != null) {
                Bundle bundle = new Bundle();
                bundle.putInt(KEY_GOODS_ID, bean.getCommodityId());
                Starter.startGoodsDetailActivity(getContext(), bundle);
            }
        }
    }

}
