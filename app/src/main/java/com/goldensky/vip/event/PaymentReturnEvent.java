package com.goldensky.vip.event;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/13 17:23
 * 包名： com.goldensky.vip.event
 * 类说明：
 */
public class PaymentReturnEvent {
    private Boolean success;
    private Integer action;// 1 跳转到订单详情

    public static final Integer KEY_ACTION_ORDER_DETAIL = 1;

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
