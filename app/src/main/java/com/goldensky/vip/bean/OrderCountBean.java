package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderCountBean implements Serializable {

    @SerializedName("total")
    private Integer total;
    @SerializedName("toBePay")
    private Integer toBePay;
    @SerializedName("received")
    private Integer received;
    @SerializedName("complete")
    private Integer complete;

    public OrderCountBean(Integer total, Integer toBePay, Integer received, Integer complete) {
        this.total = total;
        this.toBePay = toBePay;
        this.received = received;
        this.complete = complete;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getToBePay() {
        return toBePay;
    }

    public void setToBePay(Integer toBePay) {
        this.toBePay = toBePay;
    }

    public Integer getReceived() {
        return received;
    }

    public void setReceived(Integer received) {
        this.received = received;
    }

    public Integer getComplete() {
        return complete;
    }

    public void setComplete(Integer complete) {
        this.complete = complete;
    }
}

