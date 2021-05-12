package com.goldensky.framework.bean;

import com.goldensky.framework.constant.NetCodeConstant;

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
    private int status;
    private T data;

    /**
     * 判断是否是逻辑上的成功
     *
     * @return 逻辑是否成功
     */
    public boolean isLogicSuccess() {
        return code == NetCodeConstant.CODE_SUCCESS;
    }

    /**
     * 判断是否逻辑失败
     *
     * @return 逻辑是否失败
     */
    public boolean isLogicFailed() {
        return code != NetCodeConstant.CODE_SUCCESS;
    }

    public boolean isAccountFrozen() {
        return code == NetCodeConstant.CODE_ACCOUNT_FROZEN;
    }

    public boolean isMultiDeviceLogin() {
        return code == NetCodeConstant.CODE_MULTI_DEVICE;
    }

    public boolean isTokenExpired() {
        return code == NetCodeConstant.CODE_TOKEN_EXPIRED;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
