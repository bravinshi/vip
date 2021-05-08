package com.goldensky.vip;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.net.error.NetErrorHandler;
import com.goldensky.framework.util.ActivityUtils;
import com.goldensky.framework.util.AppUtils;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/26 15:50
 * 包名： com.goldensky.vip
 * 类说明：自定义统一处理错误异常的处理类
 */
public class CustomNetErrorHandler implements NetErrorHandler {

    public static class ErrorHandlerHolder {
        public static NetErrorHandler netErrorHandler = new CustomNetErrorHandler();
    }

    public static NetErrorHandler getInstance() {
        return ErrorHandlerHolder.netErrorHandler;
    }

    private CustomNetErrorHandler() {
    }

    /**
     * 统一处理错误类型的方法
     *
     * @param error 错误信息
     * @return 是否消费这个错误
     */
    @Override
    public boolean onFail(NetResponse error) {
        if (error == null) {
            return false;
        }

        // token过期  账号冻结 多设备登录 都跳转到登录页
//        if (error.isAccountFrozen()
//        || error.isMultiDeviceLogin()
//        || error.isTokenExpired()) {
//            Starter.startLoginActivity(ActivityUtils.getTopActivity(), null);
//            return true;
//        }

        return false;
    }
}
