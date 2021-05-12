package com.goldensky.vip.event;

import com.goldensky.vip.bean.ConfirmOrderItemBean;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/12 10:10
 * 包名： com.goldensky.vip.event
 * 类说明：
 */
public class PurchaseNumChangeEvent {
    private Boolean notify;
    private Integer newNum;
    private ConfirmOrderItemBean confirmOrderItemBean;

    public Boolean getNotify() {
        return notify;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }

    public Integer getNewNum() {
        return newNum;
    }

    public void setNewNum(Integer newNum) {
        this.newNum = newNum;
    }

    public ConfirmOrderItemBean getConfirmOrderItemBean() {
        return confirmOrderItemBean;
    }

    public void setConfirmOrderItemBean(ConfirmOrderItemBean confirmOrderItemBean) {
        this.confirmOrderItemBean = confirmOrderItemBean;
    }
}
