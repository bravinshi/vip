package com.goldensky.vip.bean;

import com.goldensky.framework.util.GsonUtils;
import com.goldensky.framework.util.StringUtils;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/7 10:18
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class InventoryBean {

    @SerializedName("buyfromnum")
    private Integer buyFromNum;
    @SerializedName("commodityid")
    private Integer commodityId;
    @SerializedName("companytype")
    private Integer companyType;
    @SerializedName("integralstatus")
    private Integer integralStatus;
    @SerializedName("inventoryid")
    private Integer inventoryId;
    @SerializedName("inventorynum")
    private Integer inventoryNum;
    @SerializedName("commodityname")
    private String commodityName;
    @SerializedName("inputdata")
    private String inputData;
    private String inventory;
    @SerializedName("inventorypic")
    private String inventoryPic;
    @SerializedName("secinventory")
    private String secInventory;
    @SerializedName("commodityoldprice")
    private Double commodityOldPrice;
    @SerializedName("commodityprice")
    private Double commodityPrice;
    @SerializedName("costprice")
    private Double costPrice;
    @SerializedName("primaryagentprice")
    private Double primaryAgentPrice;
    @SerializedName("profitproportion")
    private Double profitProportion;
    @SerializedName("secondaryagentprice")
    private Double secondaryAgentPrice;

    public String getSpecificationForShow() {
        // 展示用规格
        if (!StringUtils.isTrimEmpty(inventory)) {
            JsonObject jsonObject = GsonUtils.fromJson(inventory, JsonObject.class);
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

            return stringBuilder.toString();
        }
        return "";
    }

    public Integer getBuyFromNum() {
        return buyFromNum;
    }

    public void setBuyFromNum(Integer buyFromNum) {
        this.buyFromNum = buyFromNum;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public Integer getIntegralStatus() {
        return integralStatus;
    }

    public void setIntegralStatus(Integer integralStatus) {
        this.integralStatus = integralStatus;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(Integer inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(String inputData) {
        this.inputData = inputData;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getInventoryPic() {
        return inventoryPic;
    }

    public void setInventoryPic(String inventoryPic) {
        this.inventoryPic = inventoryPic;
    }

    public String getSecInventory() {
        return secInventory;
    }

    public void setSecInventory(String secInventory) {
        this.secInventory = secInventory;
    }

    public Double getCommodityOldPrice() {
        return commodityOldPrice;
    }

    public void setCommodityOldPrice(Double commodityOldPrice) {
        this.commodityOldPrice = commodityOldPrice;
    }

    public Double getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(Double commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    public Double getPrimaryAgentPrice() {
        return primaryAgentPrice;
    }

    public void setPrimaryAgentPrice(Double primaryAgentPrice) {
        this.primaryAgentPrice = primaryAgentPrice;
    }

    public Double getProfitProportion() {
        return profitProportion;
    }

    public void setProfitProportion(Double profitProportion) {
        this.profitProportion = profitProportion;
    }

    public Double getSecondaryAgentPrice() {
        return secondaryAgentPrice;
    }

    public void setSecondaryAgentPrice(Double secondaryAgentPrice) {
        this.secondaryAgentPrice = secondaryAgentPrice;
    }

    //        "inventories": [
    //            {
    //                "buyfromnum": 11,
    //                "commodityid": 140,
    //                "commodityname": "陈源泰 2012级 福鼎老白茶一级寿眉 350g/饼",
    //                "commodityoldprice": 580.00,
    //                "commodityprice": 435.00,
    //                "companytype": 0,
    //                "costprice": 162.80,
    //                "inputdata": "[{"key":"饼","val":"350g*1饼/盒"}]",
    //                "integralstatus": 0,
    //                "inventory": "{"饼":"350g*1饼/盒"}",
    //                "inventoryid": 978,
    //                "inventorynum": 99999,
    //                "inventorypic": "https://file.jtmsh.com/data/jintianhezong/file/img/2021-01-21/AJ1611209589352.jpg",
    //                "primaryagentprice": 290.00,
    //                "profitproportion": 0.6000,
    //                "secinventory": "[{"饼":"350g*1饼/盒"}]",
    //                "secondaryagentprice": 350.00
    //            }
    //        ]
    //    },
}
