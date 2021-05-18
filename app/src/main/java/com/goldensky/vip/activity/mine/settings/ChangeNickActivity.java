package com.goldensky.vip.activity.mine.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.UpdateVipUserReqBean;
import com.goldensky.vip.databinding.ActivityChangeNickBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.listener.DoNothingTextWatcher;
import com.goldensky.vip.model.ChangeNickModel;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.account.AccountViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeNickActivity extends BaseActivity<ActivityChangeNickBinding, AccountViewModel> {
    private ChangeNickModel changeNickModel = new ChangeNickModel();

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        changeNickModel.setNick(AccountHelper.getUserNick());
        mBinding.topBarNick.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNotNull(changeNickModel.getNick())) {
                    toast(getResources().getString(R.string.hint_input_nick_nonull));
                } else {
                    if (changeNickModel.getNick().length() < 4 || changeNickModel.getNick().length() > 20) {
                        toast(getResources().getString(R.string.hint_change_nick));
                    } else {
                        if (changeNickModel.getNick().equals(AccountHelper.getUserNick())) {
                            toast(getResources().getString(R.string.hint_change_nick_repeat));
                        } else {
                            UpdateVipUserReqBean reqBean = new UpdateVipUserReqBean();
                            reqBean.setUserId(AccountHelper.getUserId());
                            reqBean.setUserNick(changeNickModel.getNick());
                            mViewModel.updateVipUser(reqBean);
                        }
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
        mBinding.etNickChangeNick.addTextChangedListener(new DoNothingTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                String string = s.toString();
                if (isEmote(string)) {
                    String substring = string.substring(0, (string.length() - 2));
                    mBinding.etNickChangeNick.setText(substring);
                    mBinding.etNickChangeNick.setSelection(substring.length());
                }
            }
        });
    }

    private boolean isEmote(String str) {
        Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    private boolean isNotNull(String str) {
        return str != null && !str.equals("");
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