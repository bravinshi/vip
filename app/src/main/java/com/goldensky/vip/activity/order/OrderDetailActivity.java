package com.goldensky.vip.activity.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.OrderDetailListAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.OrderDetailBean;
import com.goldensky.vip.bean.OrderListBean;
import com.goldensky.vip.databinding.ActivityOrderDetailBinding;
import com.goldensky.vip.event.ChangeOrderStatusEvent;
import com.goldensky.vip.utils.NoScrollStaggeredGridLayoutManager;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.order.OrderDetailViewModel;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends BaseActivity<ActivityOrderDetailBinding, OrderDetailViewModel> implements View.OnClickListener {
    private String orderNumber;
    private String[] orderStatus = {"未付款","待发货","待收货","已完成","已关闭","已取消"};
    private int orderType;
    private OrderDetailBean orderDetail=new OrderDetailBean(1);
    private OrderDetailListAdapter adapter;
    private List<OrderDetailBean.OrderDetailListDTO> list=new ArrayList<>();
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        Intent intent = getIntent();
        orderNumber = intent.getStringExtra("orderNumber");
        orderType = intent.getIntExtra("orderType", 0);
        mBinding.tvStatuOrderdetail.setText(orderStatus[orderType]);
        mBinding.setDetailBean(orderDetail);
        mBinding.rvOrderdetail.setLayoutManager(new NoScrollStaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        adapter=new OrderDetailListAdapter(list,orderType);
        mBinding.rvOrderdetail.setAdapter(adapter);
        mBinding.setListener(this);
        mBinding.topBarOrderdetail.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter.addChildClickViewIds(new int[]{R.id.go_evaluated_item_order_detail});
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(view.getId()==R.id.go_evaluated_item_order_detail){
                    Bundle bundle = new Bundle();
                    bundle.putString("PRODUCT_URL_KEY",orderDetail.getOrderDetailList().get(position).getInventorypic());
                    bundle.putString("PRODUCT_SECOND_ORDERID_KEY",orderDetail.getOrderDetailList().get(position).getSecondorderid());
                    Starter.startOrderCommentActivity(OrderDetailActivity.this,bundle);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.getOrderDetail(orderNumber);
    }

    @Override
    public void observe() {
        mViewModel.orderDetailLive.observe(this, new Observer<OrderDetailBean>() {
            @Override
            public void onChanged(OrderDetailBean orderDetailBean) {
                list.clear();
                list.addAll(orderDetailBean.getOrderDetailList());
                adapter.notifyDataSetChanged();
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
                        mBinding.ivStatuOrderdetail.setImageResource(R.mipmap.img_wddd_dfk);
                        break;
                    case 1:
                    case 2:
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.btnMethedLeft.setVisibility(View.GONE);
                        mBinding.btnMethedRight.setText(getResources().getText(R.string.text_confirm_receipt));
                        mBinding.ivStatuOrderdetail.setImageResource(R.mipmap.img_wddd_dsh);
                        break;
                    case 3:
                    case 4:
                    case 5:
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.ivStatuOrderdetail.setImageResource(R.mipmap.img_wddd_ywc);
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
                        mBinding.ivStatuOrderdetail.setImageResource(R.mipmap.img_wddd_ywc);
                        mBinding.clLogisticsOrderdetail.setVisibility(View.GONE);
                        mBinding.cancelOrPaymentOrderdetail.setVisibility(View.GONE);
                        break;
                    case 1:
                    case 2:
                        orderType=3;
                        mBinding.tvStatuOrderdetail.setText(orderStatus[orderType]);
                        mBinding.ivStatuOrderdetail.setImageResource(R.mipmap.img_wddd_ywc);
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.btnMethedLeft.setVisibility(View.GONE);
                        mBinding.btnMethedRight.setText(getResources().getText(R.string.text_confirm_receipt));
                        break;
                }
                EventBus.getDefault().post(new ChangeOrderStatusEvent(true));
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