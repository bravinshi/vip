package com.goldensky.entity.model;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/11 16:44
 * 包名： com.goldensky.together.model
 * 类说明：
 */
public class ForgetPasswordInputModel {
    private String phone;
    private String verificationCode;
    private String newPassword;
    private String newPasswordConfirm;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPasswordConfirm() {
        return newPasswordConfirm;
    }

    public void setNewPasswordConfirm(String newPasswordConfirm) {
        this.newPasswordConfirm = newPasswordConfirm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
