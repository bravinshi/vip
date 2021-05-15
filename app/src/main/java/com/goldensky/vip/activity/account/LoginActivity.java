package com.goldensky.vip.activity.account;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;

import androidx.annotation.NonNull;

import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.activity.WebViewActivity;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.LoginResponseBean;
import com.goldensky.vip.databinding.ActivityLoginBinding;
import com.goldensky.vip.enumerate.LoginTypeEnum;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.model.LoginInputModel;
import com.goldensky.vip.viewmodel.account.AccountViewModel;

import io.reactivex.Observable;
import io.reactivex.Observer;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, AccountViewModel> {

    private static final int LOGIN_TYPE_PASSWORD = 1;// 登录类型 密码
    private static final int LOGIN_TYPE_VERIFICATION_CODE = 2;// 登录类型 验证码
    private static int CURRENT_LOGIN_TYPE = LOGIN_TYPE_PASSWORD;// 当前登录类型
    private final LoginInputModel loginInputModel = new LoginInputModel();// 保存用户输入的数据

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        // 绑定数据
        mBinding.setLoginInfo(loginInputModel);

        initContentText();
    }

    @Override
    public void initListener() {
        // 切换登录方式监听
        mBinding.clLoginType.setOnClickListener(v -> changeLoginType(CURRENT_LOGIN_TYPE
                == LOGIN_TYPE_PASSWORD ? LOGIN_TYPE_VERIFICATION_CODE : LOGIN_TYPE_PASSWORD));

        changeLoginType(LOGIN_TYPE_VERIFICATION_CODE);
        // 获取验证码监听
        mBinding.btnGetVerificationCode.setOnClickListener(v -> getVerificationCode());
        // 登录监听
        mBinding.tvLogin.setOnClickListener(v -> {
            if (CURRENT_LOGIN_TYPE == LOGIN_TYPE_PASSWORD) {
                loginWithPassword();
            } else if (CURRENT_LOGIN_TYPE == LOGIN_TYPE_VERIFICATION_CODE) {
                loginWithVerificationCode();
            }
        });
        // 忘记密码监听
        mBinding.tvUnableLogin.setOnClickListener(v -> forgetPassword());
    }

    @Override
    public void observe() {
        mViewModel.verificationCodeLive.observe(this, aBoolean -> {
            // 成功获取验证码
            toast(R.string.hint_verification_code_has_sent);
            mBinding.btnGetVerificationCode.startCountDown();
        });

        // 登录成功
        mViewModel.loginResponseLive.observe(this, this::loginSuccess);
    }

    /**
     * 登录成功
     */
    private void loginSuccess(LoginResponseBean loginResponseBean) {
        toast(getString(R.string.hint_login_success));
        AccountHelper.login(loginResponseBean);

        if (loginResponseBean.getVipUser().isNewUser()) {
            // 进入设置密码页
            Starter.startSetPasswordActivity(this, null);
        } else {
            Starter.startMainActivity(this, null);
        }

        finish();
    }

    /**
     * 获取验证码
     */
    private void getVerificationCode() {
        // 检查手机号有效性
        if (notPhoneNumber(loginInputModel.getPhoneOrLicense())) {
            toast(R.string.hint_input_effective_phone);
            return;
        }
        // 获取验证码
        mViewModel.getVerificationCode(loginInputModel.getPhoneOrLicense().trim());
    }

    /**
     * 密码登录
     */
    private void loginWithPassword() {
        // 检查手机号有效性
        if (notPhoneNumber(loginInputModel.getPhoneOrLicense())) {
            toast(R.string.hint_input_effective_phone);
            return;
        }
        // 检查是否有密码
        if (StringUtils.isTrimEmpty(loginInputModel.getPasswordOrVerificationCode())) {
            toast(R.string.hint_input_password);
            return;
        }

        // 登录
        mViewModel.login(loginInputModel.getPhoneOrLicense().trim(),
                loginInputModel.getPasswordOrVerificationCode(), LoginTypeEnum.PASSWORD.value, mBinding.tvLogin);
    }

    /**
     * 验证码登录
     */
    private void loginWithVerificationCode() {
        // 检查手机号有效性
        if (notPhoneNumber(loginInputModel.getPhoneOrLicense())) {
            toast(R.string.hint_input_effective_phone);
            return;
        }

        // 检查是否有验证码
        if (StringUtils.isTrimEmpty(loginInputModel.getPasswordOrVerificationCode())) {
            toast(R.string.hint_input_verification_code);
        }

        // 登录
        mViewModel.login(loginInputModel.getPhoneOrLicense().trim(),
                loginInputModel.getPasswordOrVerificationCode(),
                LoginTypeEnum.VERIFICATION_CODE.value, mBinding.tvLogin);
    }

    private boolean notPhoneNumber(String paddingTestText) {
        return StringUtils.isTrimEmpty(paddingTestText)
                || paddingTestText.trim().length() != 11;
    }


    /**
     * 忘记密码
     */
    private void forgetPassword() {
        Starter.startForgetPasswordActivity(this, null);
    }

    /**
     * 更改登录类型并重新渲染界面
     *
     * @param type 登录类型
     */
    private void changeLoginType(int type) {
        CURRENT_LOGIN_TYPE = type;
        mBinding.etPasswordOrVerificationCode.setText(null);
        switch (type) {
            case LOGIN_TYPE_PASSWORD:
                // text
                mBinding.tvChangeLoginType.setText(R.string.text_login_type_verification_code);
                mBinding.tvLogin.setText(R.string.text_login);
                //hint
                mBinding.etPasswordOrVerificationCode.setHint(R.string.hint_input_password);
                // Visibility
                mBinding.btnGetVerificationCode.setVisibility(View.INVISIBLE);
                mBinding.tvUnableLogin.setVisibility(View.VISIBLE);
                // inputType
                mBinding.etPasswordOrVerificationCode.setInputType(InputType
                        .TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                mBinding.etPasswordOrVerificationCode.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
            case LOGIN_TYPE_VERIFICATION_CODE:
                // text
                mBinding.tvChangeLoginType.setText(R.string.text_login_type_password);
                mBinding.tvLogin.setText(R.string.text_login_or_register);
                //hint
                mBinding.etPasswordOrVerificationCode.setHint(R.string.hint_input_verification_code);
                // Visibility
                mBinding.btnGetVerificationCode.setVisibility(View.VISIBLE);
                mBinding.tvUnableLogin.setVisibility(View.GONE);
                // inputType
                // inputType
                mBinding.etPasswordOrVerificationCode.setInputType(InputType
                        .TYPE_CLASS_NUMBER);
                mBinding.etPasswordOrVerificationCode.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
        }
    }

    private void initContentText() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String content = getResources().getString(R.string
                .text_hint_protocol_policy);
        int indexPolicy = content.indexOf("隐私政策");
        int indexProtocol = content.indexOf("用户协议");
        SpannableString spannableString = new SpannableString(content);

        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Bundle bundle = new Bundle();
                bundle.putString(WebViewActivity.CENTER_TEXT, "隐私政策");
                bundle.putString(WebViewActivity.WEBVIEW_URL, "file:android_asset/privacy.htm");
                Starter.startWebViewActivity(widget.getContext(), bundle);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        }, indexPolicy, indexPolicy + 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color
                .color_6)), indexPolicy, indexPolicy + 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);// 设置颜色
        spannableString.setSpan(new BackgroundColorSpan(Color.TRANSPARENT), indexPolicy, indexPolicy + 4, Spanned
                .SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Bundle bundle = new Bundle();
                bundle.putString(WebViewActivity.CENTER_TEXT, "用户协议");
                bundle.putString(WebViewActivity.WEBVIEW_URL, "file:android_asset/register.htm");
                Starter.startWebViewActivity(widget.getContext(), bundle);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }

        }, indexProtocol, indexProtocol + 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color
                .color_6)), indexProtocol, indexProtocol + 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);// 设置颜色
        spannableString.setSpan(new BackgroundColorSpan(Color.TRANSPARENT), indexProtocol, indexProtocol + 4, Spanned
                .SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableStringBuilder.append(spannableString);

        mBinding.tvProtocolPolicy.setText(spannableStringBuilder);
        mBinding.tvProtocolPolicy.setMovementMethod(LinkMovementMethod.getInstance());
        mBinding.tvProtocolPolicy.setHighlightColor(getResources().getColor(R.color.colorTransparent));// 设置点击时无颜色
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


}