package com.goldensky.vip.api.shoppingcart;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.bean.ShoppingCartGoodsBean;
import com.goldensky.vip.bean.UserIdReqBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ShoppingCartService {
    @POST("/goldendays-order/vipShoppingCart/getVipShoppingCartListByUserId")//获取vip购物车列表
    Observable<NetResponse<List<ShoppingCartGoodsBean>>> getVipShoppingCartList(@Body UserIdReqBean body);
}
