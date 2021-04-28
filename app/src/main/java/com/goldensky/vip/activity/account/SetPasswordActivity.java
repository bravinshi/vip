package com.goldensky.vip.activity.account;

import android.os.Bundle;

import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.UpdateVipUserReqBean;
import com.goldensky.vip.databinding.ActivitySetPasswordBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.model.SetPasswordInputModel;
import com.goldensky.vip.viewmodel.account.AccountViewModel;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/28 17:11
 * 包名： com.goldensky.vip.activity.account
 * 类说明：
 */
public class SetPasswordActivity extends BaseActivity<ActivitySetPasswordBinding, AccountViewModel> {
    private final SetPasswordInputModel model = new SetPasswordInputModel();
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.setModel(model);
    }

    @Override
    public void initListener() {
        mBinding.tvNotSetTemporarily.setOnClickListener(v -> {
            Starter.startMainActivity(v.getContext(), null);
            finish();
        });

        mBinding.tvConfirm.setOnClickListener(v -> {
            confirm();
        });
    }

    private void confirm() {
        if (StringUtils.isTrimEmpty(model.getPassword())) {
            toast(R.string.hint_input_password);
            return;
        }

        if (StringUtils.isTrimEmpty(model.getConfirm())) {
            toast(R.string.hint_confirm_password);
            return;
        }

        if (!model.getConfirm().equals(model.getPassword())) {
            toast(getString(R.string.text_hint_twice_input_not_same));
            return;
        }

        UpdateVipUserReqBean reqBean = new UpdateVipUserReqBean();
        reqBean.setUserId(AccountHelper.getUserId());
        UpdateVipUserReqBean.VipUser vipUser = new UpdateVipUserReqBean.VipUser();
        vipUser.setUserPassword(model.getPassword());
        reqBean.setVipUser(vipUser);

        mViewModel.updateVipUser(reqBean);
    }

    @Override
    public void observe() {
        mViewModel.userLive.observe(this, integer -> Starter.startMainActivity(SetPasswordActivity.this, null));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_set_password;
    }
}
