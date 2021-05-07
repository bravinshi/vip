package com.goldensky.vip.api.account;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.bean.EditAddressBean;
import com.goldensky.vip.bean.UserAddressBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AddressService {
    @POST("/goldendays-user/vipAddress/getUserAddressListByUserId")
    Observable<NetResponse<List<UserAddressBean>>> getUserAddressList(@Query("userid") String userId);
    @POST("/goldendays-user/vipAddress/deleteUserAddressByAddressId")
    Observable<NetResponse<EditAddressBean>> deleteUserAddress(@Query("useraddressid") String useraddressid);
    @POST("/goldendays-user/vipAddress/addVipAddress")
    Observable<NetResponse<EditAddressBean>> changeUserAddress(@Body RequestBody body);
//    @GET("/goldendays-currency/sysArea/getAreaListAll")
//    Observable
}
