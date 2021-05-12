package com.goldensky.vip.api.order;


import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.base.net.NetParams;
import com.goldensky.vip.bean.CommentReqBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface OrderService {
    @POST("/goldendays-order/vipOrder/vipEvaluate")
    Observable<NetResponse<Object>> orderComment(@Body CommentReqBean reqBean);
}
