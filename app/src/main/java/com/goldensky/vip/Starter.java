package com.goldensky.vip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.goldensky.vip.activity.account.ForgetPasswordActivity;
import com.goldensky.vip.activity.account.LoginActivity;
import com.goldensky.vip.activity.account.SetPasswordActivity;
import com.goldensky.vip.activity.article.ArticleDetailActivity;
import com.goldensky.vip.activity.customer.CustomerServiceActivity;
import com.goldensky.vip.activity.goods.GoodsCommentActivity;
import com.goldensky.vip.activity.goods.GoodsDetailActivity;
import com.goldensky.vip.activity.goods.HotTodayActivity;
import com.goldensky.vip.activity.goods.PackageCustomizationActivity;
import com.goldensky.vip.activity.goods.RecommendActivity;
import com.goldensky.vip.activity.goods.SearchActivity;
import com.goldensky.vip.activity.goods.SpecialPriceAreaActivity;
import com.goldensky.vip.activity.mine.focus.CircleFocusActivity;
import com.goldensky.vip.activity.mine.focus.GoodsFocusActivity;
import com.goldensky.vip.activity.mine.focus.RecentBrowseActivity;
import com.goldensky.vip.activity.mine.settings.AboutGoldenDaysActivity;
import com.goldensky.vip.activity.mine.settings.ChangeNickActivity;
import com.goldensky.vip.activity.mine.settings.ChangePWDActivity;
import com.goldensky.vip.activity.mine.settings.PersonalDetailsActivity;
import com.goldensky.vip.activity.mine.settings.SettingsActivity;
import com.goldensky.vip.activity.mine.tools.CouponActivity;
import com.goldensky.vip.activity.mine.tools.InviteCompanyActivity;
import com.goldensky.vip.activity.mine.tools.adress.EditAddressActivity;
import com.goldensky.vip.activity.mine.tools.adress.MyAddressActivity;
import com.goldensky.vip.activity.mine.tools.ShareToFriendActivity;
import com.goldensky.vip.activity.mine.tools.adress.NewAddressActivity;
import com.goldensky.vip.activity.order.OrderCommentActivity;
import com.goldensky.vip.activity.order.OrderListActivity;


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
     * 启动设置界面
     */
    public static void startSettingsActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SettingsActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 启动个人信息页面
     */
    public static void startPersonDetailActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PersonalDetailsActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 启动修改密码页面
     */
    public static void startChangePWDActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ChangePWDActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 启动修改昵称页面
     */
    public static void startChangeNickActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ChangeNickActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 启动商品关注页面
     */
    public static void startGoodsFocusActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GoodsFocusActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 启动圈子关注页面
     */
    public static void startCircleFocusActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, CircleFocusActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 启动最近浏览页面
     */
    public static void startRecentBrowseActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RecentBrowseActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 启动订单列表页面
     */
    public static void startOrderListActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, OrderListActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 启动优惠券页面
     */
    public static void startCouponActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, CouponActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 启动我的地址页面
     */
    public static void startMyAddressActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MyAddressActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 启动好友分享页面
     */
    public static void startShareToFriendActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ShareToFriendActivity.class);
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
     * 启动邀请企业页面
     */
    public static void startInviteCompanyActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, InviteCompanyActivity.class);
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

    /**
     * 启动客服页面
     */
    public static void startCustomerServiceActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, CustomerServiceActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /*
     * 启动搜索
     */
    public static void startSearchActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SearchActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 今日爆款
     */
    public static void startHotTodayActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, HotTodayActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 个性推荐
     */
    public static void startCustomActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PackageCustomizationActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 优惠专区
     */
    public static void startYhzqActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SpecialPriceAreaActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 文章详情
     */
    public static void startArticleDetailActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 新建地址
     */
    public static void startNewAddressActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, NewAddressActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void startSetPasswordActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SetPasswordActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 修改收货地址
     */
    public static void startEditAddressActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, EditAddressActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    //商品评价全部
    public static void startGoodsCommentActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GoodsCommentActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    //订单发表评价
    public static void startOrderCommentActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, OrderCommentActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    //关于今天合纵
    public static void startAboutGoldenDaysActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AboutGoldenDaysActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
