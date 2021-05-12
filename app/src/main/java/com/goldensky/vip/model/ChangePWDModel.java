package com.goldensky.vip.model;


import com.goldensky.vip.helper.AccountHelper;

public class ChangePWDModel {
    private String hint;
    private String verificationCode;
    private String newPassword;
    private String newPasswordConfirm;
    private Boolean newPasswordCanSee=false;
    private Boolean confirmPasswordCanSee=false;
    private Boolean isConfirm=false;

    public ChangePWDModel() {
        setHint("本次操作需要短信确认，短信验证码将发送至"+ AccountHelper.getUserMobile().substring(0,3)+"****"+AccountHelper.getUserMobile().substring(7,11) +"，请注意查收");
    }

    public Boolean getConfirm() {
        return isConfirm;
    }

    public void setConfirm(Boolean confirm) {
        isConfirm = confirm;
    }

    public Boolean getNewPasswordCanSee() {
        return newPasswordCanSee;
    }

    public void setNewPasswordCanSee(Boolean newPasswordCanSee) {
        this.newPasswordCanSee = newPasswordCanSee;
    }

    public Boolean getConfirmPasswordCanSee() {
        return confirmPasswordCanSee;
    }

    public void setConfirmPasswordCanSee(Boolean confirmPasswordCanSee) {
        this.confirmPasswordCanSee = confirmPasswordCanSee;
    }

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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
