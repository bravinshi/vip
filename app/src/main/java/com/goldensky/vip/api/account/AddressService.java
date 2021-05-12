package com.goldensky.vip.api.account;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.bean.AddUserAddressReqBean;
import com.goldensky.vip.bean.ChangeUserAddressReqBean;
import com.goldensky.vip.bean.DeleteUserAddressReqBean;
import com.goldensky.vip.bean.NewAddressResponseBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.bean.UserIdReqBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AddressService {
    @POST("/goldendays-user/vipAddress/getUserAddressListByUserId")//加载收货地址列表
    Observable<NetResponse<List<UserAddressBean>>> getUserAddressList(@Body UserIdReqBean body);
    @POST("/goldendays-user/vipAddress/deleteUserAddressByAddressId")//删除收货地址
    Observable<NetResponse<Object>> deleteUserAddress(@Body DeleteUserAddressReqBean body);
    @POST("/goldendays-user/vipAddress/updateByUserAddressId")//修改收货地址
    Observable<NetResponse<Object>> changeUserAddress(@Body ChangeUserAddressReqBean body);
    @POST("/goldendays-user/vipAddress/addVipAddress")//增加新地址
    Observable<NetResponse<NewAddressResponseBean>> addUserAddress(@Body AddUserAddressReqBean body);
}
