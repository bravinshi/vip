package com.goldensky.vip.viewmodel.goods;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.api.goods.GoodsService;
import com.goldensky.vip.bean.CommodityBean;
import com.goldensky.vip.bean.CommodityResBean;
import com.goldensky.vip.viewmodel.PublicViewModel;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/14 14:23
 * 包名： com.goldensky.vip.viewmodel.goods
 * 类说明：
 */
public class GoodsDetailViewModel extends PublicViewModel {

    public MutableLiveData<CommodityResBean> goodsDetailLive = new MutableLiveData<>();

    public void getGoodsDetail(Integer goodsId) {
        RetrofitAgent.create(GoodsService.class)
                .getGoodsDetail(goodsId)
                .subscribe(new ToastNetObserver<CommodityResBean>(){
                    @Override
                    public void onSuccess(CommodityResBean data) {
                        goodsDetailLive.postValue(data);
                    }
                });
    }
}
