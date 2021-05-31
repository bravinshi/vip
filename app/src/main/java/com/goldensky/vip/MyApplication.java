package com.goldensky.vip;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.goldensky.framework.net.ApiConfiguration;
import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.framework.util.AppUtils;
import com.goldensky.framework.util.GsonUtils;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.framework.util.Utils;
import com.goldensky.vip.helper.AccountHelper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/26 11:33
 * 包名： com.goldensky.entity
 * 类说明：
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ProcessLifecycleOwner.get().getLifecycle().addObserver(new ProcessLifecycleObserver());

        ApiConfiguration apiConfiguration = new ApiConfiguration();
        //正式环境
        apiConfiguration.setBaseUrl("https://openapi.jtmsh.com/");
        // 财哥
//        apiConfiguration.setBaseUrl("http://testopenapi.jtmsh.com/");
//        apiConfiguration.setBaseUrl("http://172.25.0.249:9999/");
        //王珂
//        apiConfiguration.setBaseUrl("http://172.25.0.187:9999/");
        //任晓阳
//        apiConfiguration.setBaseUrl("http://172.25.0.84:9999/");
        // 马晓伟
//        apiConfiguration.setBaseUrl("http://172.25.0.178:9999/");

        //正式
//        apiConfiguration.setBaseUrl("https://openapi.jtmsh.com/");
        //国新 172.25.0.145
//        apiConfiguration.setBaseUrl("http://172.25.0.183:9999/");

        // 设置gson
        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
        Gson gson = gsonBuilder.create();
        apiConfiguration.setGson(gson);
        GsonUtils.init(gson);
        // 设置拦截器
        apiConfiguration.setInterceptors(new ArrayList<Interceptor>(){{
            add(createHeaderInterceptor());
        }});
        RetrofitAgent.config(apiConfiguration);

        Utils.init(this);
        AccountHelper.deserializationAgent();
    }

    /**
     * 请求头拦截器
     */
    private static Interceptor createHeaderInterceptor() {
        return chain -> {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();

            String userId = AccountHelper.getUserId();
            if (StringUtils.isTrimEmpty(userId)) {
                userId = "";
            }
            builder.addHeader("vip_user_id", userId);
            builder.addHeader("Authorization", AccountHelper.getToken());
            builder.addHeader("app_version_code", AppUtils.getAppVersionCode() + "");// 101
            builder.addHeader("app_version", AppUtils.getAppVersionName());// 1.0.1
            builder.addHeader("app_client", "vip");
            builder.addHeader("app_platform", "android");
            try {
                Response mResponse = chain.proceed(builder.build());
                return mResponse;
            } catch (SocketTimeoutException exception) {
                throw new SocketTimeoutException("网络超时");
            }
        };
    }

    public static class ProcessLifecycleObserver implements LifecycleObserver {
        /**
         * 当app变成后台进程或者退出调用
         * 此方法一次app进入退出只会调用一次
         */
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void exitAppListener(){
            AccountHelper.serializationAgent();
        }
    }
}
