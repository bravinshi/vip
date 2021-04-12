package com.goldensky.vip.activity.account;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.LoginResponseBean;
import com.goldensky.vip.databinding.ActivityLoginBinding;
import com.goldensky.vip.enumerate.LoginTypeEnum;
import com.goldensky.vip.enumerate.VerificationCodePurposeEnum;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.model.LoginInputModel;
import com.goldensky.vip.viewmodel.LoginViewModel;
import com.goldensky.framework.util.StringUtils;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> {

    private static final int LOGIN_TYPE_PASSWORD = 1;// 登录类型 密码
    private static final int LOGIN_TYPE_VERIFICATION_CODE = 2;// 登录类型 验证码
    private static int CURRENT_LOGIN_TYPE = LOGIN_TYPE_PASSWORD;// 当前登录类型
    private final LoginInputModel loginInputModel = new LoginInputModel();// 保存用户输入的数据

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
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
        // 注册监听
        mBinding.tvRegeist.setOnClickListener(v -> register());
        // 绑定数据
        mBinding.setLoginInfo(loginInputModel);
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
//        AccountHelper.login(loginResponseBean);
        // 进入主页
//        Starter.startMainActivity(this, null);
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
        mViewModel.getVerificationCode(VerificationCodePurposeEnum.LOGIN.value,
                loginInputModel.getPhoneOrLicense().trim());
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
        }

        // 登录
        mViewModel.login(loginInputModel.getPhoneOrLicense().trim(),
                loginInputModel.getPasswordOrVerificationCode(), LoginTypeEnum.PASSWORD.value);
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
                LoginTypeEnum.VERIFICATION_CODE.value);
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
     * 注册
     */
    private void register() {
//        Starter.startRegisterActivity(this, null);
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
                mBinding.tvLoginType.setText(R.string.login_type_hint_company);
                mBinding.tvChangeLoginType.setText(R.string.text_login_type_verification_code);
                //hint
                mBinding.etPhoneOrLicense.setHint(R.string.hint_input_phone_license);
                mBinding.etPasswordOrVerificationCode.setHint(R.string.hint_input_password);
                // Visibility
                mBinding.btnGetVerificationCode.setVisibility(View.INVISIBLE);
                mBinding.tvUnableLogin.setVisibility(View.VISIBLE);
                mBinding.tvRegeist.setVisibility(View.VISIBLE);
                // inputType
                mBinding.etPasswordOrVerificationCode.setInputType(InputType
                        .TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
            case LOGIN_TYPE_VERIFICATION_CODE:
                // text
                mBinding.tvLoginType.setText(R.string.text_login_type_verification_code);
                mBinding.tvChangeLoginType.setText(R.string.text_login_type_password);
                //hint
                mBinding.etPhoneOrLicense.setHint(R.string.hint_input_phone);
                mBinding.etPasswordOrVerificationCode.setHint(R.string.hint_input_verification_code);
                // Visibility
                mBinding.btnGetVerificationCode.setVisibility(View.VISIBLE);
                mBinding.tvUnableLogin.setVisibility(View.GONE);
                mBinding.tvRegeist.setVisibility(View.INVISIBLE);
                // inputType
                mBinding.etPasswordOrVerificationCode.setInputType(InputType
                        .TYPE_CLASS_NUMBER);
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


}