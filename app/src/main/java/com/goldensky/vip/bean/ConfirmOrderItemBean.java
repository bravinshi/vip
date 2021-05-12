package com.goldensky.vip.bean;

import androidx.databinding.BaseObservable;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/11 16:42
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class ConfirmOrderItemBean {
    // "belongid": 0,
    //			"belongtype": 0,
    //			"commodityid": 0,
    //			"commodityname": "",
    //			"inventoryid": 0,
    //			"purchasenum": 0

    private String pic;
    private String commodityName;
    private Integer belongId;
    private Integer belongType;
    private Integer commodityId;
    private Integer inventoryId;
    private Integer purchaseNum;
    private String specification;
    private Double price;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getBelongId() {
        return belongId;
    }

    public void setBelongId(Integer belongId) {
        this.belongId = belongId;
    }

    public Integer getBelongType() {
        return belongType;
    }

    public void setBelongType(Integer belongType) {
        this.belongType = belongType;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
