package com.goldensky.vip.model;

import androidx.databinding.BaseObservable;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/10 16:58
 * 包名： com.goldensky.vip.model
 * 类说明：
 */
public class PurchaseQuantityModel extends BaseObservable {
    private String purchaseQuantity;

    public PurchaseQuantityModel(String purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public void increase() {
        int temp = Integer.parseInt(purchaseQuantity);
        temp++;
        purchaseQuantity = Integer.toString(temp);
        notifyChange();
    }

    public void decrease() {
        int temp = Integer.parseInt(purchaseQuantity);
        temp--;
        if (temp < 1) {
            temp = 1;
        }
        purchaseQuantity = Integer.toString(temp);
        notifyChange();
    }

    public String getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public Integer getPurchaseQuantityInt() {
        return Integer.valueOf(purchaseQuantity);
    }

    public void setPurchaseQuantity(String purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }
}
