package com.goldensky.framework.net;

import android.util.Log;

import com.goldensky.framework.BuildConfig;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/26 10:53
 * 包名： com.goldensky.framework.net
 * 类说明：
 */
public class RetrofitAgent {

    public static final int MAX_CORE_SIZE = Runtime.getRuntime().availableProcessors() + 1;
    public static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(MAX_CORE_SIZE,
            MAX_CORE_SIZE * 10, 1,
            TimeUnit.SECONDS, new LinkedBlockingDeque<>());

    private static Retrofit retrofit;

    public static <T> T create(Class<T> clazz) {
        if (retrofit == null) {
            throw new IllegalStateException("should call config method first at Application");
        }
        return retrofit.create(clazz);
    }

    public static void config(ApiConfiguration apiConfiguration) {
        //日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {
            //打印retrofit日志
            Log.e("RetrofitLog", message);
        });

        loggingInterceptor.setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        // 创建
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS);

        for (Interceptor interceptor : apiConfiguration.getInterceptors()) {
            builder.addInterceptor(interceptor);
        }

        OkHttpClient httpClient = builder.build();

        GsonConverterFactory gsonConverterFactory
                = apiConfiguration.getGson() == null
                ? GsonConverterFactory.create()
                : GsonConverterFactory.create(apiConfiguration.getGson());

        retrofit = new Retrofit.Builder()
                .baseUrl(apiConfiguration.getBaseUrl())
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory
                        .createWithScheduler(Schedulers.from(EXECUTOR)))
                .client(httpClient)
                .build();
    }

}
