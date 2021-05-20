package com.goldensky.vip.bean;

public class CommentProductBean {
    private String secondorderid;
    private String inventorypic;
    private String inventory; //规格
    private String commodityname;
    private Integer isevaluate;

    public CommentProductBean(String secondorderid, String inventorypic, String inventory, String commodityname, Integer isevaluate, String commodityid) {
        this.secondorderid = secondorderid;
        this.inventorypic = inventorypic;
        this.inventory = inventory;
        this.commodityname = commodityname;
        this.isevaluate = isevaluate;
        this.commodityid = commodityid;
    }

    public Integer getIsevaluate() {
        return isevaluate;
    }

    public void setIsevaluate(Integer isevaluate) {
        this.isevaluate = isevaluate;
    }

    public String getSecondorderid() {
        return secondorderid;
    }

    public void setSecondorderid(String secondorderid) {
        this.secondorderid = secondorderid;
    }

    public String getInventorypic() {
        return inventorypic;
    }

    public void setInventorypic(String inventorypic) {
        this.inventorypic = inventorypic;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public String getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(String commodityid) {
        this.commodityid = commodityid;
    }

    private String commodityid;


}
