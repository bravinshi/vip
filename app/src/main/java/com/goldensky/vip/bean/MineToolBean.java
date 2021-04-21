package com.goldensky.vip.bean;

public class MineToolBean {
    private Integer sign;
    private String nick;

    public Integer getSign() {
        return sign;
    }


    public String getNick() {
        return nick;
    }


    public MineToolBean(Integer sign, String nick) {
        this.sign = sign;
        this.nick = nick;
    }
}
