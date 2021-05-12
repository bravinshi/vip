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
public class CommodityBean {

    @SerializedName("adminid")
    private Integer adminId;
    @SerializedName("badevaluate")
    private Integer badEvaluate;
    @SerializedName("belongid")
    private Integer belongId;
    @SerializedName("belongtype")
    private Integer belongType;
    @SerializedName("commodityicon")
    private String commodityIcon;
    @SerializedName("commodityname")
    private String commodityName;
    @SerializedName("commodityid")
    private Integer commodityId;
    @SerializedName("commodityisdel")
    private Integer commodityIsDel;
    @SerializedName("commoditynum")
    private Integer commodityNum;
    @SerializedName("commoditytype")
    private Integer commodityType;
    @SerializedName("commoditystatus")
    private Integer commodityStatus;
    @SerializedName("goodevaluate")
    private Integer goodEvaluate;
    @SerializedName("haspics")
    private Integer hasPics;
    @SerializedName("integralstatus")
    private Integer integralStatus;
    @SerializedName("onshelfstatus")
    private Integer onShelfStatus;
    @SerializedName("ordinaryevaluate")
    private Integer ordinaryEvaluate;
    @SerializedName("recommendstatus")
    private Integer recommendStatus;
    @SerializedName("salenum")
    private Integer saleNum;
    @SerializedName("secondarycategoryid")
    private Integer secondaryCategoryId;
    @SerializedName("thirdcategoryid")
    private Integer thirdCategoryId;
    @SerializedName("topcategoryid")
    private Integer topCategoryId;
    @SerializedName("commodityoldprice")
    private Double commodityOldPrice;
    @SerializedName("commodityprice")
    private Double commodityPrice;
    @SerializedName("createtime")
    private Date createTime;
    @SerializedName("commoditypiclist")
    private List<CommodityPicBean> commodityPicList;
    @SerializedName("commoditydesc")
    private String commodityDesc;

    private List<InventoryBean> commodityInventoryList;

    public List<InventoryBean> getCommodityInventoryList() {
        return commodityInventoryList;
    }

    public void setCommodityInventoryList(List<InventoryBean> commodityInventoryList) {
        this.commodityInventoryList = commodityInventoryList;
    }

    // {
    //    "code": 0,
    //    "data": {
    //        "commodity": {
    //            "adminid": 1,
    //            "badevaluate": 0,
    //            "belongid": 1,
    //            "belongtype": 0,
    //            "certificationcontent": "{"id":683,"name":"平台代理商品模板","templateType":null,"templateItemFormList":[{"id":2796,"templateid":683,"fieldtype":"图片","fieldname":"说明","fieldvalue":"https://file.jtmsh.com/data/jintianhezong/file/img/2021-01-21/KO1611209562798.png","ismust":1}]}",
    //            "commoditydesc": "<p><img src="https://file.jtmsh.com/data/jintianhezong/file/img/2021-04-26/EN1619424837364_big.jpg"></p>",
    //            "commodityfrom": "",
    //            "commodityicon": "https://file.jtmsh.com/data/jintianhezong/file/img/2021-01-21/AP1611209619817.jpg",
    //            "commodityid": 140,
    //            "commodityisdel": 0,
    //            "commodityname": "陈源泰 2012级 福鼎老白茶一级寿眉 350g/饼",
    //            "commoditynum": 99999,
    //            "commodityoldprice": 290.00,
    //            "commoditypiclist": [
    //                {
    //                    "commodityid": 140,
    //                    "commoditypicid": 1403,
    //                    "picurl": "https://file.jtmsh.com/data/jintianhezong/file/img/2021-01-21/AP1611209619817.jpg",
    //                    "recoursetype": 0
    //                },
    //                {
    //                    "commodityid": 140,
    //                    "commoditypicid": 1404,
    //                    "picurl": "https://file.jtmsh.com/data/jintianhezong/file/img/2021-01-21/OG1611209621497.jpg",
    //                    "recoursetype": 0
    //                },
    //                {
    //                    "commodityid": 140,
    //                    "commoditypicid": 1405,
    //                    "picurl": "https://file.jtmsh.com/data/jintianhezong/file/img/2021-01-21/OX1611209623216.jpg",
    //                    "recoursetype": 0
    //                },
    //                {
    //                    "commodityid": 140,
    //                    "commoditypicid": 1406,
    //                    "picurl": "https://file.jtmsh.com/data/jintianhezong/file/img/2021-01-21/IK1611209625029.jpg",
    //                    "recoursetype": 0
    //                }
    //            ],
    //            "commodityprice": 580.00,
    //            "commoditystatus": 1,
    //            "commoditytype": 0,
    //            "createtime": "2021-01-21T06:13:54.000+0000",
    //            "goodevaluate": 5,
    //            "haspics": 0,
    //            "integralstatus": 0,
    //            "onshelfstatus": 1,
    //            "ordinaryevaluate": 1,
    //            "recommendstatus": 0,
    //            "salenum": 0,
    //            "secondarycategoryid": 330,
    //            "thirdcategoryid": 2909,
    //            "topcategoryid": 25
    //        },
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
    //    "message": "success",
    //    "status": 1
    //}


