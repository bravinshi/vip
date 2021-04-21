package com.goldensky.vip.bean;

public class RecentBrowseBean {
    private Integer image;
    private Integer position;

    public RecentBrowseBean(Integer image, Integer position) {
        this.image = image;
        this.position = position;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
