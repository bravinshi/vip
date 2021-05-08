package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChangeUserAddressReqBean implements Serializable {
    @SerializedName("area")
    private String area;
    @SerializedName("areaid")
    private Integer areaid;
    @SerializedName("city")
    private String city;
    @SerializedName("cityid")
    private Integer cityid;
    @SerializedName("isdel")
    private Integer isdel;
    @SerializedName("province")
    private String province;
    @SerializedName("provinceid")
    private Integer provinceid;
    @SerializedName("useraddress")
    private String useraddress;
    @SerializedName("useraddressdefault")
    private Integer useraddressdefault;
    @SerializedName("useraddressid")
    private String useraddressid;
    @SerializedName("useraddressname")
    private String useraddressname;
    @SerializedName("useraddressphone")
    private String useraddressphone;
    @SerializedName("userid")
    private String userid;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Integer getAreaid() {
        return areaid;
    }

    public void setAreaid(Integer areaid) {
        this.areaid = areaid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }

    public String getUseraddress() {
        return useraddress;
    }

    public void setUseraddress(String useraddress) {
        this.useraddress = useraddress;
    }

    public Integer getUseraddressdefault() {
        return useraddressdefault;
    }

    public void setUseraddressdefault(Integer useraddressdefault) {
        this.useraddressdefault = useraddressdefault;
    }

    public String getUseraddressid() {
        return useraddressid;
    }

    public void setUseraddressid(String useraddressid) {
        this.useraddressid = useraddressid;
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



    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public ChangeUserAddressReqBean(String area, Integer areaid, String city, Integer cityid, Integer isdel, String province, Integer provinceid, String useraddress, Integer useraddressdefault, String useraddressid, String useraddressname, String useraddressphone, String userid) {
        this.area = area;
        this.areaid = areaid;
        this.city = city;
        this.cityid = cityid;
        this.isdel = isdel;
        this.province = province;
        this.provinceid = provinceid;
        this.useraddress = useraddress;
        this.useraddressdefault = useraddressdefault;
        this.useraddressid = useraddressid;
        this.useraddressname = useraddressname;
        this.useraddressphone = useraddressphone;
        this.userid = userid;
    }
}
