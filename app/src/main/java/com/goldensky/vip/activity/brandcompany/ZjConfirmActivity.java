package com.goldensky.vip.activity.brandcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityZjConfirmBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class ZjConfirmActivity extends BaseActivity<ActivityZjConfirmBinding, PublicViewModel> implements View.OnClickListener {

    public  static final  String  ZJ_TYPE_KEY = "ZJ_TYPE_KEY"; //1，2，3
    private int zjType;

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.backV.setOnClickListener(this);
        mBinding.commitIv.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        zjType = bundle.getInt(ZJ_TYPE_KEY, 1);
        switch (zjType) {
            case 1:
                mBinding.topIv.setImageResource(R.mipmap.zj_xian_confirm);
                break;
            case 2:
                mBinding.topIv.setImageResource(R.mipmap.zj_shi_confirm);
                break;
            case 3:
                mBinding.topIv.setImageResource(R.mipmap.zj_sheng_confirm);
                break;
            case 4:
                mBinding.topIv.setImageResource(R.mipmap.zj_pinpai_confirm);
                break;
        }
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_zj_confirm;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_v:
                Bundle bundle = new Bundle();
                bundle.putInt(ZJ_TYPE_KEY, zjType);
                Starter.startZjCompanyActivity(this,bundle);
                finish();
                break;
            case R.id.commit_iv:
                finish();
                break;
        }
    }
}