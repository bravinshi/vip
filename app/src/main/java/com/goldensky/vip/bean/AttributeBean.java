package com.goldensky.vip.bean;

import com.google.gson.JsonObject;

import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/24 16:31
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class AttributeBean {

    private Integer id;
    private Integer templatetype;
    private String name;
    private List<JsonObject> templateItemFormList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplatetype() {
        return templatetype;
    }

    public void setTemplatetype(Integer templatetype) {
        this.templatetype = templatetype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JsonObject> getTemplateItemFormList() {
        return templateItemFormList;
    }

    public void setTemplateItemFormList(List<JsonObject> templateItemFormList) {
        this.templateItemFormList = templateItemFormList;
    }
}
