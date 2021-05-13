package com.goldensky.vip.api.order;


import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.base.net.NetParams;
import com.goldensky.vip.bean.CommentProductBean;
import com.goldensky.vip.bean.CommentReqBean;
import com.goldensky.vip.bean.GetOrderListReqBean;
import com.goldensky.vip.bean.OrderDetailBean;
import com.goldensky.vip.bean.OrderDetailReqBean;
import com.goldensky.vip.bean.OrderListBean;

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

    @POST("/goldendays-order/vipOrder/getOrderList")
    Observable<NetResponse<List<OrderListBean>>> getOrderList(@Body GetOrderListReqBean body);

    @POST("/goldendays-order/vipOrder/getOrderDetail")
    Observable<NetResponse<OrderDetailBean>> getOrderDetail(@Body OrderDetailReqBean body);

}
