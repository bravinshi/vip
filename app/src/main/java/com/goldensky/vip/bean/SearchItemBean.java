package com.goldensky.vip.bean;

public class SearchItemBean {
    private Integer pic;
    private String text;

    public SearchItemBean(Integer pic, String text) {
        this.pic = pic;
        this.text = text;
    }

    public Integer getPic() {
        return pic;
    }

    public void setPic(Integer pic) {
        this.pic = pic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
