package com.goldensky.vip.viewmodel.order;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.api.order.OrderService;
import com.goldensky.vip.bean.GetOrderListReqBean;
import com.goldensky.vip.bean.OrderListBean;
import com.goldensky.vip.bean.UpdateOrderReqBean;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.List;

public class OrderListViewModel extends PublicViewModel {
    public MutableLiveData<List<OrderListBean>> getOrderListLive = new MutableLiveData<>();
    public MutableLiveData<Object> updateOrderLive=new MutableLiveData<>();

    public void getOrderList(String userId, Integer orderStatus){
        GetOrderListReqBean bean = new GetOrderListReqBean(orderStatus, userId);
        RetrofitAgent.create(OrderService.class)
                .getOrderList(bean)
                .subscribe(new ToastNetObserver<List<OrderListBean>>(){

                    @Override
                    public void onSuccess(List<OrderListBean> data) {
                        getOrderListLive.postValue(data);
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

