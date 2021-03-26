package com.goldensky.framework.net;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/26 10:38
 * 包名： com.goldensky.framework.net
 * 类说明：
 */
public class ApiConfiguration {

    private String baseUrl;

    private List<Interceptor> interceptors = new ArrayList<>();

    private Gson gson;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public List<Interceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public Gson getGson() {
        return gson;
    }

    public void setGson(Gson gson) {
        this.gson = gson;
    }
}
