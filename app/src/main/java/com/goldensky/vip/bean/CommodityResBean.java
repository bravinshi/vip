package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/7 9:59
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class CommodityResBean {

    private CommodityBean commodity;
    private List<InventoryBean> inventories;

    public CommodityBean getCommodity() {
        return commodity;
    }

    public void setCommodity(CommodityBean commodity) {
        this.commodity = commodity;
    }

    public List<InventoryBean> getInventories() {
        return inventories;
    }

    public void setInventories(List<InventoryBean> inventories) {
        this.inventories = inventories;
    }
}
