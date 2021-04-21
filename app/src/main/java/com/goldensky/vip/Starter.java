package com.goldensky.vip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.goldensky.vip.activity.account.ForgetPasswordActivity;
import com.goldensky.vip.activity.account.LoginActivity;
import com.goldensky.vip.activity.goods.RecommendActivity;
import com.goldensky.vip.activity.goods.GoodsDetailActivity;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/26 10:35
 * 包名： com.goldensky.entity
 * 类说明：
 */
public class Starter {

    /**
     * 启动登录页
     */
    public static void startLoginActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 启动MainActivity
     */
    public static void startMainActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 启动忘记密码界面
     */
    public static void startForgetPasswordActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 启动商品详情页
     */
    public static void startGoodsDetailActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 启动推荐
     */
    public static void startRecommendActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RecommendActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
