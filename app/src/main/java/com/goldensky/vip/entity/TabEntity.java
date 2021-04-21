package com.goldensky.vip.entity;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/21 14:09
 * 包名： com.goldensky.vip.entity
 * 类说明：
 */
public class TabEntity implements CustomTabEntity {
    private String title;

    public TabEntity(String title) {
        this.title = title;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return 0;
    }

    @Override
    public int getTabUnselectedIcon() {
        return 0;
    }
}
