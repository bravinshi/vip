package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShoppingCartGoodsBean implements Serializable {


    @SerializedName("abandon")
    private Integer abandon;
    @SerializedName("belongid")
    private Integer belongid;
    @SerializedName("belongtype")
    private Integer belongtype;
    @SerializedName("commodityicon")
    private String commodityicon;
    @SerializedName("commodityid")
    private Integer commodityid;
    @SerializedName("commodityisdel")
    private Integer commodityisdel;
    @SerializedName("commodityname")
    private String commodityname;
    @SerializedName("commodityoldprice")
    private Double commodityoldprice;
    @SerializedName("commodityprice")
    private Double commodityprice;
    @SerializedName("commoditytype")
    private Integer commoditytype;
    @SerializedName("companytype")
    private Integer companytype;
    @SerializedName("createtime")
    private String createtime;
    @SerializedName("entrance")
    private Integer entrance;
    @SerializedName("inventory")
    private String inventory;
    @SerializedName("inventoryid")
    private Integer inventoryid;
    @SerializedName("inventorynum")
    private Integer inventorynum;
    @SerializedName("inventorypic")
    private String inventorypic;
    @SerializedName("onshelfstatus")
    private Integer onshelfstatus;
    @SerializedName("purchasenum")
    private Integer purchasenum;
    @SerializedName("shoppingcartid")
    private String shoppingcartid;
    @SerializedName("userid")
    private String userid;

    public Integer getAbandon() {
        return abandon;
    }

    public void setAbandon(Integer abandon) {
        this.abandon = abandon;
    }

    public Integer getBelongid() {
        return belongid;
    }

    public void setBelongid(Integer belongid) {
        this.belongid = belongid;
    }

    public Integer getBelongtype() {
        return belongtype;
    }

    public void setBelongtype(Integer belongtype) {
        this.belongtype = belongtype;
    }

    public String getCommodityicon() {
        return commodityicon;
    }

    public void setCommodityicon(String commodityicon) {
        this.commodityicon = commodityicon;
    }

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }

    public Integer getCommodityisdel() {
        return commodityisdel;
    }

    public void setCommodityisdel(Integer commodityisdel) {
        this.commodityisdel = commodityisdel;
    }

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public Double getCommodityoldprice() {
        return commodityoldprice;
    }

    public void setCommodityoldprice(Double commodityoldprice) {
        this.commodityoldprice = commodityoldprice;
    }

    public Double getCommodityprice() {
        return commodityprice;
    }

    public void setCommodityprice(Double commodityprice) {
        this.commodityprice = commodityprice;
    }

    public Integer getCommoditytype() {
        return commoditytype;
    }

    public void setCommoditytype(Integer commoditytype) {
        this.commoditytype = commoditytype;
    }

    public Integer getCompanytype() {
        return companytype;
    }

    public void setCompanytype(Integer companytype) {
        this.companytype = companytype;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getEntrance() {
        return entrance;
    }

    public void setEntrance(Integer entrance) {
        this.entrance = entrance;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public Integer getInventoryid() {
        return inventoryid;
    }

    public void setInventoryid(Integer inventoryid) {
        this.inventoryid = inventoryid;
    }

    public Integer getInventorynum() {
        return inventorynum;
    }

    public void setInventorynum(Integer inventorynum) {
        this.inventorynum = inventorynum;
    }

    public String getInventorypic() {
        return inventorypic;
    }

    public void setInventorypic(String inventorypic) {
        this.inventorypic = inventorypic;
    }

    public Integer getOnshelfstatus() {
        return onshelfstatus;
    }

    public void setOnshelfstatus(Integer onshelfstatus) {
        this.onshelfstatus = onshelfstatus;
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

    public ShoppingCartGoodsBean(Integer abandon, Integer belongid, Integer belongtype, String commodityicon, Integer commodityid, Integer commodityisdel, String commodityname, Double commodityoldprice, Double commodityprice, Integer commoditytype, Integer companytype, String createtime, Integer entrance, String inventory, Integer inventoryid, Integer inventorynum, String inventorypic, Integer onshelfstatus, Integer purchasenum, String shoppingcartid, String userid) {
        this.abandon = abandon;
        this.belongid = belongid;
        this.belongtype = belongtype;
        this.commodityicon = commodityicon;
        this.commodityid = commodityid;
        this.commodityisdel = commodityisdel;
        this.commodityname = commodityname;
        this.commodityoldprice = commodityoldprice;
        this.commodityprice = commodityprice;
        this.commoditytype = commoditytype;
        this.companytype = companytype;
        this.createtime = createtime;
        this.entrance = entrance;
        this.inventory = inventory;
        this.inventoryid = inventoryid;
        this.inventorynum = inventorynum;
        this.inventorypic = inventorypic;
        this.onshelfstatus = onshelfstatus;
        this.purchasenum = purchasenum;
        this.shoppingcartid = shoppingcartid;
        this.userid = userid;
    }
}
