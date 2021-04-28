package com.goldensky.vip;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.goldensky.framework.net.ApiConfiguration;
import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.framework.util.Utils;
import com.goldensky.vip.helper.AccountHelper;
import com.google.gson.Gson;

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
        // 财哥
//        apiConfiguration.setBaseUrl("http://testopenapi.jtmsh.com/");
        // 王涛
//        apiConfiguration.setBaseUrl("http://172.25.0.187:9999/");
        // 马晓伟
        apiConfiguration.setBaseUrl("http://172.25.0.159:9999/");
        Gson gson = new Gson();
        apiConfiguration.setGson(gson);
        RetrofitAgent.config(apiConfiguration);

        Utils.init(this);
        AccountHelper.deserializationAgent();
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
