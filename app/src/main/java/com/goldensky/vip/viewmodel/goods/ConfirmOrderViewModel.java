package com.goldensky.vip.viewmodel.goods;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.api.goods.GoodsService;
import com.goldensky.vip.bean.AddOrderReqBean;
import com.goldensky.vip.bean.CommodityBean;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.google.gson.JsonObject;

import org.json.JSONObject;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/14 14:23
 * 包名： com.goldensky.vip.viewmodel.goods
 * 类说明：
 */
public class ConfirmOrderViewModel extends PublicViewModel {

    public MutableLiveData<JsonObject> submitOrderLive = new MutableLiveData<>();

    public void addOrder(AddOrderReqBean addOrderReqBean) {
        RetrofitAgent.create(GoodsService.class)
                .addOrder(addOrderReqBean)
                .subscribe(new ToastNetObserver<JsonObject>() {
                    @Override
                    public void onSuccess(JsonObject data) {
                        submitOrderLive.postValue(data);
                    }
                });
    }
}
