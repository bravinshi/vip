package com.goldensky.vip.bean;

import retrofit2.http.Query;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/15 11:58
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class ForgetPasswordReqBean {
    private String userMobile;
    private String userCode;
    private String password;

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
