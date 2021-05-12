package com.goldensky.vip.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class HomeBean implements MultiItemEntity {
    @Override
    public int getItemType() {
        return 0;
    }

    public static  class  ProductBean implements MultiItemEntity {
        @Override
        public int getItemType() {
            return 0;
        }
    }

}
