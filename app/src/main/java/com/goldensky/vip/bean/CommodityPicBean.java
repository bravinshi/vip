package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/7 10:11
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class CommodityPicBean {

    @SerializedName("commodityid")
    private Integer commodityId;
    @SerializedName("commoditypicid")
    private Integer commodityPicId;
    @SerializedName("recoursetype")
    private Integer recourseType;
    @SerializedName("picurl")
    private String picUrl;

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCommodityPicId() {
        return commodityPicId;
    }

    public void setCommodityPicId(Integer commodityPicId) {
        this.commodityPicId = commodityPicId;
    }

    public Integer getRecourseType() {
        return recourseType;
    }

    public void setRecourseType(Integer recourseType) {
        this.recourseType = recourseType;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
