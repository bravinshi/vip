package com.goldensky.vip.model;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/11 16:44
 * 包名： com.goldensky.together.model
 * 类说明：
 */
public class SetPasswordInputModel {
    private String password;
    private String confirm;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
