package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/13 13:38
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class PaymentOrderReqBean {
    // "openid": "",
    //	"ordernumberList": [],
    //	"payType": 0,
    //	"rechargeMoney": 0

    @SerializedName("ordernumberList")
    private List<String> orderNumberList;
    private Integer payType;
    private Double rechargeMoney;

    public List<String> getOrderNumberList() {
        return orderNumberList;
    }

    public void setOrderNumberList(List<String> orderNumberList) {
        this.orderNumberList = orderNumberList;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Double getRechargeMoney() {
        return rechargeMoney;
    }

    public void setRechargeMoney(Double rechargeMoney) {
        this.rechargeMoney = rechargeMoney;
    }
}
