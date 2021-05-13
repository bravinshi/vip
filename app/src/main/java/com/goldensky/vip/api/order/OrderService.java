package com.goldensky.vip.api.order;


import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.base.net.NetParams;
import com.goldensky.vip.bean.CommentProductBean;
import com.goldensky.vip.bean.CommentReqBean;
import com.goldensky.vip.bean.PaymentOrderReqBean;
import com.google.gson.JsonObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface OrderService {
    @POST("/goldendays-order/vipOrder/vipEvaluate")
    Observable<NetResponse<Object>> orderComment(@Body CommentReqBean reqBean);

    @GET("/goldendays-order/vipOrder/getVipOrderDetail")
    Observable<NetResponse<List<CommentProductBean>>> getVipOrderDetail(@Query("userId") String userId);

    @POST("/goldendays-order/order/weChatPay/getOrder")
    Observable<NetResponse<JsonObject>> getPaymentOrder(@Body PaymentOrderReqBean reqBean);
}
