package com.goldensky.vip.bean;

public class GetUserAddressReqBean {
    private String userId;

    public GetUserAddressReqBean(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
