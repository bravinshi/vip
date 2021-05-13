package com.goldensky.vip.activity.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.OrderDetailBean;
import com.goldensky.vip.bean.OrderListBean;
import com.goldensky.vip.databinding.ActivityOrderDetailBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.order.OrderDetailViewModel;

import java.io.Serializable;

public class OrderDetailActivity extends BaseActivity<ActivityOrderDetailBinding, OrderDetailViewModel> implements View.OnClickListener {
    private String orderNumber;
    private String[] orderStatus = {"未付款","待发货","待收货","已完成","已关闭","已取消"};
    private int orderType;
    private OrderDetailBean orderDetail=new OrderDetailBean(1);
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        Intent intent = getIntent();
        orderNumber = intent.getStringExtra("orderNumber");
        orderType = intent.getIntExtra("orderType", 0);
        mViewModel.getOrderDetail(orderNumber);
        mBinding.tvStatuOrderdetail.setText(orderStatus[orderType]);
        mBinding.setDetailBean(orderDetail);

        mBinding.setListener(this);
    }

    @Override
    public void observe() {
        mViewModel.orderDetailLive.observe(this, new Observer<OrderDetailBean>() {
            @Override
            public void onChanged(OrderDetailBean orderDetailBean) {
                orderDetail.setCity(orderDetailBean.getCity());
                orderDetail.setCreatetime(orderDetailBean.getCreatetime());
                orderDetail.setOrdernumber(orderDetailBean.getOrdernumber());
                orderDetail.setOrderprice(orderDetailBean.getOrderpriceD());
                orderDetail.setPickuptype(orderDetailBean.getPickuptypeI());
                orderDetail.setPostage(orderDetailBean.getPostageD());
                orderDetail.setProvince(orderDetailBean.getProvince());
                orderDetail.setUseraddress(orderDetailBean.getUseraddress());
                orderDetail.setUseraddressname(orderDetailBean.getUseraddressname());
                orderDetail.setUseraddressphone(orderDetailBean.getUseraddressphone());
                orderDetail.setOrderDetailList(orderDetailBean.getOrderDetailList());
                orderDetail.setArea( orderDetailBean.getArea());
                orderDetail.notifyChange();
                switch (orderType){
                    case 0:
                        mBinding.clLogisticsOrderdetail.setVisibility(View.GONE);
                        mBinding.btnMethedRight.setText(getResources().getText(R.string.text_go_pay));
                        mBinding.btnMethedLeft.setText(getResources().getText(R.string.text_cancel_order));
                        mBinding.tvStatuOrderdetail.setBackgroundResource(R.mipmap.img_wddd_dfk);
                        break;
                    case 1:
                    case 2:
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.btnMethedLeft.setVisibility(View.GONE);
                        mBinding.btnMethedRight.setText(getResources().getText(R.string.text_confirm_receipt));
                        mBinding.tvStatuOrderdetail.setBackgroundResource(R.mipmap.img_wddd_dsh);
                        break;
                    case 3:
                    case 4:
                    case 5:
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.tvStatuOrderdetail.setBackgroundResource(R.mipmap.img_wddd_ywc);
                        mBinding.clLogisticsOrderdetail.setVisibility(View.GONE);
                        mBinding.cancelOrPaymentOrderdetail.setVisibility(View.GONE);
                        break;
                }
            }
        });
        mViewModel.updateOrderLive.observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                switch (orderType){
                    case 0:
                        orderType=5;
                        mBinding.tvStatuOrderdetail.setText(orderStatus[orderType]);
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.tvStatuOrderdetail.setBackgroundResource(R.mipmap.img_wddd_ywc);
                        mBinding.clLogisticsOrderdetail.setVisibility(View.GONE);
                        mBinding.cancelOrPaymentOrderdetail.setVisibility(View.GONE);
                        break;
                    case 1:
                    case 2:
                        orderType=3;
                        mBinding.tvStatuOrderdetail.setText(orderStatus[orderType]);
                        mBinding.tvStatuOrderdetail.setBackgroundResource(R.mipmap.img_wddd_ywc);
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.btnMethedLeft.setVisibility(View.GONE);
                        mBinding.btnMethedRight.setText(getResources().getText(R.string.text_confirm_receipt));
                        break;
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_methed_right:
                switch (orderType){
                    case 0:

                        break;
                    case 2:
                    case 3:
                        mViewModel.updateOrder(orderDetail.getOrdernumber(),3);
                        break;
                }
                break;
            case R.id.btn_methed_left:
                mViewModel.updateOrder(orderDetail.getOrdernumber(),5);
                break;
        }
    }
}