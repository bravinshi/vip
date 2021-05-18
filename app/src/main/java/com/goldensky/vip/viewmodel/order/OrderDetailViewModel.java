package com.goldensky.vip.viewmodel.order;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.api.order.OrderService;
import com.goldensky.vip.base.error.FailCallback;
import com.goldensky.vip.bean.ExpressBean;
import com.goldensky.vip.bean.GetPaymentOrderResBean;
import com.goldensky.vip.bean.LogisticsBean;
import com.goldensky.vip.bean.LogisticsReqBean;
import com.goldensky.vip.bean.OrderDetailBean;
import com.goldensky.vip.bean.OrderNumberReqBean;
import com.goldensky.vip.bean.PaymentOrderReqBean;
import com.goldensky.vip.bean.UpdateOrderReqBean;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.google.gson.JsonObject;

public class OrderDetailViewModel extends PublicViewModel {
    public MutableLiveData<OrderDetailBean> orderDetailLive=new MutableLiveData<>();
    public MutableLiveData<Object> updateOrderLive=new MutableLiveData<>();
    public MutableLiveData<GetPaymentOrderResBean> paymentOrderLive = new MutableLiveData<>();
    public MutableLiveData<ExpressBean> expressLive = new MutableLiveData<>();
    public MutableLiveData<LogisticsBean> getLogisticsLive=new MutableLiveData<>();
    public void getOrderDetail(String orderNumber){
        OrderNumberReqBean bean = new OrderNumberReqBean();
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

    public void getPaymentOrder(PaymentOrderReqBean reqBean, FailCallback failCallback) {
        RetrofitAgent.create(OrderService.class)
                .getPaymentOrder(reqBean)
                .subscribe(new ToastNetObserver<GetPaymentOrderResBean>() {
                    @Override
                    public void onSuccess(GetPaymentOrderResBean data) {
                        paymentOrderLive.postValue(data);
                    }

                    @Override
                    public boolean onFail(NetResponse<GetPaymentOrderResBean> data) {
                        super.onFail(data);
                        if (failCallback != null) {
                            failCallback.onFail(data);
                        }

                        return true;
                    }
                });
    }

    public void  getExpress(String orderNumber){
        OrderNumberReqBean bean = new OrderNumberReqBean();
        bean.setOrdernumber(orderNumber);
        RetrofitAgent.create(OrderService.class)
                .getExpressNumber(bean)
                .subscribe(new ToastNetObserver<ExpressBean>() {
                    @Override
                    public void onSuccess(ExpressBean data) {
                             expressLive.postValue(data);
                    }
                });
    }
    public void getLogistics(LogisticsReqBean bean){
        RetrofitAgent.create(OrderService.class)
                .querTrackMap(bean)
                .subscribe(new ToastNetObserver<LogisticsBean>() {
                    @Override
                    public void onSuccess(LogisticsBean data) {
                        getLogisticsLive.postValue(data);
                    }
                });
    }
}
