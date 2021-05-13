package com.goldensky.vip.viewmodel.order;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.api.order.OrderService;
import com.goldensky.vip.bean.OrderDetailBean;
import com.goldensky.vip.bean.OrderDetailReqBean;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class OrderDetailViewModel extends PublicViewModel {
    public MutableLiveData<OrderDetailBean> orderDetailLive=new MutableLiveData<>();

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
}
