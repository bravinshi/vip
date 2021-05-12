package com.goldensky.framework.util;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/12 11:30
 * 包名： com.goldensky.framework.util
 * 类说明：
 */
public class GsonUtils {
    private static Gson gson;

    public static void init(@NonNull final Gson gson) {
        GsonUtils.gson = gson;
    }

    public static  <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        return gson.fromJson(json, typeOfT);
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
