package com.goldensky.vip.api;

import com.goldensky.framework.bean.NetResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/12 16:54
 * 包名： com.goldensky.together.api.account
 * 类说明：
 */
public interface PublicService {

    @Multipart
    @POST("/goldendays-currency/tool/uploadpro")
    Observable<NetResponse<String>> uploadPic(@Part MultipartBody.Part file);

    /**
     * 获取验证码
     *
     * @param mobile  手机号码
     * @param forWhat 验证码用途。1登录,2注册, 3:修改密码 4更换手机原手机  5更换手机新手机
     * @return
     */
    @GET("/goldendays-user/mbUserPer/getCode")
    Observable<NetResponse<Boolean>> getVerifyCode(@Query("userMobile") String mobile,
                                                   @Query("forwhat") String forWhat);

    /**
     * 设置新密码/找回密码
     *
     * @param mobile
     * @param code
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST("/goldendays-user/mbUser/updatePwd")
    Observable<NetResponse<Object>> updatePwd(@Field("userMobile") String mobile,
                                              @Field("userCode") String code,
                                              @Field("userPwd") String password);

    @GET("/goldendays-user/mbUserPer/checkCode")
    Observable<NetResponse<Object>> checkVerifyCode(@Query("userMobile") String mobile,
                                                    @Query("userCode") String verifyCode);
}
