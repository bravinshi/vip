package com.goldensky.vip.api.account;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.bean.AreaListBean;
import com.goldensky.vip.bean.EditAddressBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.bean.UserAddressListReqBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AddressService {
    @POST("/goldendays-user/vipAddress/getUserAddressListByUserId")
    Observable<NetResponse<List<UserAddressBean>>> getUserAddressList(@Body UserAddressListReqBean body);
    @POST("/goldendays-user/vipAddress/deleteUserAddressByAddressId")
    Observable<NetResponse<EditAddressBean>> deleteUserAddress(@Body RequestBody body);
    @POST("/goldendays-user/vipAddress/addVipAddress")
    Observable<NetResponse<EditAddressBean>> changeUserAddress(@Body RequestBody body);
    @GET("/goldendays-currency/sysArea/getAreaListAll")
    Observable<NetResponse<List<AreaListBean>>> getAreaList();
}
