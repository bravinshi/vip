package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewAddressResponseBean implements Serializable {

    @SerializedName("useraddressid")
    private String useraddressid;
    @SerializedName("useraddresstime")
    private String useraddresstime;

    public String getUseraddressid() {
        return useraddressid;
    }

    public void setUseraddressid(String useraddressid) {
        this.useraddressid = useraddressid;
    }

    public String getUseraddresstime() {
        return useraddresstime;
    }

    public void setUseraddresstime(String useraddresstime) {
        this.useraddresstime = useraddresstime;
    }
}
