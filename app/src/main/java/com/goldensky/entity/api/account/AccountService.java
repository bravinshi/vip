package com.goldensky.entity.api.account;


import com.goldensky.entity.base.net.NetParams;
import com.goldensky.entity.bean.LoginResponseBean;
import com.goldensky.framework.bean.NetResponse;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface AccountService {

    @POST("/goldendays-auth/oauth/token")
    Observable<NetResponse<LoginResponseBean>> login(@QueryMap NetParams netParams);

}
