package com.goldensky.entity.enumerate;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/11 17:25
 * 包名： com.goldensky.together.enumerate
 * 类说明：
 */
public enum LoginTypeEnum {

    PASSWORD("1"),
    VERIFICATION_CODE("2");


    LoginTypeEnum(String value) {
        this.value = value;
    }

    public String value;
}
