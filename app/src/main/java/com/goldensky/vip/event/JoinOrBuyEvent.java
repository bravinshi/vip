package com.goldensky.vip.event;

import com.goldensky.vip.bean.InventoryBean;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/11 10:20
 * 包名： com.goldensky.vip.event
 * 类说明：
 */
public class JoinOrBuyEvent {
    private InventoryBean inventory;
    private Integer purchaseNum;
    private Boolean join;

    public InventoryBean getInventory() {
        return inventory;
    }

    public void setInventory(InventoryBean inventory) {
        this.inventory = inventory;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public Boolean getJoin() {
        return join;
    }

    public void setJoin(Boolean join) {
        this.join = join;
    }
}
