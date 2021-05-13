package com.goldensky.vip.viewmodel.home;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.api.goods.GoodsService;
import com.goldensky.vip.base.error.FailCallback;
import com.goldensky.vip.bean.CommodityBean;
import com.goldensky.vip.bean.HomeBean;
import com.goldensky.vip.bean.MainPageGoodsResBean;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.goldensky.vip.adapter.HomeAdapter.ITEM_TYPE_JRBK;
import static com.goldensky.vip.adapter.HomeAdapter.ITEM_TYPE_JTYX;
import static com.goldensky.vip.adapter.HomeAdapter.ITEM_TYPE_LB;
import static com.goldensky.vip.adapter.HomeAdapter.ITEM_TYPE_RMD;

public class HomeViewModel extends PublicViewModel {
    public MutableLiveData<Boolean> loadResult = new MutableLiveData();
    private HomeBean lbBean;
    public List<HomeBean> homeBeans = new ArrayList<>();
    //轮播
    public void initLbData() {
        lbBean = new HomeBean(ITEM_TYPE_LB);
        List<String> lbList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lbList.add("testurl");
        }
        lbBean.setLbList(lbList);
        homeBeans.add(lbBean);
        loadResult.postValue(true);
    }


    //获取商品信息
    public void getCommodityIndex(String userid, FailCallback failCallback) {
        RetrofitAgent.create(GoodsService.class)
                .index(userid).subscribe(new ToastNetObserver<MainPageGoodsResBean>() {
            @Override
            public void onSuccess(MainPageGoodsResBean data) {
                homeBeans.clear();
                if (lbBean != null) {
                    homeBeans.add(lbBean);
                }
                if (data != null) {
                    List<CommodityBean> rmdBeans = data.getRecommend();
                    if (rmdBeans != null && rmdBeans.size() > 0) {
                        HomeBean bean = new HomeBean(ITEM_TYPE_RMD, rmdBeans, "为你推荐");
                        homeBeans.add(bean);
                    }
                    List<CommodityBean> hotBeans = data.getHotMoney();
                    if (hotBeans != null && hotBeans.size() > 0) {
                        HomeBean bean = new HomeBean(ITEM_TYPE_JRBK, hotBeans, "今日爆款");
                        homeBeans.add(bean);
                    }

                    List<CommodityBean> jtyxBeans = data.getOptimization();
                    if (jtyxBeans != null && jtyxBeans.size() > 0) {
                        if (jtyxBeans.size() % 3 == 2) {
                            jtyxBeans.add(null);
                        } else if (jtyxBeans.size() % 3 == 1) {
                            jtyxBeans.add(null);
                            jtyxBeans.add(null);
                        }
                        HomeBean bean = new HomeBean(ITEM_TYPE_JTYX, jtyxBeans, "金天优选");
                        homeBeans.add(bean);
                    }
                }
                loadResult.postValue(true);
            }

            @Override
            public boolean onFail(NetResponse<MainPageGoodsResBean> data) {
                super.onFail(data);
                if (failCallback != null) {
                    failCallback.onFail(data);
                }
                return true;
            }
        });
    }
}
