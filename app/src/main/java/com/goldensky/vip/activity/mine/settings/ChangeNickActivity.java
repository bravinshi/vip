package com.goldensky.vip.activity.mine.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityChangeNickBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.model.ChangeNickModel;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class ChangeNickActivity extends BaseActivity<ActivityChangeNickBinding, PublicViewModel> {
    private ChangeNickModel changeNickModel=new ChangeNickModel();
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarNick.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
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

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_nick;
    }
}