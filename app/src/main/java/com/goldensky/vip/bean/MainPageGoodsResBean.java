package com.goldensky.vip.bean;

import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/13 9:16
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class MainPageGoodsResBean {
    private List<CommodityBean> hotMoney;
    private List<CommodityBean> recommend;
    private List<CommodityBean> optimization;

    public List<CommodityBean> getHotMoney() {
        return hotMoney;
    }

    public void setHotMoney(List<CommodityBean> hotMoney) {
        this.hotMoney = hotMoney;
    }

    public List<CommodityBean> getRecommend() {
        return recommend;
    }

    public void setRecommend(List<CommodityBean> recommend) {
        this.recommend = recommend;
    }

    public List<CommodityBean> getOptimization() {
        return optimization;
    }

    public void setOptimization(List<CommodityBean> optimization) {
        this.optimization = optimization;
    }
}
