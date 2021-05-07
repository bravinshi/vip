package com.goldensky.framework.util;

import java.util.Collection;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/7 11:58
 * 包名： com.goldensky.framework.util
 * 类说明：
 */
public class CollectionUtils {

    public static boolean nullOrEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }
}
