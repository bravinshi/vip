package com.goldensky.vip.api.account;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.bean.AddUserAddressReqBean;
import com.goldensky.vip.bean.ChangeUserAddressReqBean;
import com.goldensky.vip.bean.DeleteUserAddressReqBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.bean.UserAddressListReqBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AddressService {
    @POST("/goldendays-user/vipAddress/getUserAddressListByUserId")
    Observable<NetResponse<List<UserAddressBean>>> getUserAddressList(@Body UserAddressListReqBean body);
    @POST("/goldendays-user/vipAddress/deleteUserAddressByAddressId")
    Observable<NetResponse<Object>> deleteUserAddress(@Body DeleteUserAddressReqBean body);
    @POST("/goldendays-user/vipAddress/addVipAddress")
    Observable<NetResponse<Object>> changeUserAddress(@Body ChangeUserAddressReqBean body);
    @POST("/goldendays-user/vipAddress/addVipAddress")
    Observable<NetResponse<Object>> addUserAddress(@Body AddUserAddressReqBean body);
}
