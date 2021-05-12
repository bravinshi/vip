package com.goldensky.vip.bean;

import androidx.databinding.BaseObservable;

import com.goldensky.framework.util.GsonUtils;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/11 16:42
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class ConfirmOrderItemBean {
    // "belongid": 0,
    //			"belongtype": 0,
    //			"commodityid": 0,
    //			"commodityname": "",
    //			"inventoryid": 0,
    //			"purchasenum": 0

    private String pic;
    private String commodityName;
    private Integer belongId;
    private Integer belongType;
    private Integer commodityId;
    private Integer inventoryId;
    private Integer purchaseNum;
    private String specification;
    private Double price;

    public AddOrderReqBean.Commodity generateCommodity() {
        AddOrderReqBean.Commodity commodity = new AddOrderReqBean.Commodity();

        commodity.setBelongId(belongId);
        commodity.setBelongType(belongType);
        commodity.setCommodityId(commodityId);
        commodity.setCommodityName(commodityName);
        commodity.setInventoryId(inventoryId);
        commodity.setPurchaseNum(purchaseNum);

        return commodity;
    }

    public static ConfirmOrderItemBean generateConfirmOrderItem(InventoryBean inventory,
                                                                CommodityBean commodityBean, Integer purchaseNum) {
        ConfirmOrderItemBean confirmOrderItemBean = new ConfirmOrderItemBean();

        confirmOrderItemBean.setPrice(inventory.getCommodityOldPrice());
        // 展示用规格
        JsonObject jsonObject = GsonUtils.fromJson(inventory.getInventory(), JsonObject.class);
        StringBuilder stringBuilder = new StringBuilder();
        List<String> keySet = new ArrayList<>(jsonObject.keySet());
        int size = keySet.size();
        for (int i = 0; i < size; i++) {
            String key = keySet.get(i);
            stringBuilder.append(jsonObject.get(key).getAsString());
            if (i != size - 1) {
                stringBuilder.append(";");
            }
        }
        confirmOrderItemBean.setSpecification(stringBuilder.toString());
        confirmOrderItemBean.setPic(inventory.getInventoryPic());
        confirmOrderItemBean.setCommodityName(inventory.getCommodityName());
        confirmOrderItemBean.setBelongId(commodityBean.getBelongId());
        confirmOrderItemBean.setBelongType(commodityBean.getBelongType());
        confirmOrderItemBean.setCommodityId(inventory.getCommodityId());
        confirmOrderItemBean.setInventoryId(inventory.getInventoryId());
        confirmOrderItemBean.setPurchaseNum(purchaseNum);

        return confirmOrderItemBean;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getBelongId() {
        return belongId;
    }

    public void setBelongId(Integer belongId) {
        this.belongId = belongId;
    }

    public Integer getBelongType() {
        return belongType;
    }

    public void setBelongType(Integer belongType) {
        this.belongType = belongType;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
