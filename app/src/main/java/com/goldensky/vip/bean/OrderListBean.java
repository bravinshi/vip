package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class OrderListBean implements Serializable {


    @SerializedName("orderid")
    private String orderid;
    @SerializedName("ordernumber")
    private String ordernumber;
    @SerializedName("orderprice")
    private Double orderprice;
    @SerializedName("orderstatus")
    private Integer orderstatus;
    @SerializedName("orderDetailList")
    private List<OrderDetailList> orderDetailList;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Double getOrderprice() {
        return orderprice;
    }

    public void setOrderprice(Double orderprice) {
        this.orderprice = orderprice;
    }

    public Integer getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }

    public List<OrderDetailList> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailList> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public static class OrderDetailList implements Serializable {
        @SerializedName("commodityid")
        private Integer commodityid;
        @SerializedName("commodityname")
        private String commodityname;
        @SerializedName("commodityoldprice")
        private Double commodityoldprice;
        @SerializedName("inventory")
        private String inventory;
        @SerializedName("inventorypic")
        private String inventorypic;
        @SerializedName("purchasenum")
        private Integer purchasenum;

        public Integer getCommodityid() {
            return commodityid;
        }

        public void setCommodityid(Integer commodityid) {
            this.commodityid = commodityid;
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

        public String getInventory() {
            return inventory;
        }

        public void setInventory(String inventory) {
            this.inventory = inventory;
        }

        public String getInventorypic() {
            return inventorypic;
        }

        public void setInventorypic(String inventorypic) {
            this.inventorypic = inventorypic;
        }

        public Integer getPurchasenum() {
            return purchasenum;
        }

        public void setPurchasenum(Integer purchasenum) {
            this.purchasenum = purchasenum;
        }
    }

}
