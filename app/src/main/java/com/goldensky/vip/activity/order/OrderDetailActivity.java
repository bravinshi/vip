package com.goldensky.vip.activity.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.OrderDetailBean;
import com.goldensky.vip.bean.OrderListBean;
import com.goldensky.vip.databinding.ActivityOrderDetailBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.order.OrderDetailViewModel;

import java.io.Serializable;

public class OrderDetailActivity extends BaseActivity<ActivityOrderDetailBinding, OrderDetailViewModel> {
    private OrderListBean order;
    private String[] orderStatus = {"未付款","待发货","待收货","已完成","已关闭","已取消"};
    private OrderDetailBean orderDetail=new OrderDetailBean();
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        Intent intent = getIntent();
        order = (OrderListBean) intent.getSerializableExtra("order");
        mViewModel.getOrderDetail(order.getOrdernumber());
        mBinding.setOrderBean(order);
        mBinding.tvStatuOrderdetail.setText(orderStatus[order.getOrderstatus()]);
        mBinding.setDetailBean(orderDetail);
    }

    @Override
    public void observe() {
        mViewModel.orderDetailLive.observe(this, new Observer<OrderDetailBean>() {
            @Override
            public void onChanged(OrderDetailBean orderDetailBean) {
                orderDetail=orderDetailBean;
                mBinding.notifyChange();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }
}