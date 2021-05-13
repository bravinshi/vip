package com.goldensky.vip.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class HomeBean implements MultiItemEntity {

    private int homeItemType;
    private List<String> lbList;

    public HomeBean(int homeItemType) {
        this.homeItemType = homeItemType;
    }

    public List<String> getLbList() {
        return lbList;
    }

    public void setLbList(List<String> lbList) {
        this.lbList = lbList;
    }


    @Override
    public int getItemType() {
        return homeItemType;
    }

    public static  class  ProductBean implements MultiItemEntity {
        @Override
        public int getItemType() {
            return 0;
        }
    }

}
