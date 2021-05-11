package com.goldensky.vip.bean;

public class DeleteUserAddressReqBean {
    private String useraddressid;

    public String getUseraddressid() {
        return useraddressid;
    }

    public void setUseraddressid(String useraddressid) {
        this.useraddressid = useraddressid;
    }

    public DeleteUserAddressReqBean(String useraddressid) {
        this.useraddressid = useraddressid;
    }
}
