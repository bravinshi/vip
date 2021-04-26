package com.goldensky.vip.activity.accountManager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityJsAccountBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class JsAccountActivity extends BaseActivity<ActivityJsAccountBinding, PublicViewModel> implements View.OnClickListener {

    private int tag = 0;
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.backV.setOnClickListener(this);
        mBinding.leftV.setOnClickListener(this);
        mBinding.rightV.setOnClickListener(this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_js_account;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.back_v:
                finish();
                break;
            case R.id.left_v:
                if (tag == 0) return;
                mBinding.tabIv.setImageResource(R.mipmap.my_pic_yuejiesuan1);
                mBinding.moneyIv.setImageResource(R.mipmap.my_pic_yuejiesuan2);
                mBinding.bottomIv.setImageResource(R.mipmap.my_pic_yuejiesuan3);
                mBinding.scrollView.scrollTo(0,0);
                tag = 0;
                break;
            case  R.id.right_v:
                if (tag == 1) return;
                mBinding.tabIv.setImageResource(R.mipmap.my_pic_yijiesuan1);
                mBinding.moneyIv.setImageResource(R.mipmap.my_pic_yijiesuan2);
                mBinding.bottomIv.setImageResource(R.mipmap.my_pic_yijiesuan3);
                mBinding.scrollView.scrollTo(0,0);
                tag = 1;
                break;
        }
    }
}