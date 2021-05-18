package com.goldensky.vip.api.order;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.bean.CommentProductBean;
import com.goldensky.vip.bean.CommentReqBean;
import com.goldensky.vip.bean.ExpressBean;
import com.goldensky.vip.bean.GetOrderListReqBean;
import com.goldensky.vip.bean.GetPaymentOrderResBean;
import com.goldensky.vip.bean.LogisticsBean;
import com.goldensky.vip.bean.LogisticsReqBean;
import com.goldensky.vip.bean.OrderDetailBean;
import com.goldensky.vip.bean.OrderListBean;
import com.goldensky.vip.bean.OrderNumberReqBean;
import com.goldensky.vip.bean.PaymentOrderReqBean;
import com.goldensky.vip.bean.UpdateOrderReqBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface OrderService {
    @POST("/goldendays-order/vipOrder/vipEvaluate")
    Observable<NetResponse<Object>> orderComment(@Body CommentReqBean reqBean);

    @GET("/goldendays-order/vipOrder/getVipOrderDetail")
    Observable<NetResponse<List<CommentProductBean>>> getVipOrderDetail(@Query("userId") String userId);

    @POST("/goldendays-order/order/weChatPay/getOrder")
    Observable<NetResponse<GetPaymentOrderResBean>> getPaymentOrder(@Body PaymentOrderReqBean reqBean);

    @POST("/goldendays-order/vipOrder/getOrderList")
    Observable<NetResponse<List<OrderListBean>>> getOrderList(@Body GetOrderListReqBean body);

    @POST("/goldendays-order/vipOrder/getOrderDetail")
    Observable<NetResponse<OrderDetailBean>> getOrderDetail(@Body OrderNumberReqBean body);

    @POST("/goldendays-order/vipOrder/updateOrder")
    Observable<NetResponse<Object>> updateOrder(@Body UpdateOrderReqBean body);

    @POST("/goldendays-order/vipOrder/getExpressNumber")
    Observable<NetResponse<ExpressBean>> getExpressNumber(@Body OrderNumberReqBean body);

    @POST("/goldendays-currency/currency/queryTrackMap")
    Observable<NetResponse<LogisticsBean>> querTrackMap(@Body LogisticsReqBean body);

}
