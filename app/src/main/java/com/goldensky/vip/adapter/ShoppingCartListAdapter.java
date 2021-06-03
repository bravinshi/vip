package com.goldensky.vip.adapter;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.framework.ui.view.NumberButton;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.ShoppingCartGoodsBean;
import com.goldensky.vip.databinding.ItemShoppingCartBinding;
import com.goldensky.vip.helper.ShoppingCartHelper;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.List;

public class ShoppingCartListAdapter extends BaseQuickAdapter<ShoppingCartGoodsBean, BaseViewHolder> {
    private NumberButton.OnCountChangeListener listener;
    private TextView.OnEditorActionListener foucsListener;
    public ShoppingCartListAdapter(@Nullable List<ShoppingCartGoodsBean> data) {
        super(R.layout.item_shopping_cart, data);
    }

    public void setOnCountChangeListener(NumberButton.OnCountChangeListener listener) {
        this.listener = listener;
    }

    public void setFoucsListener(TextView.OnEditorActionListener foucsListener) {
        this.foucsListener = foucsListener;
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, ShoppingCartGoodsBean shoppingCartGoodsBean) {
        ItemShoppingCartBinding binding = DataBindingUtil.bind(baseViewHolder.itemView);
        Glide.with(getContext()).load(shoppingCartGoodsBean.getInventorypic()).into(binding.goodsimageItemShoppingCart);
        binding.setBean(shoppingCartGoodsBean);
        String s = new DecimalFormat("#.00").format(shoppingCartGoodsBean.getCommodityoldprice());
        binding.priceItemShoppingCart.setText(changTvSize("¥"+s));
        binding.selectItemShoppingCart.setChecked(ShoppingCartHelper.getInstance().isGoodsChecked(shoppingCartGoodsBean.getShoppingcartid()));
        binding.iscloseItemShoppingCart.setVisibility(View.GONE);
        if(shoppingCartGoodsBean.getOnshelfstatus()==0||shoppingCartGoodsBean.getAbandon()==1||shoppingCartGoodsBean.getCommodityisdel()==1){
            binding.iscloseItemShoppingCart.setVisibility(View.VISIBLE);
            binding.priceItemShoppingCart.setVisibility(View.GONE);
            binding.numberItemShoppingCart.setVisibility(View.GONE);
            binding.viewOverspreadShoppingCart.setVisibility(View.VISIBLE);
        }else {
            binding.iscloseItemShoppingCart.setVisibility(View.GONE);
            binding.priceItemShoppingCart.setVisibility(View.VISIBLE);
            binding.numberItemShoppingCart.setVisibility(View.VISIBLE);
            binding.numberItemShoppingCart.setCount(shoppingCartGoodsBean.getPurchasenum());
            binding.numberItemShoppingCart.setMinCount(1);
            binding.numberItemShoppingCart.setMaxCount(999);
            binding.numberItemShoppingCart.setTag(shoppingCartGoodsBean);
            binding.numberItemShoppingCart.setCountChageListener(listener);
            binding.numberItemShoppingCart.setFocusListener(foucsListener);
            binding.viewOverspreadShoppingCart.setVisibility(View.GONE);
        }

    }

    private SpannableString changTvSize(String value) {
        SpannableString spannableString = new SpannableString(value);
        spannableString.setSpan(new RelativeSizeSpan(0.7f), value.indexOf("¥"), 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new RelativeSizeSpan(0.7f), value.indexOf(".")+1, value.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

}
