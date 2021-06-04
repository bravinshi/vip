package com.goldensky.vip;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.goldensky.vip.activity.account.ForgetPasswordActivity;
import com.goldensky.vip.activity.account.LoginActivity;
import com.goldensky.vip.activity.accountManager.AccountManageActivity;
import com.goldensky.vip.activity.accountManager.CompanyInfoActivity;
import com.goldensky.vip.activity.accountManager.BusinessAnalysisActivity;
import com.goldensky.vip.activity.accountManager.FxhzActivity;
import com.goldensky.vip.activity.accountManager.FxhzDetailActivity;
import com.goldensky.vip.activity.accountManager.JsAccountActivity;
import com.goldensky.vip.activity.accountManager.JxtjDetailActivity;
import com.goldensky.vip.activity.accountManager.JyAccountActivity;
import com.goldensky.vip.activity.article.ArticleDetailActivity;
import com.goldensky.vip.activity.brandcompany.SjczActivity;
import com.goldensky.vip.activity.brandcompany.ZjCompanyActivity;
import com.goldensky.vip.activity.brandcompany.ZjConfirmActivity;
import com.goldensky.vip.activity.customer.CustomerServiceActivity;
import com.goldensky.vip.activity.goods.HotTodayActivity;
import com.goldensky.vip.activity.goods.MainGoodsActivity;
import com.goldensky.vip.activity.goods.PackageCustomizationActivity;
import com.goldensky.vip.activity.goods.SearchActivity;
import com.goldensky.vip.activity.goods.SpecialPriceAreaActivity;
import com.goldensky.vip.activity.mine.company.AssignedAccountActivity;
import com.goldensky.vip.activity.mine.company.CompanyManageActivity;
import com.goldensky.vip.activity.mine.company.MarketActivity;
import com.goldensky.vip.activity.mine.company.PerformanceActivity;
import com.goldensky.vip.activity.mine.company.ShenPiActivity;
import com.goldensky.vip.activity.mine.focus.CircleFocusActivity;
import com.goldensky.vip.activity.mine.focus.GoodsFocusActivity;
import com.goldensky.vip.activity.mine.focus.RecentBrowseActivity;
import com.goldensky.vip.activity.mine.mall.MallManageActivity;
import com.goldensky.vip.activity.mine.mall.MallManageTestActivity;
import com.goldensky.vip.activity.mine.mall.MyLiveActivity;
import com.goldensky.vip.activity.mine.mall.PromoteActivity;
import com.goldensky.vip.activity.mine.settings.ChangeNickActivity;
import com.goldensky.vip.activity.mine.settings.ChangePWDActivity;
import com.goldensky.vip.activity.mine.settings.PersonalDetailsActivity;
import com.goldensky.vip.activity.mine.settings.SettingsActivity;
import com.goldensky.vip.activity.mine.tools.CouponActivity;
import com.goldensky.vip.activity.mine.tools.InviteCompanyActivity;
import com.goldensky.vip.activity.mine.tools.MyAddressActivity;
import com.goldensky.vip.activity.mine.tools.MyToolsActivity;
import com.goldensky.vip.activity.mine.tools.MyToolsTestActivity;
import com.goldensky.vip.activity.mine.tools.ShareToFriendActivity;
import com.goldensky.vip.activity.mine.vip.VipHomeActivity;
import com.goldensky.vip.activity.mine.vip.VipManageActivity;
import com.goldensky.vip.activity.order.ConfirmOrderActivity;
import com.goldensky.vip.activity.order.FxOrderActivity;
import com.goldensky.vip.activity.order.JhManageActivity;
import com.goldensky.vip.activity.order.OrderListActivity;
import com.goldensky.vip.activity.goods.RecommendActivity;
import com.goldensky.vip.activity.goods.GoodsDetailActivity;
import com.goldensky.vip.activity.order.XsOrderActivity;


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
     * 我的工具
     */
    public static void startMyToolsActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MyToolsActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 我的工具（体验）
     */
    public static void startMyToolsTestActivity(Context context) {
        Intent intent = new Intent(context, MyToolsTestActivity.class);
        context.startActivity(intent);
    }
    /**
     * 主营商品
     */
    public static void startMainGoodsActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainGoodsActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 商城管理
     */
    public static void startMallMangeActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MallManageActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 商城管理
     */
    public static void startMallManageTestActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MallManageTestActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * VIP管理
     */
    public static void startVipManageActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, VipManageActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
    /**
     * 推广详情
     */
    public static void startPromoteActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PromoteActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    //销售订单
    public static void startSxOrderActivity(Context context) {
        Intent intent = new Intent(context, XsOrderActivity.class);
        context.startActivity(intent);
    }

    //交易账户
    public static void startJyAccountActivity(Context context) {
        Intent intent = new Intent(context, JyAccountActivity.class);
        context.startActivity(intent);
    }

    //交易账户
    public static void startJsAccountActivity(Context context) {
        Intent intent = new Intent(context, JsAccountActivity.class);
        context.startActivity(intent);
    }

    //进货管理
    public static void startJhActivity(Context context) {
        Intent intent = new Intent(context, JhManageActivity.class);
        context.startActivity(intent);
    }

    //分销订单
    public static void startFxOrderActivity(Context context) {
        Intent intent = new Intent(context, FxOrderActivity.class);
        context.startActivity(intent);
    }

    //账号管理
    public static void startAccountManageActivity(Context context) {
        Intent intent = new Intent(context, AccountManageActivity.class);
        context.startActivity(intent);
    }

    //公司资料
    public static void startCompanyInfoActivity(Context context) {
        Intent intent = new Intent(context, CompanyInfoActivity.class);
        context.startActivity(intent);
    }

    //商城首页
    public static void startVipHomeActivity(Context context) {
        Intent intent = new Intent(context, VipHomeActivity.class);
        context.startActivity(intent);
    }

    //我的直播
    public static void startMyLiveActivity(Context context) {
        Intent intent = new Intent(context, MyLiveActivity.class);
        context.startActivity(intent);
    }
    //经营分析
    public static void startBusinessAnalysisActivity(Context context) {
        Intent intent = new Intent(context, BusinessAnalysisActivity.class);
        context.startActivity(intent);
    }
    //公司管理
    public static void startCompanyManageActivity(Context context) {
        Intent intent = new Intent(context, CompanyManageActivity.class);
        context.startActivity(intent);
    }
    //市场管理
    public static void startMarketActivity(Context context) {
        Intent intent = new Intent(context, MarketActivity.class);
        context.startActivity(intent);
    }
    //业绩收益
    public static void startPerformanceActivity(Context context) {
        Intent intent = new Intent(context, PerformanceActivity.class);
        context.startActivity(intent);
    }
    //审批管理
    public static void startShenPiActivity(Context context) {
        Intent intent = new Intent(context, ShenPiActivity.class);
        context.startActivity(intent);
    }

    //升级合作实体
    public static void startSjstActivity(Context context) {
        Intent intent = new Intent(context, SjczActivity.class);
        context.startActivity(intent);
    }

    /**
     * 组建公司申请
     */
    public static void startZjCompanyActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ZjCompanyActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    /**
     * 组建公司申请确认
     */
    public static void startZjConfirmActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ZjConfirmActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    //升级合作实体
    public static void startAssignedAccountActivity(Context context) {
        Intent intent = new Intent(context, AssignedAccountActivity.class);
        context.startActivity(intent);
    }

    //分销汇总
    public static void startFxhzActivity(Context context) {
        Intent intent = new Intent(context, FxhzActivity.class);
        context.startActivity(intent);
    }

    //分销汇总详情
    public static void startFxhzDetailActivity(Context context) {
        Intent intent = new Intent(context, FxhzDetailActivity.class);
        context.startActivity(intent);
    }

    //经销汇总详情
    public static void startJxhzDetailActivity(Context context) {
        Intent intent = new Intent(context, JxtjDetailActivity.class);
        context.startActivity(intent);
    }

    public static void startConfirmOrderActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }
}
