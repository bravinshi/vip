package com.goldensky.vip.activity.mine.company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.activity.brandcompany.BrandCompanyActivity;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityCompanyManageBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class CompanyManageActivity extends BaseActivity<ActivityCompanyManageBinding, PublicViewModel> {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarCompany.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent compInten = new Intent(CompanyManageActivity.this, BrandCompanyActivity.class);
                startActivity(compInten);
            }
        });
        mBinding.btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Starter.startMarketActivity(CompanyManageActivity.this);
            }
        });
        mBinding.btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Starter.startPerformanceActivity(CompanyManageActivity.this);
            }
        });
        mBinding.ivTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Starter.startAssignedAccountActivity(CompanyManageActivity.this);
            }
        });

//        mBinding.btnFour.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Starter.startShenPiActivity(CompanyManageActivity.this);
//            }
//        });
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_company_manage;
    }
}