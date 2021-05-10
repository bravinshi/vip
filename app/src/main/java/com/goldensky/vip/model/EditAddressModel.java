package com.goldensky.vip.model;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/11 16:44
 * 包名： com.goldensky.together.model
 * 类说明：
 */
public class EditAddressModel {
    private String consigneeName;
    private String consigneePhone;
    private String location;
    private boolean isDefault;

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneePhone() {
        return consigneePhone;
    }

    public void setConsigneePhone(String consigneePhone) {
        this.consigneePhone = consigneePhone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
