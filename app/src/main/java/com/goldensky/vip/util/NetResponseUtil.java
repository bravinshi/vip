package com.goldensky.vip.util;

import com.goldensky.vip.constant.NetCodeConstant;
import com.goldensky.framework.bean.NetResponse;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/26 15:23
 * 包名： com.goldensky.entity.util
 * 类说明：
 */
public class NetResponseUtil {

    public static boolean isSuccess(NetResponse netResponse) {
        return netResponse.getCode() == NetCodeConstant.CODE_SUCCESS;
    }
}
