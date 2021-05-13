package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailBean implements Serializable {

    @SerializedName("area")
    private String area;
    @SerializedName("city")
    private String city;
    @SerializedName("createtime")
    private String createtime;
    @SerializedName("ordernumber")
    private String ordernumber;
    @SerializedName("orderprice")
    private Double orderprice;
    @SerializedName("pickuptype")
    private Integer pickuptype;
    @SerializedName("postage")
    private Double postage;
    @SerializedName("province")
    private String province;
    @SerializedName("useraddress")
    private String useraddress;
    @SerializedName("useraddressname")
    private String useraddressname;
    @SerializedName("useraddressphone")
    private String useraddressphone;
    @SerializedName("orderDetailList")
    private List<OrderDetailListDTO> orderDetailList;
    private String loc;
    private String sumMoney;

//    public  setOrderDetailBean() {
//        city="";
//        createtime="";
//        ordernumber="";
//        orderprice=0.00;
//        pickuptype=0;
//        postage=0.00;
//        province="";
//        useraddress="";
//        useraddressname="";
//        useraddressphone="";
//        orderDetailList=new ArrayList<>();
//        area="";
//
//    }

    public String getSumMoney() {
        return  getDoubleMoney(orderprice+postage);
    }
    private String getDoubleMoney(Double price){
        String priceStr = new DecimalFormat("#.00").format(price);
        return "￥"+priceStr;
    }
    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getLoc() {
        return province+city+area;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public String getOrderprice() {
        return getDoubleMoney(orderprice);
    }

    public void setOrderprice(Double orderprice) {
        this.orderprice = orderprice;
    }

    public String getPickuptype() {
        if(pickuptype==0){
            return "普通快递";
        }else {
            return "到店自提";
        }
    }

    public void setPickuptype(Integer pickuptype) {
        this.pickuptype = pickuptype;
    }

    public String getPostage() {
        return getDoubleMoney(postage);
    }

    public void setPostage(Double postage) {
        this.postage = postage;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public String getUseraddressname() {
        return useraddressname;
    }

    public void setUseraddressname(String useraddressname) {
        this.useraddressname = useraddressname;
    }

    public String getUseraddressphone() {
        return useraddressphone;
    }

    public void setUseraddressphone(String useraddressphone) {
        this.useraddressphone = useraddressphone;
    }

    public List<OrderDetailListDTO> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetailListDTO> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    public static class OrderDetailListDTO implements Serializable {
        @SerializedName("commodityid")
        private Integer commodityid;
        @SerializedName("commodityname")
        private String commodityname;
        @SerializedName("commodityoldprice")
        private Integer commodityoldprice;
        @SerializedName("inventory")
        private String inventory;
        @SerializedName("inventorypic")
        private String inventorypic;
        @SerializedName("isevaluate")
        private Integer isevaluate;
        @SerializedName("purchasenum")
        private Integer purchasenum;
        @SerializedName("secondorderid")
        private String secondorderid;

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

        public Integer getCommodityoldprice() {
            return commodityoldprice;
        }

        public void setCommodityoldprice(Integer commodityoldprice) {
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

        public Integer getIsevaluate() {
            return isevaluate;
        }

        public void setIsevaluate(Integer isevaluate) {
            this.isevaluate = isevaluate;
        }

        public Integer getPurchasenum() {
            return purchasenum;
        }

        public void setPurchasenum(Integer purchasenum) {
            this.purchasenum = purchasenum;
        }

        public String getSecondorderid() {
            return secondorderid;
        }

        public void setSecondorderid(String secondorderid) {
            this.secondorderid = secondorderid;
        }
    }
}
