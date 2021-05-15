package com.goldensky.vip.api.account;


import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.base.net.NetParams;
import com.goldensky.vip.bean.ForgetPasswordReqBean;
import com.goldensky.vip.bean.LoginResponseBean;
import com.goldensky.vip.bean.SuperStBean;
import com.goldensky.vip.bean.UpdateVipUserReqBean;
import com.goldensky.vip.bean.UserIdReqBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface AccountService {

    @POST("/goldendays-auth/oauth/token")
    Observable<NetResponse<LoginResponseBean>> login(@QueryMap NetParams netParams);

    @POST("/goldendays-user/applet/user/updateVipUser")
    Observable<NetResponse<Object>> updateVipUser(@Body UpdateVipUserReqBean reqBean);

    @POST("/goldendays-user/merchantUser/getUserAndMerchant")
    Observable<NetResponse<SuperStBean>> getUserAndMerchant(@Body UserIdReqBean reqBean);

    @POST("https://openapi.jtmsh.com//goldendays-currency/wxApplet/getWxAppletCode")
//    @POST("/goldendays-currency/wxApplet/getWxAppletCode")
    Observable<ResponseBody> getWxAppletCode(@Query("userid") String userid,
                                                          @Query("scene") String scene);

    @POST("/goldendays-user/applet/user/forgetPwd")
    Observable<NetResponse<Object>> forgetPwd(@Body ForgetPasswordReqBean forgetPasswordReqBean);
}
