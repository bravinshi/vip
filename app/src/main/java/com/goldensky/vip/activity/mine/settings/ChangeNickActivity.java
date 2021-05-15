package com.goldensky.vip.activity.mine.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.UpdateVipUserReqBean;
import com.goldensky.vip.databinding.ActivityChangeNickBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.model.ChangeNickModel;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.account.AccountViewModel;

public class ChangeNickActivity extends BaseActivity<ActivityChangeNickBinding, AccountViewModel> {
    private ChangeNickModel changeNickModel=new ChangeNickModel();
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarNick.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(changeNickModel.getNick()==null&&changeNickModel.getNick().equals("")){
                    toast(getResources().getString(R.string.hint_input_nick_nonull));
                }else {
                    if(changeNickModel.getNick().length()<4||changeNickModel.getNick().length()>20){
                        toast(getResources().getString(R.string.hint_change_nick));
                    }else {
                        UpdateVipUserReqBean reqBean = new UpdateVipUserReqBean();
                        reqBean.setUserId(AccountHelper.getUserId());
                        reqBean.setUserNick(changeNickModel.getNick());
                        mViewModel.updateVipUser(reqBean);
                    }

                }

            }
        });
        mBinding.topBarNick.setLeftListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.setModel(changeNickModel);

    }

    @Override
    public void observe() {
        mViewModel.userLive.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                AccountHelper.setNick(changeNickModel.getNick());
                toast(getResources().getString(R.string.hint_change_nick_success));
                finish();
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_nick;
    }
}