package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ExpressBean implements Serializable {

    @SerializedName("area")
    private String area;
    @SerializedName("city")
    private String city;
    @SerializedName("expresscode")
    private String expresscode;
    @SerializedName("expressname")
    private String expressname;
    @SerializedName("expressnumber")
    private String expressnumber;
    @SerializedName("province")
    private String province;

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

    public String getExpresscode() {
        return expresscode;
    }

    public void setExpresscode(String expresscode) {
        this.expresscode = expresscode;
    }

    public String getExpressname() {
        return expressname;
    }

    public void setExpressname(String expressname) {
        this.expressname = expressname;
    }

    public String getExpressnumber() {
        return expressnumber;
    }

    public void setExpressnumber(String expressnumber) {
        this.expressnumber = expressnumber;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
