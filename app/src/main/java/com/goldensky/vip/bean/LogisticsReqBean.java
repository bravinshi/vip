package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LogisticsReqBean implements Serializable {

    @SerializedName("com")
    private String com;
    @SerializedName("from")
    private String from;
    @SerializedName("num")
    private String num;
    @SerializedName("order")
    private String order;
    @SerializedName("phone")
    private String phone;
    @SerializedName("resultv2")
    private String resultv2;
    @SerializedName("show")
    private String show;
    @SerializedName("to")
    private String to;

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getResultv2() {
        return resultv2;
    }

    public void setResultv2(String resultv2) {
        this.resultv2 = resultv2;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
