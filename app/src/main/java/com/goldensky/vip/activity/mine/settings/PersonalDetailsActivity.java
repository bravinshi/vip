package com.goldensky.vip.activity.mine.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityPersonalDetailsBinding;
import com.goldensky.vip.enumerate.DefaultUrlEnum;
import com.goldensky.vip.event.VipUserChangeEvent;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.PublicViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PersonalDetailsActivity extends BaseActivity<ActivityPersonalDetailsBinding, PublicViewModel> implements View.OnClickListener {

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mBinding.topBarPersonDetail.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.setListener(this);
        setMSG();
    }

    private void setMSG() {
        if(AccountHelper.getUserNick()!=null){
            mBinding.tvNickPersonalDetail.setText(AccountHelper.getUserNick());
        }else {
            mBinding.tvNickPersonalDetail.setText(AccountHelper.getUserMobile());
        }
        if(AccountHelper.getUserPic()!=null&&!AccountHelper.getUserPic().equals("")){
            Glide.with(this).load(AccountHelper.getUserPic()).apply(new RequestOptions().circleCrop()).into(mBinding.ivHeadPersonalDetail);
        }else {
            Glide.with(this).load(DefaultUrlEnum.DEFAULTHEADPIC.value).apply(new RequestOptions().circleCrop()).into(mBinding.ivHeadPersonalDetail);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onVipUserChanged(VipUserChangeEvent event){
        if(event.getSuccess()){
            setMSG();
        }
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