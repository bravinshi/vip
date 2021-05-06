package com.goldensky.vip.adapter;

import android.annotation.SuppressLint;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.databinding.ItemNormalGoodsBinding;
import com.goldensky.vip.entity.NormalGoodsEntity;
import com.goldensky.vip.helper.ImageLoaderHelper;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/6 14:21
 * 包名： com.goldensky.vip.adapter
 * 类说明：
 */
public class NormalGoodsAdapter extends BaseQuickAdapter<NormalGoodsEntity, BaseViewHolder> {
    public NormalGoodsAdapter() {
        super(R.layout.item_normal_goods);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void convert(BaseViewHolder baseViewHolder, NormalGoodsEntity normalGoodsEntity) {
        ItemNormalGoodsBinding binding = DataBindingUtil.bind(baseViewHolder.itemView);

        ImageLoaderHelper.loadImage(binding.ivMain, normalGoodsEntity.getImage());
        binding.tvTitle.setText(normalGoodsEntity.getTitle());
        binding.tvPrice.setText("￥" + normalGoodsEntity.getPrice());
    }
}
