package com.goldensky.framework.bean;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/10 14:21
 * 包名： com.goldensky.basekit.bean
 * 类说明：
 */
public class NetResponse<T> {
    private String message;
    private int code;
    private T data;

//    private static final int CODE_SUCCESS = 0;// 业务逻辑上的成功
//    private static final int CODE_ACCOUNT_FROZEN = 609;// 账号被冻结
//    private static final int CODE_MULTI_DEVICE = 602;    // 多设备登录

//    /**
//     * 判断是否是逻辑上的成功
//     *
//     * @return 逻辑是否成功
//     */
//    public boolean isLogicSuccess() {
//        return code == CODE_SUCCESS;
//    }
//
//    /**
//     * 判断是否逻辑失败
//     *
//     * @return 逻辑是否失败
//     */
//    public boolean isLogicFailed() {
//        return code != CODE_SUCCESS;
//    }
//
//    public boolean isAccountFrozen() {
//        return code == CODE_ACCOUNT_FROZEN;
//    }
//
//    public boolean isMultiDeviceLogin() {
//        return code == CODE_MULTI_DEVICE;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
