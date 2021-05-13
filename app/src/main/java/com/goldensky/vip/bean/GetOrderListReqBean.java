package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GetOrderListReqBean implements Serializable {

    @SerializedName("orderstatus")
    private Integer orderstatus;
    @SerializedName("userid")
    private String userid;

    public GetOrderListReqBean(Integer orderstatus, String userid) {
        this.orderstatus = orderstatus;
        this.userid = userid;
    }

    public Integer getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
