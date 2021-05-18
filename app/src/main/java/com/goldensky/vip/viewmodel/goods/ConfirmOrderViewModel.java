package com.goldensky.vip.viewmodel.goods;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.api.goods.GoodsService;
import com.goldensky.vip.api.order.OrderService;
import com.goldensky.vip.base.error.FailCallback;
import com.goldensky.vip.bean.AddOrderReqBean;
import com.goldensky.vip.bean.GetPaymentOrderResBean;
import com.goldensky.vip.bean.PaymentOrderReqBean;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.google.gson.JsonObject;

import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/14 14:23
 * 包名： com.goldensky.vip.viewmodel.goods
 * 类说明：
 */
public class ConfirmOrderViewModel extends PublicViewModel {

    public MutableLiveData<List<String>> submitOrderLive = new MutableLiveData<>();
    public MutableLiveData<GetPaymentOrderResBean> paymentOrderLive = new MutableLiveData<>();

    public void addOrder(AddOrderReqBean addOrderReqBean, FailCallback failCallback) {
        RetrofitAgent.create(GoodsService.class)
                .addOrder(addOrderReqBean)
                .subscribe(new ToastNetObserver<List<String>>() {
                    @Override
                    public void onSuccess(List<String> data) {
                        submitOrderLive.postValue(data);
                    }

                    @Override
                    public boolean onFail(NetResponse<List<String>> data) {
                        super.onFail(data);
                        if (failCallback != null) {
                            failCallback.onFail(data);
                        }

                        return true;
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
}
