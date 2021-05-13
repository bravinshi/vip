package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UpdateOrderReqBean implements Serializable {

    @SerializedName("ordernumber")
    private String ordernumber;
    @SerializedName("orderstatus")
    private Integer orderstatus;

    public String getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(String ordernumber) {
        this.ordernumber = ordernumber;
    }

    public Integer getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }
}
