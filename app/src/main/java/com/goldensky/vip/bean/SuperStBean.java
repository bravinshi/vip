package com.goldensky.vip.bean;

public class SuperStBean {
    String userpic;
    String storename;
    String enterprisename;

    public SuperStBean(String userpic, String storename, String enterprisename) {
        this.userpic = userpic;
        this.storename = storename;
        this.enterprisename = enterprisename;
    }

    public String getUserpic() {
        return userpic;
    }

    public void setUserpic(String userpic) {
        this.userpic = userpic;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getEnterprisename() {
        return enterprisename;
    }

    public void setEnterprisename(String enterprisename) {
        this.enterprisename = enterprisename;
    }
}
