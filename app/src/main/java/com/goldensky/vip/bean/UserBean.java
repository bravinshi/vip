package com.goldensky.vip.bean;

public class UserBean {
    private String portrait;
    private String nickName;
    private String shopName;
    private Integer portraitId;

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public Integer getPortraitId() {
        return portraitId;
    }

    public void setPortraitId(Integer portraitId) {
        this.portraitId = portraitId;
    }

    public UserBean(String portrait, String nickName, String shopName) {
        this.portrait = portrait;
        this.nickName = nickName;
        this.shopName = shopName;
    }
}
