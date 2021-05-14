package com.goldensky.vip.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.goldensky.framework.util.ToastUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.constant.ConfigConstant;
import com.goldensky.vip.databinding.ActivityWxPaymentBinding;
import com.goldensky.vip.event.PaymentReturnEvent;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/12 16:02
 * 包名： com.goldensky.vip.wxapi
 * 类说明：
 */
public class WXPayEntryActivity extends BaseActivity<ActivityWxPaymentBinding, PublicViewModel> implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void initListener() {
        mBinding.tvMain.setOnClickListener(v -> {
            Starter.startMainActivity(v.getContext(), null);
            finish();
        });

        mBinding.tvOrder.setOnClickListener(v -> {
            // 跳转到订单列表
            PaymentReturnEvent paymentReturnEvent = new PaymentReturnEvent();
            paymentReturnEvent.setSuccess(true);
            paymentReturnEvent.setAction(PaymentReturnEvent.KEY_ACTION_ORDER_DETAIL);
            EventBus.getDefault().post(paymentReturnEvent);
            finish();
        });
    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.errCode == 0) {
            mBinding.rlContent.setVisibility(View.VISIBLE);
        } else {
            // 支付失败
            PaymentReturnEvent paymentReturnEvent = new PaymentReturnEvent();
            paymentReturnEvent.setSuccess(false);
            paymentReturnEvent.setAction(PaymentReturnEvent.KEY_ACTION_ORDER_DETAIL);
            EventBus.getDefault().post(paymentReturnEvent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        // 屏蔽返回
    }

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        ToastUtils.showShort("1");
        api = WXAPIFactory.createWXAPI(this, ConfigConstant.WX_APP_ID);
        api.handleIntent(getIntent(), this);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wx_payment;
    }
}
