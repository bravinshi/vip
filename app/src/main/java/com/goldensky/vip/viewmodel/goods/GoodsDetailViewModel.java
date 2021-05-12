package com.goldensky.vip.viewmodel.goods;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.api.goods.GoodsService;
import com.goldensky.vip.bean.CommodityBean;
import com.goldensky.vip.viewmodel.PublicViewModel;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/14 14:23
 * 包名： com.goldensky.vip.viewmodel.goods
 * 类说明：
 */
public class GoodsDetailViewModel extends PublicViewModel {

    public MutableLiveData<CommodityBean> goodsDetailLive = new MutableLiveData<>();

    public void getGoodsDetail(Integer goodsId) {
        RetrofitAgent.create(GoodsService.class)
                .getGoodsDetail(goodsId)
                .subscribe(new ToastNetObserver<CommodityBean>(){
                    @Override
                    public void onSuccess(CommodityBean data) {
                        goodsDetailLive.postValue(data);
                    }
                });
    }
}
