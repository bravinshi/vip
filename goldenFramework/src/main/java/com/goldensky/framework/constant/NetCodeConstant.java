package com.goldensky.framework.constant;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/26 15:24
 * 包名： com.goldensky.entity.constant
 * 类说明：
 */
public class NetCodeConstant {
    // service
    public static final int CODE_SUCCESS = 0;// 业务逻辑上的成功
    public static final int CODE_ACCOUNT_FROZEN = 609;// 账号被冻结
    public static final int CODE_MULTI_DEVICE = 602;    // 多设备登录
    public static final int CODE_TOKEN_EXPIRED = 401;    // token过期

    // custom
    public static final int CODE_EXCEPTION_DEFAULT = Integer.MIN_VALUE;


}
