package com.goldensky.vip.adapter;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.framework.util.MathUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.CommodityBean;
import com.goldensky.vip.databinding.ItemHomeProductRmdBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class HomeRmdAdapter extends BaseQuickAdapter<CommodityBean, BaseViewHolder> {

    public HomeRmdAdapter(List<CommodityBean> data) {
        super(R.layout.item_home_product_rmd, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CommodityBean commodityBean) {
        ItemHomeProductRmdBinding binding = DataBindingUtil.bind(baseViewHolder.itemView);
        Glide.with(getContext()).load(commodityBean.getCommodityIcon()).into(binding.productIv);
        binding.priceTv.setText(MathUtils.bigDecimalString(commodityBean.getCommodityPrice(), 2));
        binding.setBean(commodityBean);
    }
}
