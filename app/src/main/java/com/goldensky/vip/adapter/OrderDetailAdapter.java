package com.goldensky.vip.adapter;

import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.goldensky.framework.util.GsonUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.OrderListBean;
import com.goldensky.vip.databinding.ItemDetailOrderListBinding;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailAdapter extends BaseQuickAdapter<OrderListBean.OrderDetailList, BaseDataBindingHolder> {
    private Integer orderStatus;
    public OrderDetailAdapter(@Nullable List<OrderListBean.OrderDetailList> data) {
        super(R.layout.item_detail_order_list, data);
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    protected void convert(@NotNull BaseDataBindingHolder baseDataBindingHolder, OrderListBean.OrderDetailList orderDetailList) {
        ItemDetailOrderListBinding dataBinding = (ItemDetailOrderListBinding) baseDataBindingHolder.getDataBinding();
        if (orderStatus==5){
            if(orderDetailList.getCommodityisdel()==1||orderDetailList.getOnshelfstatus()==0){
                dataBinding.isdelItemDetailOrderlist.setVisibility(View.VISIBLE);
            }else {
                dataBinding.isdelItemDetailOrderlist.setVisibility(View.GONE);
            }
        }else {
            dataBinding.isdelItemDetailOrderlist.setVisibility(View.GONE);
        }

        dataBinding.countItemDetailOrderlist.setText("共"+orderDetailList.getPurchasenum()+"件");
        dataBinding.moneyItemDetailOrderlist.setText("￥"+orderDetailList.getCommodityoldprice());
        dataBinding.sizeItemDetailOrderlist.setText(getInventory(orderDetailList.getInventory()));
        dataBinding.nameItemDetailOrderlist.setText(orderDetailList.getCommodityname());
        Glide.with(getContext()).load(orderDetailList.getInventorypic()).apply(new RequestOptions().transform(new RoundedCorners(16))).into(dataBinding.ivItemDetailOrderlist);
    }

    protected String getInventory(String inventory){
        // 展示用规格
        JsonObject jsonObject = GsonUtils.fromJson(inventory, JsonObject.class);
        StringBuilder stringBuilder = new StringBuilder();
        List<String> keySet = new ArrayList<>(jsonObject.keySet());
        int size = keySet.size();
        for (int i = 0; i < size; i++) {
            String key = keySet.get(i);
            stringBuilder.append(jsonObject.get(key).getAsString());
            if (i != size - 1) {
                stringBuilder.append(";");
            }
        }
        return stringBuilder.toString();
    }
}
