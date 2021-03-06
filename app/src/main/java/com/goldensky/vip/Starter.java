package com.goldensky.vip;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.goldensky.vip.activity.WebViewActivity;
import com.goldensky.vip.activity.account.ForgetPasswordActivity;
import com.goldensky.vip.activity.account.LoginActivity;
import com.goldensky.vip.activity.account.SetPasswordActivity;
import com.goldensky.vip.activity.article.ArticleDetailActivity;
import com.goldensky.vip.activity.customer.CustomerServiceActivity;
import com.goldensky.vip.activity.goods.ConfirmOrderActivity;
import com.goldensky.vip.activity.goods.GoodsCommentActivity;
import com.goldensky.vip.activity.goods.GoodsDetailActivity;
import com.goldensky.vip.activity.goods.HotTodayActivity;
import com.goldensky.vip.activity.goods.OptimizationActivity;
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
import com.goldensky.vip.activity.mine.tools.ShareToFriendActivity;
import com.goldensky.vip.activity.mine.tools.adress.EditAddressActivity;
import com.goldensky.vip.activity.mine.tools.adress.MyAddressActivity;
import com.goldensky.vip.activity.mine.tools.adress.NewAddressActivity;
import com.goldensky.vip.activity.order.CommentSuccessActivity;
import com.goldensky.vip.activity.order.LogisticsActivity;
import com.goldensky.vip.activity.order.OrderCommentActivity;
import com.goldensky.vip.activity.order.OrderDetailActivity;
import com.goldensky.vip.activity.order.OrderListActivity;


/**
 * @author bravin
 * @version 1.0
 * ???????????????2021/3/26 10:35
 * ????????? com.goldensky.entity
 * ????????????
 */
public class Starter {

    /**
     * ???????????????
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
     * ??????MainActivity
     */
    public static void startMainActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * ????????????????????????
     */
    public static void startForgetPasswordActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ForgetPasswordActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }


    /**
     * ??????????????????
     */
    public static void startSettingsActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SettingsActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * ????????????????????????
     */
    public static void startPersonDetailActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PersonalDetailsActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * ????????????????????????
     */
    public static void startChangePWDActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ChangePWDActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * ????????????????????????
     */
    public static void startChangeNickActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ChangeNickActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * ????????????????????????
     */
    public static void startGoodsFocusActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GoodsFocusActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * ????????????????????????
     */
    public static void startCircleFocusActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, CircleFocusActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * ????????????????????????
     */
    public static void startRecentBrowseActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RecentBrowseActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * ????????????????????????
     */
    public static void startOrderListActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, OrderListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * ?????????????????????
     */
    public static void startCouponActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, CouponActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * ????????????????????????
     */
    public static void startMyAddressActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MyAddressActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * ????????????????????????
     */
    public static void startShareToFriendActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ShareToFriendActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * ?????????????????????
     */
    public static void startGoodsDetailActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GoodsDetailActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * ????????????????????????
     */
    public static void startInviteCompanyActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, InviteCompanyActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * ????????????
     */
    public static void startRecommendActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, RecommendActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * ??????????????????
     */
    public static void startCustomerServiceActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, CustomerServiceActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /*
     * ????????????
     */
    public static void startSearchActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SearchActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * ????????????
     */
    public static void startHotTodayActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, HotTodayActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * ????????????
     */
    public static void startCustomActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PackageCustomizationActivity.class);

        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * ????????????
     */
    public static void startYhzqActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SpecialPriceAreaActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * ????????????
     */
    public static void startArticleDetailActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * ????????????
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
     * ??????????????????
     */
    public static void startEditAddressActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, EditAddressActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    //??????????????????
    public static void startGoodsCommentActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, GoodsCommentActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    //??????????????????
    public static void startOrderCommentActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, OrderCommentActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    public static void startConfirmOrderActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    //??????????????????
    public static void startAboutGoldenDaysActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AboutGoldenDaysActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    //??????????????????
    public static void startCommentSuccessActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, CommentSuccessActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }


    //????????????
    public static void startOptimizationActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, OptimizationActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    //????????????
    public static void startOrderDetailActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * ?????????????????????
     */
    public static void startWebViewActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, WebViewActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    //????????????
    public static void startLogisticsActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, LogisticsActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * ???????????????
     */
    public static void startBrowser(Context context, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }
}
