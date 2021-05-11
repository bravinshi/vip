package com.goldensky.vip.event;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/8 11:33
 * 包名： com.goldensky.vip.event
 * 类说明：
 */
public class VipUserChangeEvent {
    private Boolean success;

    public VipUserChangeEvent(Boolean success) {
        this.success = success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
