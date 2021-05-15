package com.goldensky.vip.bean;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;

import androidx.databinding.BaseObservable;

import com.goldensky.framework.util.GsonUtils;
import com.goldensky.framework.util.TimeUtils;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class OrderDetailBean extends BaseObservable implements Serializable {

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

    public OrderDetailBean() {
    }

    public OrderDetailBean(int i) {
        city="";
        createtime="";
        ordernumber="";
        orderprice=0.00;
        pickuptype=0;
        postage=0.00;
        province="";
        useraddress="";
        useraddressname="";
        useraddressphone="";
        orderDetailList=new ArrayList<>();
        area="";
    }

    public String getSumMoney() {
        return  getDoubleMoney(orderprice+postage);
    }
    public Double getSum() {
        return orderprice+postage;
    }
    private String getDoubleMoney(Double price){
        String priceStr;
        if (price==0){
            priceStr ="0"+new DecimalFormat("#.00").format(price);
        }else {
            priceStr = new DecimalFormat("#.00").format(price);
        }
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
       if(createtime.equals("")){
          return "";
       }else {
           String dateFormat = dealDateFormat(createtime);
           return dateFormat;
       }
    }
    public static String dealDateFormat(String oldDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZ");
        Date date = new Date();
        try {
            date = sdf.parse(oldDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sdf2=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String sDate=sdf2.format(date);

        return sDate;
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
    public Double getOrderpriceD() {
        return orderprice;
    }
    public void setOrderprice(Double orderprice) {
        this.orderprice = orderprice;
    }

    public String getPickuptype() {
        if(pickuptype!=null){
            if(pickuptype==0){
                return "普通快递";
            }else {
                return "到店自提";
            }
        }
        return "普通快递";
    }
    public Integer getPickuptypeI(){
        return pickuptype;
    }
    public void setPickuptype(Integer pickuptype) {
        this.pickuptype = pickuptype;
    }

    public String getPostage() {
        return getDoubleMoney(postage);
    }
    public Double getPostageD() {
        return postage;
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
        private Double commodityoldprice;
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

        public SpannableString getCommodityoldprice() {
            return changTvSize(commodityoldprice);
        }

        public void setCommodityoldprice(Double commodityoldprice) {
            this.commodityoldprice = commodityoldprice;
        }

        public String getInventory() {
            JsonObject jsonObject = GsonUtils.fromJson(inventory, JsonObject.class);
            StringBuilder stringBuilder = new StringBuilder();
            List<String> keySet = new ArrayList<>(jsonObject.keySet());
            int size = keySet.size();
            for (int i = 0; i < size; i++) {
                String key = keySet.get(i);
                stringBuilder.append(jsonObject.get(key).getAsString());
                if (i != size - 1) {
                    stringBuilder.append(";");
                }
            }
            return stringBuilder.toString();
        }
        private SpannableString changTvSize(Double value) {
            String s = "¥"+new DecimalFormat("#.00").format(value);
            SpannableString spannableString = new SpannableString(s);
            spannableString.setSpan(new RelativeSizeSpan(0.7f), s.indexOf("¥"), 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return spannableString;
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

        public String getPurchasenum() {
            return "×"+purchasenum;
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
