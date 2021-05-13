package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeleteCartGoodsReqBean implements Serializable {

    @SerializedName("shoppingcartids")
    private String shoppingcartids;

    public String getShoppingcartids() {
        return shoppingcartids;
    }

    public void setShoppingcartids(String shoppingcartids) {
        this.shoppingcartids = shoppingcartids;
    }
}
