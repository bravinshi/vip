package com.goldensky.vip.bean;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/26 16:19
 * 包名： com.jintian.jintianhezong.bean
 * 类说明：
 */
public class GoodsAttributeBean {
    private Integer id;
    private Integer attributesid;
    private Integer ismust;
    private String fieldtype;
    private String fieldname;
    private String extendfieldname;
    private String fieldvalue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttributesid() {
        return attributesid;
    }

    public void setAttributesid(Integer attributesid) {
        this.attributesid = attributesid;
    }

    public Integer getIsmust() {
        return ismust;
    }

    public void setIsmust(Integer ismust) {
        this.ismust = ismust;
    }

    public String getFieldtype() {
        return fieldtype;
    }

    public void setFieldtype(String fieldtype) {
        this.fieldtype = fieldtype;
    }

    public String getFieldname() {
        return fieldname;
    }

    public void setFieldname(String fieldname) {
        this.fieldname = fieldname;
    }

    public String getExtendfieldname() {
        return extendfieldname;
    }

    public void setExtendfieldname(String extendfieldname) {
        this.extendfieldname = extendfieldname;
    }

    public String getFieldvalue() {
        return fieldvalue;
    }

    public void setFieldvalue(String fieldvalue) {
        this.fieldvalue = fieldvalue;
    }
}
