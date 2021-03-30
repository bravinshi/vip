package com.goldensky.entity.base.error;

import com.goldensky.framework.bean.NetResponse;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/10 14:13
 * 包名： com.goldensky.basekit.error
 * 类说明：在需要单独处理逻辑上的错误时实现此接口
 */
public interface FailCallback {

    /**
     * 业务逻辑错误的回调方法
     *
     * @param netResponse 错误信息
     */
    void onFail(NetResponse netResponse);
}