    public String getCommodityDesc() {
        return commodityDesc;
    }

    public void setCommodityDesc(String commodityDesc) {
        this.commodityDesc = commodityDesc;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Integer getBadEvaluate() {
        return badEvaluate;
    }

    public void setBadEvaluate(Integer badEvaluate) {
        this.badEvaluate = badEvaluate;
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

    public String getCommodityIcon() {
        return commodityIcon;
    }

    public void setCommodityIcon(String commodityIcon) {
        this.commodityIcon = commodityIcon;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public Integer getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(Integer commodityId) {
        this.commodityId = commodityId;
    }

    public Integer getCommodityIsDel() {
        return commodityIsDel;
    }

    public void setCommodityIsDel(Integer commodityIsDel) {
        this.commodityIsDel = commodityIsDel;
    }

    public Integer getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(Integer commodityNum) {
        this.commodityNum = commodityNum;
    }

    public Integer getCommodityType() {
        return commodityType;
    }

    public void setCommodityType(Integer commodityType) {
        this.commodityType = commodityType;
    }

    public Integer getCommodityStatus() {
        return commodityStatus;
    }

    public void setCommodityStatus(Integer commodityStatus) {
        this.commodityStatus = commodityStatus;
    }

    public Integer getGoodEvaluate() {
        return goodEvaluate;
    }

    public void setGoodEvaluate(Integer goodEvaluate) {
        this.goodEvaluate = goodEvaluate;
    }

    public Integer getHasPics() {
        return hasPics;
    }

    public void setHasPics(Integer hasPics) {
        this.hasPics = hasPics;
    }

    public Integer getIntegralStatus() {
        return integralStatus;
    }

    public void setIntegralStatus(Integer integralStatus) {
        this.integralStatus = integralStatus;
    }

    public Integer getOnShelfStatus() {
        return onShelfStatus;
    }

    public void setOnShelfStatus(Integer onShelfStatus) {
        this.onShelfStatus = onShelfStatus;
    }

    public Integer getOrdinaryEvaluate() {
        return ordinaryEvaluate;
    }

    public void setOrdinaryEvaluate(Integer ordinaryEvaluate) {
        this.ordinaryEvaluate = ordinaryEvaluate;
    }

    public Integer getRecommendStatus() {
        return recommendStatus;
    }

    public void setRecommendStatus(Integer recommendStatus) {
        this.recommendStatus = recommendStatus;
    }

    public Integer getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(Integer saleNum) {
        this.saleNum = saleNum;
    }

    public Integer getSecondaryCategoryId() {
        return secondaryCategoryId;
    }

    public void setSecondaryCategoryId(Integer secondaryCategoryId) {
        this.secondaryCategoryId = secondaryCategoryId;
    }

    public Integer getThirdCategoryId() {
        return thirdCategoryId;
    }

    public void setThirdCategoryId(Integer thirdCategoryId) {
        this.thirdCategoryId = thirdCategoryId;
    }

    public Integer getTopCategoryId() {
        return topCategoryId;
    }

    public void setTopCategoryId(Integer topCategoryId) {
        this.topCategoryId = topCategoryId;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<CommodityPicBean> getCommodityPicList() {
        return commodityPicList;
    }

    public void setCommodityPicList(List<CommodityPicBean> commodityPicList) {
        this.commodityPicList = commodityPicList;
    }
}
