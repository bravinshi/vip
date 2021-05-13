package com.goldensky.vip.adapter;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
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

import java.util.List;

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
    private HomeJtyxAdapter mHomeJtyxAdapter;
    private FlexboxLayoutManager mFlexboxLayoutManager;

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

                if (mFlexboxLayoutManager == null) {
                    mFlexboxLayoutManager = new FlexboxLayoutManager(getContext());
                    mFlexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
                    mFlexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
                    jtyxBing.recycleJtyx.setLayoutManager(mFlexboxLayoutManager);
                }

                if (mHomeJtyxAdapter == null) {
                    mHomeJtyxAdapter = new HomeJtyxAdapter(homeBean.getCommodityBeanList());
                    jtyxBing.recycleJtyx.setAdapter(mHomeJtyxAdapter);
                } else {
                    mHomeJtyxAdapter.setNewInstance(homeBean.getCommodityBeanList());
                    mHomeJtyxAdapter.notifyDataSetChanged();
                }
                break;
        }

    }
}
