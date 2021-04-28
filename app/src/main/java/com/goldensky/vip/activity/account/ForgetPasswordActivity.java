package com.goldensky.vip.activity.account;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityForgetPasswordBinding;
import com.goldensky.vip.enumerate.VerificationCodePurposeEnum;
import com.goldensky.vip.model.ForgetPasswordInputModel;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.framework.util.StringUtils;

public class ForgetPasswordActivity extends BaseActivity<ActivityForgetPasswordBinding, PublicViewModel> {

    private final ForgetPasswordInputModel inputModel = new ForgetPasswordInputModel();// 数据和界面双向绑定

    private boolean isInputPasswordNow = false;

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        // 绑定数据到界面
        mBinding.setInfo(inputModel);
        // 设置监听
        setListener();

        refreshView();
    }

    private void refreshView() {
        mBinding.clPassword.setVisibility(isInputPasswordNow ? View.VISIBLE : View.GONE);
        mBinding.clVerificationCode.setVisibility(!isInputPasswordNow ? View.VISIBLE : View.GONE);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        mBinding.tabBar.setBackListener(v -> {
            if (isInputPasswordNow) {
                isInputPasswordNow = false;
                refreshView();
            } else {
                finish();
            }
        });
        mBinding.tvNext.setOnClickListener(v -> next());
        mBinding.btnGetVerificationCode.setOnClickListener(v -> getVerificationCode());
        mBinding.tvConfirm.setOnClickListener(v -> confirm());
    }

    private void confirm() {
        if (StringUtils.isTrimEmpty(inputModel.getNewPassword())) {
            toast(R.string.hint_input_new_password);
            return;
        }

        if (inputModel.getNewPassword().trim().length() < 6
                || inputModel.getNewPassword().trim().length() > 16) {
            toast(R.string.hint__new_password_length_is_wrong);
            return;
        }

        if (StringUtils.isTrimEmpty(inputModel.getNewPasswordConfirm())) {
            toast(R.string.hint_input_new_password_confirm);
            return;
        }

        if (!inputModel.getNewPassword().trim().equals(inputModel.getNewPasswordConfirm().trim())) {
            toast(R.string.hint_password_not_equal);
            return;
        }

        // 修改密码
        mViewModel.updatePassword(inputModel.getPhone().trim(), inputModel
                .getVerificationCode().trim(), inputModel.getNewPassword()
                .trim());
    }

    /**
     * 下一步
     */
    private void next() {
        // 检查手机号有效性
        if (notPhoneNumber(inputModel.getPhone())) {
            toast(R.string.hint_input_effective_phone);
            return;
        }

        if (StringUtils.isTrimEmpty(inputModel.getVerificationCode())) {
            toast(R.string.hint_input_verification_code);
            return;
        }

        isInputPasswordNow = true;
        refreshView();
    }

    /**
     * 获取验证码
     */
    private void getVerificationCode() {
        // 检查手机号有效性
        if (notPhoneNumber(inputModel.getPhone())) {
            toast(R.string.hint_input_effective_phone);
            return;
        }

        // 获取验证码
//        mViewModel.getVerificationCode(VerificationCodePurposeEnum.CHANGE_PASSWORD.value,
//                inputModel.getPhone().trim());
    }

    private boolean notPhoneNumber(String paddingTestText) {
        return StringUtils.isTrimEmpty(paddingTestText)
                || paddingTestText.trim().length() != 11;
    }

    @Override
    public void observe() {
        mViewModel.verificationCodeLive.observe(this, aBoolean -> {
            // 成功获取验证码
            toast(R.string.hint_verification_code_has_sent);
            mBinding.btnGetVerificationCode.startCountDown();
        });

        mViewModel.changePasswordLive.observe(this, o -> {
            toast(getString(R.string.hint__new_password_length_is_wrong));
            Starter.startLoginActivity(ForgetPasswordActivity.this, null);
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_forget_password;
    }
}