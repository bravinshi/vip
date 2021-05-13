package com.goldensky.vip.api.shoppingcart;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.bean.DeleteCartGoodsReqBean;
import com.goldensky.vip.bean.ShoppingCartGoodsBean;
import com.goldensky.vip.bean.UpdateCartGoodsNumberReqBean;
import com.goldensky.vip.bean.UserIdReqBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ShoppingCartService {
    @POST("/goldendays-order/vipShoppingCart/getVipShoppingCartListByUserId")//获取vip购物车列表
    Observable<NetResponse<List<ShoppingCartGoodsBean>>> getVipShoppingCartList(@Body UserIdReqBean body);

    @POST("/goldendays-order/vipShoppingCart/updateShoppingCartCommodityNumber")//修改购物车商品数量
    Observable<NetResponse<Object>> updateCartGoodsNumber(@Body UpdateCartGoodsNumberReqBean body);

    @POST("/goldendays-order/vipShoppingCart/deleteByShoppingCartIdList")
    Observable<NetResponse<Object>> deleteCartGoods(@Body DeleteCartGoodsReqBean body);
}
