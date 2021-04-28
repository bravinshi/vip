package com.goldensky.vip;

import android.app.Application;

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

    /**
     * 方法不可靠
     *
     * This method is for use in emulated process environments.  It will
     * never be called on a production Android device, where processes are
     * removed by simply killing them; no user code (including this callback)
     * is executed when doing so.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        AccountHelper.serializationAgent();
    }
}
