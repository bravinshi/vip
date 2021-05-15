package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.Query;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/15 11:58
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class ForgetPasswordReqBean {
    @SerializedName("usermobile")
    private String userMobile;
    private String phoneCode;
    @SerializedName("userpassword")
    private String password;

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
