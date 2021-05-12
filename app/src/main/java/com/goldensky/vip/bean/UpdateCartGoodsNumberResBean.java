package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateCartGoodsNumberResBean implements Serializable {

    @SerializedName("belongtype")
    private Integer belongtype;
    @SerializedName("commodityid")
    private Integer commodityid;
    @SerializedName("commoditytype")
    private Integer commoditytype;
    @SerializedName("inventoryid")
    private Integer inventoryid;
    @SerializedName("purchasenum")
    private Integer purchasenum;
    @SerializedName("shoppingcartid")
    private String shoppingcartid;
    @SerializedName("userid")
    private String userid;

    public UpdateCartGoodsNumberResBean(Integer belongtype, Integer commodityid, Integer commoditytype, Integer inventoryid, Integer purchasenum, String shoppingcartid, String userid) {
        this.belongtype = belongtype;
        this.commodityid = commodityid;
        this.commoditytype = commoditytype;
        this.inventoryid = inventoryid;
        this.purchasenum = purchasenum;
        this.shoppingcartid = shoppingcartid;
        this.userid = userid;
    }

    public Integer getBelongtype() {
        return belongtype;
    }

    public void setBelongtype(Integer belongtype) {
        this.belongtype = belongtype;
    }

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }

    public Integer getCommoditytype() {
        return commoditytype;
    }

    public void setCommoditytype(Integer commoditytype) {
        this.commoditytype = commoditytype;
    }

    public Integer getInventoryid() {
        return inventoryid;
    }

    public void setInventoryid(Integer inventoryid) {
        this.inventoryid = inventoryid;
    }

    public Integer getPurchasenum() {
        return purchasenum;
    }

    public void setPurchasenum(Integer purchasenum) {
        this.purchasenum = purchasenum;
    }

    public String getShoppingcartid() {
        return shoppingcartid;
    }

    public void setShoppingcartid(String shoppingcartid) {
        this.shoppingcartid = shoppingcartid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
