package com.goldensky.vip.viewmodel.order;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.api.order.OrderService;
import com.goldensky.vip.bean.OrderDetailBean;
import com.goldensky.vip.bean.OrderDetailReqBean;
import com.goldensky.vip.bean.UpdateOrderReqBean;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class OrderDetailViewModel extends PublicViewModel {
    public MutableLiveData<OrderDetailBean> orderDetailLive=new MutableLiveData<>();
    public MutableLiveData<Object> updateOrderLive=new MutableLiveData<>();

    public void getOrderDetail(String orderNumber){
        OrderDetailReqBean bean = new OrderDetailReqBean();
        bean.setOrdernumber(orderNumber);
        RetrofitAgent.create(OrderService.class)
                .getOrderDetail(bean)
                .subscribe(new ToastNetObserver<OrderDetailBean>() {
                    @Override
                    public void onSuccess(OrderDetailBean data) {
                        orderDetailLive.postValue(data);
                    }
                });
    }
    public void updateOrder(String ordernumber,int orderstatus){
        UpdateOrderReqBean bean = new UpdateOrderReqBean();
        bean.setOrdernumber(ordernumber);
        bean.setOrderstatus(orderstatus);
        RetrofitAgent.create(OrderService.class)
                .updateOrder(bean)
                .subscribe(new ToastNetObserver<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        updateOrderLive.postValue(data);
                    }
                });
    }
}
