package com.goldensky.vip.adapter;

import android.graphics.Paint;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.HomeBean;
import com.goldensky.vip.databinding.ItemHomeProductJrbkBinding;
import com.goldensky.vip.databinding.ItemHomeProductJtyxBinding;
import com.goldensky.vip.databinding.ItemHomeProductRmdBinding;

import java.util.List;

public class HomeProductAdapter extends BaseMultiItemQuickAdapter<HomeBean.ProductBean, BaseViewHolder> {

    public final static int TYPE_RMD_PD = 1;
    public final static int TYPE_JRBK_PD = 2;
    public final static int TYPE_JTYX_PD = 3;

    public HomeProductAdapter(List<HomeBean.ProductBean> data) {
        super(data);
        addItemType(TYPE_RMD_PD, R.layout.item_home_product_rmd);
        addItemType(TYPE_JRBK_PD, R.layout.item_home_product_jrbk);
        addItemType(TYPE_JTYX_PD, R.layout.item_home_product_jtyx);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.ProductBean item) {
        if (item.getProductType() == TYPE_RMD_PD) {
            ItemHomeProductRmdBinding binding = DataBindingUtil.bind(helper.itemView);
            Glide.with(mContext).load(item.getImgId()).into(binding.productIv);
            binding.setBean(item);
        } else if (item.getProductType() == TYPE_JRBK_PD) {
            ItemHomeProductJrbkBinding binding = DataBindingUtil.bind(helper.itemView);
            Glide.with(mContext).load(item.getImgId()).into(binding.productIv);
            binding.setBean(item);
        } else if (item.getProductType() == TYPE_JTYX_PD) {
            ItemHomeProductJtyxBinding binding = DataBindingUtil.bind(helper.itemView);
            Glide.with(mContext).load(item.getImgId()).into(binding.productIv);
            binding.opriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            binding.setBean(item);
        }
    }
}
