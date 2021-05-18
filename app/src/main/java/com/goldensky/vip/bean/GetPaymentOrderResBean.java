package com.goldensky.vip.bean;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/18 13:04
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class GetPaymentOrderResBean {
    private String prepayid;
    private String noncestr;
    private String timestamp;
    private String paySign;

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }
}
