package com.goldensky.vip.adapter;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.bean.OrderListBean;
import com.goldensky.vip.databinding.ItemOrderListBinding;
import com.goldensky.vip.utils.NoScrollStaggeredGridLayoutManager;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.List;

public class OrderListAdapter extends BaseQuickAdapter<OrderListBean, BaseDataBindingHolder> {

    public OrderListAdapter(@Nullable List<OrderListBean> data) {
        super(R.layout.item_order_list, data);
    }


    @Override
    protected void convert(@NotNull BaseDataBindingHolder baseDataBindingHolder, OrderListBean orderListBean) {
        ItemOrderListBinding dataBinding = (ItemOrderListBinding) baseDataBindingHolder.getDataBinding();
//        0:未付款 1:待发货  2:待收货 3:已完成 4:已关闭 5:已取消'
        String[] orderStatus = {"待付款","待发货","待收货","已完成","已关闭","已取消"};
        dataBinding.statuItemOrderlist.setText(orderStatus[orderListBean.getOrderstatus()]);
        dataBinding.numberItemOrderlist.setText("订单编号:"+orderListBean.getOrdernumber());
        dataBinding.countItemOrderlist.setText("共"+orderListBean.getOrderDetailList().size()+"件");
        String price = new DecimalFormat("#.00").format(orderListBean.getOrderprice());
        dataBinding.sumMoneyItemOrderlist.setText("￥"+price);
        switch (orderListBean.getOrderstatus()){
            case 0:
                dataBinding.btnGrayItemOrderlist.setVisibility(View.GONE);
                dataBinding.btnRedItemOrderlist.setVisibility(View.VISIBLE);
                dataBinding.btnRedItemOrderlist.setText(getContext().getResources().getText(R.string.text_go_pay));
                break;
            case 2:
                dataBinding.btnGrayItemOrderlist.setVisibility(View.VISIBLE);
                dataBinding.btnRedItemOrderlist.setVisibility(View.VISIBLE);
                dataBinding.btnGrayItemOrderlist.setText(getContext().getResources().getText(R.string.text_check_logistics));
                dataBinding.btnRedItemOrderlist.setText(getContext().getResources().getText(R.string.text_confirm_receipt));
                break;
            default:
                dataBinding.btnGrayItemOrderlist.setVisibility(View.GONE);
                dataBinding.btnRedItemOrderlist.setVisibility(View.GONE);
                break;
        }
        dataBinding.rvItemOrderlist.setLayoutManager(new NoScrollStaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
        OrderDetailAdapter adapter = new OrderDetailAdapter(orderListBean.getOrderDetailList());
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("orderNumber", orderListBean.getOrdernumber());
                bundle.putInt("orderType", orderListBean.getOrderstatus());
                Starter.startOrderDetailActivity(getContext(), bundle);
            }
        });
        dataBinding.rvItemOrderlist.setAdapter(adapter);
    }
}
