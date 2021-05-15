package com.goldensky.vip.entity;

import com.goldensky.vip.bean.CommodityBean;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/6 14:19
 * 包名： com.goldensky.vip.entity
 * 类说明：
 */
public class NormalGoodsEntity {
    private Integer goodsId;
    private String image;
    private String title;
    private Double price;

    public static NormalGoodsEntity fromCommodity(CommodityBean commodityBean) {
        NormalGoodsEntity normalGoodsEntity = new NormalGoodsEntity();

        normalGoodsEntity.setGoodsId(commodityBean.getCommodityId());
        normalGoodsEntity.setImage(commodityBean.getCommodityIcon());
        normalGoodsEntity.setTitle(commodityBean.getCommodityName());
        normalGoodsEntity.setPrice(commodityBean.getCommodityPrice());

        return normalGoodsEntity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
