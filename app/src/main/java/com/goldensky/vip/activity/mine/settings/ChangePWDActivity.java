package com.goldensky.vip.activity.mine.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.activity.account.SetPasswordActivity;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.base.error.FailCallback;
import com.goldensky.vip.bean.UpdateVipUserReqBean;
import com.goldensky.vip.databinding.ActivityChangePWDBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.model.ChangePWDModel;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.account.AccountViewModel;

public class ChangePWDActivity extends BaseActivity<ActivityChangePWDBinding, AccountViewModel> implements View.OnClickListener {
    private ChangePWDModel changePWDModel=new ChangePWDModel();

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarChangePwd.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.setModel(changePWDModel);
        mBinding.setListener(this);
        mBinding.tvPromptChangePwd.setText(changePWDModel.getHint());
    }

    @Override
    public void observe() {
        mViewModel.verificationCodeLive.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                toast(R.string.hint_verification_code_has_sent);
                mBinding.btnGetCodeChangePwd.startCountDown();
            }
        });
        mViewModel.checkCodeLive.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                changePWDModel.setConfirm(true);
                mBinding.clSendCodeChangePwd.setVisibility(View.GONE);
                mBinding.clConfirmPwdChangePwd.setVisibility(View.VISIBLE);
            }
        });
        mViewModel.userLive.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                toast(getResources().getString(R.string.hint_change_psd_success));
                AccountHelper.loginOut();
                Starter.startLoginActivity(ChangePWDActivity.this,null);
            }
        });
    }

    private void confirm() {
        if (StringUtils.isTrimEmpty(changePWDModel.getNewPassword())) {
            toast(R.string.hint_input_password);
            return;
        }

        if (StringUtils.isTrimEmpty(changePWDModel.getNewPasswordConfirm())) {
            toast(R.string.hint_confirm_password);
            return;
        }

        if (!changePWDModel.getNewPasswordConfirm().equals(changePWDModel.getNewPassword())) {
            toast(getString(R.string.text_hint_twice_input_not_same));
            return;
        }

        UpdateVipUserReqBean reqBean = new UpdateVipUserReqBean();
        reqBean.setUserId(AccountHelper.getUserId());
        reqBean.setUserPassword(changePWDModel.getNewPassword());

        mViewModel.updateVipUser(reqBean);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_p_w_d;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get_code_change_pwd:
                mViewModel.getVerificationCode(AccountHelper.getUserMobile());
                break;
            case R.id.confirm_commit_change_pwd:
                if(changePWDModel.getConfirm()){
                    confirm();
                }else {
                    mViewModel.checkCode(AccountHelper.getUserMobile(), changePWDModel.getVerificationCode(), new FailCallback() {
                        @Override
                        public void onFail(NetResponse netResponse) {
                            if(netResponse.getCode()==5){
                                toast(getResources().getString(R.string.hint_input_code_nonull));
                            }else {
                                toast(netResponse.getMessage());
                            }
                        }
                    });
                }
                break;
            case R.id.can_see_new_change_pwd:
                if (changePWDModel.getNewPasswordCanSee()){
                    mBinding.etPwdChangePwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mBinding.canSeeNewChangePwd.setImageResource(R.mipmap.bukejian);
                }else {
                    mBinding.etPwdChangePwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mBinding.canSeeNewChangePwd.setImageResource(R.mipmap.kejian);
                }
                changePWDModel.setNewPasswordCanSee(!changePWDModel.getNewPasswordCanSee());
                break;
            case R.id.can_see_confirm_change_pwd:
                if (changePWDModel.getConfirmPasswordCanSee()){
                    mBinding.etConfirmPwdChangePwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mBinding.canSeeConfirmChangePwd.setImageResource(R.mipmap.bukejian);
                }else {
                    mBinding.etConfirmPwdChangePwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mBinding.canSeeConfirmChangePwd.setImageResource(R.mipmap.kejian);
                }
                changePWDModel.setConfirmPasswordCanSee(!changePWDModel.getConfirmPasswordCanSee());
                break;

        }
    }
}