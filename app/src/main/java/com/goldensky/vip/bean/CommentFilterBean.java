package com.goldensky.vip.bean;

public class CommentFilterBean {
    private String title;
    private Integer count;
    private boolean isSelected;
    private String txt;

    public CommentFilterBean(String title, boolean isSelected, Integer count) {
        this.title = title;
        this.isSelected = isSelected;
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        if (count == null) return 0;
        return count;
    }

    public void setCount(Integer count) {
        this.count = (count == null) ? 0 : count;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getTxt() {
        if (count == 0 || count == null) return title;
        if (count >= 1000) return  title + "(999+)";
        return title + "(" + count + ")";
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
}
