package com.goldensky.vip.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class HomeBean implements MultiItemEntity {

    private int homeItemType;
    private String itemTitle;
    private List<String> lbList;
    private List<CommodityBean> mCommodityBeanList;

    public HomeBean(int homeItemType) {
        this.homeItemType = homeItemType;
    }

    public HomeBean(int homeItemType, List<CommodityBean> commodityBeanList, String itemTitle) {
        this.homeItemType = homeItemType;
        mCommodityBeanList = commodityBeanList;
        this.itemTitle = itemTitle;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public List<String> getLbList() {
        return lbList;
    }

    public void setLbList(List<String> lbList) {
        this.lbList = lbList;
    }

    public List<CommodityBean> getCommodityBeanList() {
        return mCommodityBeanList;
    }

    public void setCommodityBeanList(List<CommodityBean> commodityBeanList) {
        mCommodityBeanList = commodityBeanList;
    }

    @Override
    public int getItemType() {
        return homeItemType;
    }
}
