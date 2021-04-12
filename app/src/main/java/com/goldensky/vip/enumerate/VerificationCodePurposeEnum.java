package com.goldensky.vip.enumerate;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/11 17:25
 * 包名： com.goldensky.together.enumerate
 * 类说明：
 */
public enum VerificationCodePurposeEnum {
    // 1:登陆 2:注册用户 不需要判断是否存在 3:修改密码 不需要判断是否存在 4更换手机号原手机号 不需要判断是否存在
    // 5更换手机号新手机号 6付款（充值） 7结款（提现） 8转账
    // 4和5是在更换手机号时，既要验证旧手机，也要验证新手机
    LOGIN("1"),
    REGISTER("2"),
    CHANGE_PASSWORD("3"),
    CHANGE_PHONE_NUMBER_OLD_PHONE("4"),
    CHANGE_PHONE_NUMBER_NEW_PHONE("5"),
    PAY("6"),
    WITHDRAW("7"),
    TRANSFER("8");


    VerificationCodePurposeEnum(String value) {
        this.value = value;
    }

    public String value;
}
