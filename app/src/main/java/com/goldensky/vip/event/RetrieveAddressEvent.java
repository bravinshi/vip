package com.goldensky.vip.event;

import com.goldensky.vip.bean.UserAddressBean;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/8 11:33
 * 包名： com.goldensky.vip.event
 * 类说明：
 */
public class RetrieveAddressEvent {
    private String address;
    private UserAddressBean addressBean;

    public UserAddressBean getAddressBean() {
        return addressBean;
    }

    public void setAddressBean(UserAddressBean addressBean) {
        this.addressBean = addressBean;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
