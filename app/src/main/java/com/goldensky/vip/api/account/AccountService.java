package com.goldensky.vip.api.account;


import com.goldensky.vip.base.net.NetParams;
import com.goldensky.vip.bean.LoginResponseBean;
import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.bean.UpdateVipUserReqBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface AccountService {

    @POST("/goldendays-auth/oauth/token")
    Observable<NetResponse<LoginResponseBean>> login(@QueryMap NetParams netParams);

    @POST("/goldendays-user/applet/user/updateVipUser")
    Observable<NetResponse<Object>> updateVipUser(@Body UpdateVipUserReqBean reqBean);
}
