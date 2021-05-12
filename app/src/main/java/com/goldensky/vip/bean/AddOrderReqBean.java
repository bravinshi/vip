package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/12 14:28
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class AddOrderReqBean {
    // {
    //	"area": "",
    //	"areaid": 0,
    //	"city": "",
    //	"cityid": 0,
    //	"commodityList": [
    //		{
    //			"belongid": 0,
    //			"belongtype": 0,
    //			"commodityid": 0,
    //			"commodityname": "",
    //			"inventoryid": 0,
    //			"purchasenum": 0
    //		}
    //	],
    //	"province": "",
    //	"provinceid": 0,
    //	"useraddress": "",
    //	"useraddressname": "",
    //	"useraddressphone": "",
    //	"userid": ""
    //}

    private String area;
    @SerializedName("areaid")
    private Integer areaId;
    private String city;
    @SerializedName("cityid")
    private Integer cityId;
    private String province;
    @SerializedName("provinceid")
    private Integer provinceId;
    @SerializedName("useraddress")
    private String userAddress;
    @SerializedName("useraddressname")
    private String userAddressName;
    @SerializedName("useraddressphone")
    private String userAddressPhone;
    @SerializedName("userid")
    private String userId;

    private List<Commodity> commodityList;

    public static class Commodity {
        @SerializedName("belongid")
        private Integer belongId;
        @SerializedName("belongtype")
        private Integer belongType;
        @SerializedName("commodityid")
        private Integer commodityId;
        @SerializedName("inventoryid")
        private Integer inventoryId;
        @SerializedName("purchasenum")
        private Integer purchaseNum;
        @SerializedName("commodityname")
        private String commodityName;

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

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }
    }

    public List<Commodity> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<Commodity> commodityList) {
        this.commodityList = commodityList;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserAddressName() {
        return userAddressName;
    }

    public void setUserAddressName(String userAddressName) {
        this.userAddressName = userAddressName;
    }

    public String getUserAddressPhone() {
        return userAddressPhone;
    }

    public void setUserAddressPhone(String userAddressPhone) {
        this.userAddressPhone = userAddressPhone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
