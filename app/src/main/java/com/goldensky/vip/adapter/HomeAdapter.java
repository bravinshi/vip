package com.goldensky.vip.adapter;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.HomeBean;
import com.goldensky.vip.databinding.ItemHomeLbBinding;
import com.youth.banner.config.BannerConfig;
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

    private HomeProductAdapter mRmdProductAdapter;
    private HomeProductAdapter mJtyxProductAdapter;
    private HomeProductAdapter mJrbkProductAdapter;
    private BannerImageAdapter mBannerImageAdapter;

    public HomeAdapter(List<HomeBean> data) {
        super(data);
        addItemType(ITEM_TYPE_LB, R.layout.item_home_lb);
        addItemType(ITEM_TYPE_RMD, R.layout.item_home_product);
        addItemType(ITEM_TYPE_JRBK, R.layout.item_home_product);
        addItemType(ITEM_TYPE_JTYX, R.layout.item_home_jtyx);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, HomeBean homeBean) {

        if (homeBean.getItemType() == ITEM_TYPE_LB) {
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
        }
    }
}
