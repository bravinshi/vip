package com.goldensky.vip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.goldensky.vip.activity.account.ForgetPasswordActivity;
import com.goldensky.vip.activity.account.LoginActivity;
import com.goldensky.vip.activity.article.ArticleDetailActivity;
import com.goldensky.vip.activity.customer.CustomerServiceActivity;
import com.goldensky.vip.activity.customer.VIPTalkActivity;
import com.goldensky.vip.activity.goods.HotTodayActivity;
import com.goldensky.vip.activity.goods.PackageCustomizationActivity;
import com.goldensky.vip.activity.goods.SearchActivity;
import com.goldensky.vip.activity.goods.SpecialPriceAreaActivity;
import com.goldensky.vip.activity.message.LiveActivity;
import com.goldensky.vip.activity.message.MessageActivity;
import com.goldensky.vip.activity.message.PromoteActivity;
import com.goldensky.vip.activity.message.ScienceActivity;
import com.goldensky.vip.activity.message.SystemMessageActivity;
import com.goldensky.vip.activity.message.TradeLogisticsActivity;
import com.goldensky.vip.activity.mine.focus.CircleFocusActivity;
import com.goldensky.vip.activity.mine.focus.GoodsFocusActivity;
import com.goldensky.vip.activity.mine.focus.RecentBrowseActivity;
import com.goldensky.vip.activity.mine.settings.ChangeNickActivity;
import com.goldensky.vip.activity.mine.settings.ChangePWDActivity;
import com.goldensky.vip.activity.mine.settings.PersonalDetailsActivity;
import com.goldensky.vip.activity.mine.settings.SettingsActivity;
import com.goldensky.vip.activity.mine.tools.CouponActivity;
import com.goldensky.vip.activity.mine.tools.InviteCompanyActivity;
import com.goldensky.vip.activity.mine.tools.MyAddressActivity;
import com.goldensky.vip.activity.mine.tools.ShareToFriendActivity;
import com.goldensky.vip.activity.order.ConfirmOrderActivity;
import com.goldensky.vip.activity.order.OrderListActivity;
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
     * vip交流群
     */
    public static void startVIPTalkActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, VIPTalkActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }


    /**
     * 确认订单
     */
    public static void startConfirmOrderActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ConfirmOrderActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 交易物流
     */
    public static void startTradeLogisticsActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, TradeLogisticsActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 系统消息
     */
    public static void startSystemMessageActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SystemMessageActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 合纵直播
     */
    public static void startLiveActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LiveActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 推广消息
     */
    public static void startPromoteActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PromoteActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 科普消息
     */
    public static void startScienceActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ScienceActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 消息通知
     */
    public static void startMessageActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MessageActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
