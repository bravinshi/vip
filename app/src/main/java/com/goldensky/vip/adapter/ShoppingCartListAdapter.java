package com.goldensky.vip.adapter;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.Observer;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.framework.ui.view.NumberButton;
import com.goldensky.framework.util.ImageUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.ShoppingCartGoodsBean;
import com.goldensky.vip.databinding.ItemShoppingCartBinding;
import com.goldensky.vip.helper.ShoppingCartHelper;
import com.goldensky.vip.transform.GlideRoundTransform;
import com.goldensky.vip.viewmodel.shoppingcart.ShoppingCartViewModel;
import com.google.android.material.shape.RoundedCornerTreatment;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.List;

public class ShoppingCartListAdapter extends BaseQuickAdapter<ShoppingCartGoodsBean, BaseViewHolder> {
    private NumberButton.OnCountChangeListener listener;
    public ShoppingCartListAdapter(@Nullable List<ShoppingCartGoodsBean> data) {
        super(R.layout.item_shopping_cart, data);
    }

    public void setOnCountChangeListener(NumberButton.OnCountChangeListener listener) {
        this.listener = listener;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ShoppingCartGoodsBean shoppingCartGoodsBean) {
        ItemShoppingCartBinding binding = DataBindingUtil.bind(baseViewHolder.itemView);
        Glide.with(getContext()).load(shoppingCartGoodsBean.getInventorypic()).apply(new RequestOptions().transform(new GlideRoundTransform(getContext(),5))).into(binding.goodsimageItemShoppingCart);
        binding.setBean(shoppingCartGoodsBean);
        binding.numberItemShoppingCart.setCount(shoppingCartGoodsBean.getPurchasenum());
        binding.numberItemShoppingCart.setMaxCount(shoppingCartGoodsBean.getInventorynum());
        binding.numberItemShoppingCart.setMinCount(1);
        String s = new DecimalFormat("#.00").format(shoppingCartGoodsBean.getCommodityoldprice());
        binding.priceItemShoppingCart.setText(changTvSize("¥"+s));
        binding.numberItemShoppingCart.setTag(shoppingCartGoodsBean);
        binding.numberItemShoppingCart.setCountChageListener(listener);
        binding.selectItemShoppingCart.setChecked(ShoppingCartHelper.getInstance().isGoodsChecked(shoppingCartGoodsBean.getShoppingcartid()));


    }

    private SpannableString changTvSize(String value) {
        SpannableString spannableString = new SpannableString(value);
        spannableString.setSpan(new RelativeSizeSpan(0.7f), value.indexOf("¥"), 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(0.7f), value.indexOf(".")+1, value.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}