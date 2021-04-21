package com.goldensky.vip.activity.mine.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityPersonalDetailsBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class PersonalDetailsActivity extends BaseActivity<ActivityPersonalDetailsBinding, PublicViewModel> implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
    }

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarPersonDetail.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.setListener(this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_personal_details;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cl_head_person_detail:

                break;
            case R.id.cl_nick_person_detail:
                Starter.startChangeNickActivity(this,null);
                break;
        }
    }
}