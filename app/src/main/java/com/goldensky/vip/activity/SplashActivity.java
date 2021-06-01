package com.goldensky.vip.activity;

import android.os.Bundle;
import android.os.CountDownTimer;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.ui.dialog.UpdateDialog;
import com.goldensky.framework.util.AppUtils;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.framework.util.ToastUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.base.error.FailCallback;
import com.goldensky.vip.bean.CheckVersionResBean;
import com.goldensky.vip.databinding.ActivitySplashBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.PublicViewModel;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/28 13:26
 * 包名： com.goldensky.vip.activity
 * 类说明：
 */
public class SplashActivity extends BaseActivity<ActivitySplashBinding, PublicViewModel> {

    private CountDownTimer countDownTimer;
    private final long millisInFuture = 4000;
    private Integer timeRemain = 3;

    private UpdateDialog updateDialog;
    private CheckVersionResBean mCheckVersionResBean;


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        countDownTimer = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                setText();
            }

            @Override
            public void onFinish() {
                setText();
                checkLoginState();
            }
        };

        //检查版本更新
        mViewModel.checkVersion(AppUtils.getAppVersionName(), new FailCallback() {
            @Override
            public void onFail(NetResponse netResponse) {
                countDownTimer.start();
            }
        });
    }


    private void setText() {
        if (timeRemain <= 0) {
            timeRemain = 0;
        }
        String text = "跳过广告(" + timeRemain + ")";
        mBinding.tvJump.setText(text);
        timeRemain--;
    }

    @Override
    public void initListener() {

        mViewModel.checkVersionLive.observe(this, this::handleVersionInfo);

        mBinding.tvJump.setOnClickListener(v -> {
            checkLoginState();
        });
    }

    private void handleVersionInfo(CheckVersionResBean checkVersionResBean) {
        mCheckVersionResBean = checkVersionResBean;
        countDownTimer.start();
        if (checkVersionResBean.getVersionstats() < 3) {
            // 可以更新
            countDownTimer.cancel();
            updateDialog = new UpdateDialog(this, checkVersionResBean.getVersionstats() == 2,
                    checkVersionResBean.getVersion(), checkVersionResBean.getVersionexplain(), v -> {
                // 更新
                Starter.startBrowser(SplashActivity.this, checkVersionResBean.getVersionurl());
                if (checkVersionResBean.getVersionstats() == 2) {
                    updateDialog.dismiss();
                } else {
                    // 强制更新
                    ToastUtils.showShort("请等待安装包下载完成后安装");
                }
            }, view -> {
                updateDialog.dismiss();
                countDownTimer.start();
            });

            updateDialog.setCancelable(false);
            updateDialog.setCanceledOnTouchOutside(false);

            if (!updateDialog.isShowing()) {
                updateDialog.show();
            }
        }
    }

    /**
     * 检查登录状态
     */
    private void checkLoginState() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        if (StringUtils.isTrimEmpty(AccountHelper.getToken())) {
            // 没有登录信息 跳转到登录界面
            Starter.startLoginActivity(SplashActivity.this, null);
        } else {
            Starter.startMainActivity(SplashActivity.this, null);
        }

        finish();
    }

    @Override
    public void observe() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if ((mCheckVersionResBean == null || mCheckVersionResBean.getVersionstats() != 1) && countDownTimer != null && (updateDialog == null || !updateDialog.isShowing()) ) {
            countDownTimer.start();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }
}
