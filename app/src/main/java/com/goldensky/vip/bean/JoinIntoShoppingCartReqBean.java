package com.goldensky.vip.bean;

import com.goldensky.vip.helper.AccountHelper;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/11 9:58
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class JoinIntoShoppingCartReqBean {

    private Double commodityoldprice;
    private Double commodityprice;
    private Integer commoditytype;
    private Integer belongtype;
    private Integer belongid;
    private Integer companytype;
    private Integer inventoryid;
    private Integer inventorynum;
    private Integer commodityid;
    private Integer purchasenum;
    private String inventorypic;
    private String userid;
    private String inventory;
    private String commodityname;
    private String commodityicon;

    public static JoinIntoShoppingCartReqBean fromInventoryAndGoodsDetail(CommodityBean commodityDetail, InventoryBean inventory) {
        JoinIntoShoppingCartReqBean joinIntoShoppingCartReqBean = new JoinIntoShoppingCartReqBean();

        joinIntoShoppingCartReqBean.setCommodityoldprice(inventory.getCommodityOldPrice());
        joinIntoShoppingCartReqBean.setCommodityprice(inventory.getCommodityPrice());
        joinIntoShoppingCartReqBean.setCommoditytype(commodityDetail.getCommodityType());
        joinIntoShoppingCartReqBean.setBelongtype(commodityDetail.getBelongType());
        joinIntoShoppingCartReqBean.setBelongid(commodityDetail.getBelongId());
        joinIntoShoppingCartReqBean.setCommodityid(inventory.getCommodityId());
        joinIntoShoppingCartReqBean.setCompanytype(inventory.getCompanyType());
        joinIntoShoppingCartReqBean.setInventoryid(inventory.getInventoryId());
        joinIntoShoppingCartReqBean.setInventorynum(inventory.getInventoryNum());
        joinIntoShoppingCartReqBean.setInventorypic(inventory.getInventoryPic());
        joinIntoShoppingCartReqBean.setUserid(AccountHelper.getUserId());
        joinIntoShoppingCartReqBean.setInventory(inventory.getInventory());
        joinIntoShoppingCartReqBean.setCommodityname(inventory.getCommodityName());
        joinIntoShoppingCartReqBean.setCommodityicon(inventory.getInventoryPic());

        return joinIntoShoppingCartReqBean;
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

    public Integer getBelongtype() {
        return belongtype;
    }

    public void setBelongtype(Integer belongtype) {
        this.belongtype = belongtype;
    }

    public Integer getBelongid() {
        return belongid;
    }

    public void setBelongid(Integer belongid) {
        this.belongid = belongid;
    }

    public Integer getCompanytype() {
        return companytype;
    }

    public void setCompanytype(Integer companytype) {
        this.companytype = companytype;
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

    public Integer getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(Integer commodityid) {
        this.commodityid = commodityid;
    }

    public Integer getPurchasenum() {
        return purchasenum;
    }

    public void setPurchasenum(Integer purchasenum) {
        this.purchasenum = purchasenum;
    }

    public String getInventorypic() {
        return inventorypic;
    }

    public void setInventorypic(String inventorypic) {
        this.inventorypic = inventorypic;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public String getCommodityicon() {
        return commodityicon;
    }

    public void setCommodityicon(String commodityicon) {
        this.commodityicon = commodityicon;
    }
}
