package com.goldensky.vip.api.account;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.bean.AddUserAddressReqBean;
import com.goldensky.vip.bean.AreaListBean;
import com.goldensky.vip.bean.ChangeUserAddressReqBean;
import com.goldensky.vip.bean.DeleteUserAddressReqBean;
import com.goldensky.vip.bean.EditAddressBean;
import com.goldensky.vip.bean.GetUserAddressReqBean;
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
    Observable<NetResponse<List<UserAddressBean>>> getUserAddressList(@Body GetUserAddressReqBean body);
    @POST("/goldendays-user/vipAddress/deleteUserAddressByAddressId")
    Observable<NetResponse<EditAddressBean>> deleteUserAddress(@Body DeleteUserAddressReqBean body);
    @POST("/goldendays-user/vipAddress/addVipAddress")
    Observable<NetResponse<EditAddressBean>> changeUserAddress(@Body ChangeUserAddressReqBean body);
    @GET("/goldendays-currency/sysArea/getAreaListAll")
    Observable<NetResponse<List<AreaListBean>>> getAreaList();
    @POST("/goldendays-user/vipAddress/addVipAddress")
    Observable<NetResponse<EditAddressBean>> addUserAddress(@Body AddUserAddressReqBean body);
}
