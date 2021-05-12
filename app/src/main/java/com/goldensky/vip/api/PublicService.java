package com.goldensky.vip.api;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.bean.AreaListBean;
import com.goldensky.vip.bean.ShoppingCartGoodsBean;
import com.goldensky.vip.bean.UserIdReqBean;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
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
     * @return
     */
    @GET("/goldendays-user/applet/user/getVipCode")
    Observable<NetResponse<Boolean>> getVerifyCode(@Query("userMobile") String mobile);

    @GET("/goldendays-user/mbUserPer/checkCode")
    Observable<NetResponse<Object>> checkVerifyCode(@Query("userMobile") String mobile,
                                                    @Query("userCode") String verifyCode);

    /**
     * 获取省市县列表
     * @return
     */
    @GET("/goldendays-currency/sysArea/getAreaListAll")
    Observable<NetResponse<List<AreaListBean>>> getAreaList();

    /**
     * 获取购物车列表
     * @param body
     * @return
     */
    @POST("/goldendays-order/vipShoppingCart/getVipShoppingCartListByUserId")
    Observable<NetResponse<List<ShoppingCartGoodsBean>>> getVipShoppingCartList(@Body UserIdReqBean body);
}
