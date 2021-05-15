package com.goldensky.vip.activity;

import android.os.Bundle;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityProtocolContentBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/15 9:25
 * 包名： com.goldensky.vip.activity
 * 类说明：
 */
public class WebViewActivity extends BaseActivity<ActivityProtocolContentBinding, PublicViewModel> {

    public static final String CENTER_TEXT = "CENTER_TEXT";
    public static final String WEBVIEW_URL = "WEBVIEW_URL";

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBar.setCenterHint(getIntent().getExtras().getString(CENTER_TEXT));
        mBinding.webView.loadUrl(getIntent().getExtras().getString(WEBVIEW_URL));
        mBinding.topBar.setBackListener(v -> finish());
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_protocol_content;
    }
}
