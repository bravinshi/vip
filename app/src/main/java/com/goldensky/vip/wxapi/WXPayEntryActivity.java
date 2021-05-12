package com.goldensky.vip.wxapi;

import android.os.Bundle;

import com.goldensky.framework.util.GsonUtils;
import com.goldensky.framework.util.ToastUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityWxPaymentBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/12 16:02
 * 包名： net.sourceforge.simcpux.wxapi
 * 类说明：
 */
public class WXPayEntryActivity extends BaseActivity<ActivityWxPaymentBinding, PublicViewModel> implements IWXAPIEventHandler {

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        ToastUtils.showShort(GsonUtils.toJson(baseResp));
        // TODO 如果成功再去服务端请求支付结果
    }

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        ToastUtils.showShort("1");
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_wx_payment;
    }
}
