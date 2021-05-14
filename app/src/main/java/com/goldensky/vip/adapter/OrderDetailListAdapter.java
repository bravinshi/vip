package com.goldensky.vip.adapter;

import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.OrderDetailBean;
import com.goldensky.vip.databinding.ItemOrderDetailBinding;
import com.goldensky.vip.utils.GlideRoundTransform;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class OrderDetailListAdapter extends BaseQuickAdapter<OrderDetailBean.OrderDetailListDTO, BaseDataBindingHolder> {
    private int orderStatus;
    public OrderDetailListAdapter(@Nullable List<OrderDetailBean.OrderDetailListDTO> data,int orderStatus) {
        super(R.layout.item_order_detail, data);
        this.orderStatus=orderStatus;
    }

    @Override
    protected void convert(@NotNull BaseDataBindingHolder baseDataBindingHolder, OrderDetailBean.OrderDetailListDTO orderDetailListDTO) {
        ItemOrderDetailBinding dataBinding = (ItemOrderDetailBinding) baseDataBindingHolder.getDataBinding();
        dataBinding.setBean(orderDetailListDTO);
        Glide.with(getContext()).load(orderDetailListDTO.getInventorypic()).apply(new RequestOptions().transform(new GlideRoundTransform(getContext(),16))).into(dataBinding.ivItemOrderDetail);
        if(orderDetailListDTO.getIsevaluate()==0){
           switch (orderStatus){
               case 3:
               case 4:
                   dataBinding.goEvaluatedItemOrderDetail.setVisibility(View.VISIBLE);
                   break;
               default:
                   dataBinding.goEvaluatedItemOrderDetail.setVisibility(View.GONE);
                   break;
           }
        }else {
            dataBinding.goEvaluatedItemOrderDetail.setVisibility(View.GONE);
        }
    }
}
