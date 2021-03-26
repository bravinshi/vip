package com.goldensky.framework.net.error;


import com.goldensky.framework.bean.NetResponse;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/10 14:13
 * 包名： com.goldensky.basekit.error
 * 类说明：处理网络错误的类应该继承此接口
 */
public interface NetErrorHandler {

    /**
     * 处理错误的回调方法
     *
     * @param error 错误信息
     */
    boolean onFail(NetResponse error);

}
