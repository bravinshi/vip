package com.goldensky.entity.model;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/11 16:44
 * 包名： com.goldensky.together.model
 * 类说明：
 */
public class LoginInputModel {
    private String phoneOrLicense;
    private String passwordOrVerificationCode;

    public String getPhoneOrLicense() {
        return phoneOrLicense;
    }

    public void setPhoneOrLicense(String phoneOrLicense) {
        this.phoneOrLicense = phoneOrLicense;
    }

    public String getPasswordOrVerificationCode() {
        return passwordOrVerificationCode;
    }

    public void setPasswordOrVerificationCode(String passwordOrVerificationCode) {
        this.passwordOrVerificationCode = passwordOrVerificationCode;
    }
}
