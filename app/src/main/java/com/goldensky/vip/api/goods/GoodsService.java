package com.goldensky.vip.api.goods;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.bean.CommodityResBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
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
    Observable<NetResponse<CommodityResBean>> getGoodsDetail(@Query("commodityId") Integer goodsId);
}
