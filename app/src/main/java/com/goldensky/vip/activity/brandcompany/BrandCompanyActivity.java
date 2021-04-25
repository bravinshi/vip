package com.goldensky.vip.activity.brandcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityBrandCompanyBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class BrandCompanyActivity extends BaseActivity<ActivityBrandCompanyBinding, PublicViewModel> implements View.OnClickListener {

    private int count = 0;

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.backV.setOnClickListener(this);
        mBinding.bottomIv.setOnClickListener(this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_brand_company;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_v:
                finish();
                break;
            case R.id.bottom_iv:
                if (count > 8) return;
                if (count == 0) {
                    mBinding.topIv.setImageResource(R.mipmap.my_pic_21);
                    mBinding.middleIv.setImageResource(R.mipmap.my_pic_22);
                    mBinding.bottomIv.setImageResource(R.mipmap.my_pic_23);
                } else if (count == 1) {
                    mBinding.topIv.setImageResource(R.mipmap.my_pic_31);
                    mBinding.middleIv.setImageResource(R.mipmap.my_pic_32);
                    mBinding.bottomIv.setImageResource(R.mipmap.my_pic_33);
                } else if (count == 2) {
                    mBinding.topIv.setImageResource(R.mipmap.my_pic_41);
                    mBinding.middleIv.setImageResource(R.mipmap.my_pic_42);
                    mBinding.bottomIv.setImageResource(R.mipmap.my_pic_43);
                } else if (count == 3) {
                    mBinding.topIv.setImageResource(R.mipmap.my_pic_61);
                    mBinding.middleIv.setImageResource(R.mipmap.my_pic_62);
                    mBinding.bottomIv.setImageResource(R.mipmap.my_pic_63);
                } else if (count == 4) {
                    mBinding.topIv.setImageResource(R.mipmap.my_pic_71);
                    mBinding.middleIv.setImageResource(R.mipmap.my_pic_72);
                    mBinding.bottomIv.setImageResource(R.mipmap.my_pic_73);
                } else if (count == 5) {
                    mBinding.topIv.setImageResource(R.mipmap.my_pic_81);
                    mBinding.middleIv.setImageResource(R.mipmap.my_pic_82);
                    mBinding.bottomIv.setImageResource(R.mipmap.my_pic_83);
                } else if (count == 6) {
                    mBinding.topIv.setImageResource(R.mipmap.my_pic_91);
                    mBinding.middleIv.setImageResource(R.mipmap.my_pic_92);
                    mBinding.bottomIv.setImageResource(R.mipmap.my_pic_93);
                } else if (count == 7) {
                    mBinding.topIv.setImageResource(R.mipmap.my_pic_101);
                    mBinding.middleIv.setImageResource(R.mipmap.my_pic_102);
                    mBinding.bottomIv.setImageResource(R.mipmap.my_pic_103);
                } else if (count == 8) {
                    mBinding.topIv.setImageResource(R.mipmap.my_pic_111);
                    mBinding.middleIv.setImageResource(R.mipmap.my_pic_112);
                    mBinding.bottomIv.setImageResource(R.mipmap.my_pic_113);
                }

                count ++;

                break;
        }
    }
}