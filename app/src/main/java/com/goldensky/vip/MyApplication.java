package com.goldensky.vip;

import android.app.Application;

import com.goldensky.framework.net.ApiConfiguration;
import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.framework.util.Utils;
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
        apiConfiguration.setBaseUrl("http://testopenapi.jtmsh.com/");
        // 涛哥
//        apiConfiguration.setBaseUrl("http://25155vf240.qicp.vip:14022/");
        Gson gson = new Gson();
        apiConfiguration.setGson(gson);
        RetrofitAgent.config(apiConfiguration);

        Utils.init(this);
    }
}
