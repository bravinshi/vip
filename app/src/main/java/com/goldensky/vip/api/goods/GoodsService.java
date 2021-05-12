package com.goldensky.vip.api.goods;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.bean.AddOrderReqBean;
import com.goldensky.vip.bean.CommodityBean;
import com.goldensky.vip.bean.GoodsCommentResBean;
import com.goldensky.vip.bean.JoinIntoShoppingCartReqBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/7 9:31
 * 包名： com.goldensky.vip.api.goods
 * 类说明：
 */
public interface GoodsService {

    @GET("/goldendays-goods/commodity/vip/detail")
    Observable<NetResponse<CommodityBean>> getGoodsDetail(@Query("commodityId") Integer goodsId);

    @GET("/goldendays-goods/commodity/getCommodityComment")
    Observable<NetResponse<GoodsCommentResBean>> getGoodsComment(@Query("currentPage") Integer currentPage,
                                                                @Query("pageSize") Integer pageSize,
                                                                @Query("commodityId") String commodityId,
                                                                @Query("evaluateType") Integer evaluateType);// 评论类型 不传为全部 1:好 2:中 3:差 4:是否有图

    /**
     * 加入购物车
     *
     * @param reqBean body
     *
     * @return observable
     */
    @POST("/goldendays-order/vipShoppingCart/addVipShoppingCart")
    Observable<NetResponse<Object>> joinIntoShoppingCart(@Body JoinIntoShoppingCartReqBean reqBean);

    @POST("/goldendays-order/vipOrder/addOrder")
    Observable<NetResponse<Object>> addOrder(@Body AddOrderReqBean reqBean);
}
