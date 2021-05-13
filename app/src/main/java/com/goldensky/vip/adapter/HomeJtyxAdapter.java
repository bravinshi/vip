package com.goldensky.vip.adapter;

import android.graphics.Paint;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.framework.util.MathUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.CommodityBean;
import com.goldensky.vip.databinding.ItemHomeProductJrbkBinding;
import com.goldensky.vip.databinding.ItemHomeProductJtyxBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HomeJtyxAdapter extends BaseQuickAdapter<CommodityBean, BaseViewHolder> {

    public HomeJtyxAdapter(List<CommodityBean> data) {
        super(R.layout.item_home_product_jtyx, data);
    }
//
//    @Override
//    protected void convert(@NotNull BaseViewHolder baseViewHolder, CommodityBean commodityBean) {
//        ItemHomeProductJtyxBinding binding = DataBindingUtil.bind(baseViewHolder.itemView);
//        if (commodityBean != null) {
//            binding.productIv.setVisibility(View.VISIBLE);
//            binding.priceTv.setVisibility(View.VISIBLE);
//            binding.opriceTv.setVisibility(View.VISIBLE);
//            binding.productIv.setVisibility(View.VISIBLE);
//            binding.unitTv.setVisibility(View.VISIBLE);
////            Glide.with(getContext()).load(commodityBean.getCommodityIcon()).into(binding.productIv);
//            Glide.with(getContext()).load("http://49.234.85.95/data/jintianhezong/file/img/2021-05-12/DO1620782539367_big.jpg").into(binding.productIv);
//            binding.priceTv.setText(MathUtils.bigDecimalString(commodityBean.getCommodityPrice(), 2));
//            binding.opriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
//            if (commodityBean.getCommodityOldPrice() != null) {
//                binding.opriceTv.setText(MathUtils.bigDecimalString(commodityBean.getCommodityOldPrice(), 2));
//            }
//            binding.setBean(commodityBean);
//        } else {
//            binding.productIv.setVisibility(View.INVISIBLE);
//            binding.priceTv.setVisibility(View.INVISIBLE);
//            binding.opriceTv.setVisibility(View.INVISIBLE);
//            binding.productIv.setVisibility(View.INVISIBLE);
//            binding.unitTv.setVisibility(View.INVISIBLE);
//        }
//
//
//    }

    @Override
    public void onBindViewHolder(@NotNull BaseViewHolder holder, int position) {
        ItemHomeProductJtyxBinding binding = DataBindingUtil.bind(holder.itemView);
        CommodityBean commodityBean = getData().get(position);
        if (commodityBean != null) {
            binding.productIv.setVisibility(View.VISIBLE);
            binding.priceTv.setVisibility(View.VISIBLE);
            binding.opriceTv.setVisibility(View.VISIBLE);
            binding.productIv.setVisibility(View.VISIBLE);
            binding.unitTv.setVisibility(View.VISIBLE);
//            Glide.with(getContext()).load(commodityBean.getCommodityIcon()).into(binding.productIv);
            Glide.with(getContext()).load("http://49.234.85.95/data/jintianhezong/file/img/2021-05-12/DO1620782539367_big.jpg").into(binding.productIv);
            binding.priceTv.setText(MathUtils.bigDecimalString(commodityBean.getCommodityPrice(), 2));
            binding.opriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
            if (commodityBean.getCommodityOldPrice() != null) {
                binding.opriceTv.setText(MathUtils.bigDecimalString(commodityBean.getCommodityOldPrice(), 2));
            }
            binding.setBean(commodityBean);
        } else {
            binding.productIv.setVisibility(View.INVISIBLE);
            binding.priceTv.setVisibility(View.INVISIBLE);
            binding.opriceTv.setVisibility(View.INVISIBLE);
            binding.productIv.setVisibility(View.INVISIBLE);
            binding.unitTv.setVisibility(View.INVISIBLE);
        }

       if (position % 3 == 0) {
           binding.layoutTop.setBackgroundResource(R.drawable.shape_home_left);
       } else if (position % 3 == 1) {
           binding.layoutTop.setBackgroundResource(R.drawable.shape_home_middle);
       } else if (position % 3 == 2) {
           binding.layoutTop.setBackgroundResource(R.drawable.shape_home_right);
       }
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CommodityBean commodityBean) {

    }
}
