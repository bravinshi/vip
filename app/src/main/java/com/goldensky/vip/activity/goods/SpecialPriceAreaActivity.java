package com.goldensky.vip.activity.goods;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.goldensky.framework.viewmodel.BaseViewModel;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivitySpecialPriceAreaBinding;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/21 15:07
 * 包名： com.goldensky.vip.activity.goods
 * 类说明：
 */
public class SpecialPriceAreaActivity extends BaseActivity<ActivitySpecialPriceAreaBinding, BaseViewModel> implements View.OnClickListener {

    private int currentIndex = 0;
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.backV.setOnClickListener(this);
        mBinding.ptqgV.setOnClickListener(this);
        mBinding.xsmsV.setOnClickListener(this);
        mBinding.lqzxV.setOnClickListener(this);
        mBinding.btnService.setOnClickListener(this);
        mBinding.tmzqV.setOnClickListener(this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_special_price_area;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_v:
                finish();
                break;
            case R.id.ptqg_v:
                if (currentIndex == 0)return;
                currentIndex = 0;
                mBinding.tabIv.setImageResource(R.mipmap.yhzq_tab_1);
                mBinding.ptqgScroll.setVisibility(View.VISIBLE);
                mBinding.ptqgScroll.scrollTo(0,0);
                mBinding.xsmsScroll.setVisibility(View.GONE);
                mBinding.lqzxScroll.setVisibility(View.GONE);
                break;
            case R.id.xsms_v:
                if (currentIndex == 1)return;
                currentIndex = 1;
                mBinding.tabIv.setImageResource(R.mipmap.yhzq_tab_2);
                mBinding.ptqgScroll.setVisibility(View.GONE);
                mBinding.xsmsScroll.setVisibility(View.VISIBLE);
                mBinding.xsmsScroll.scrollTo(0,0);
                mBinding.lqzxScroll.setVisibility(View.GONE);
                break;
            case R.id.lqzx_v:
                if (currentIndex == 2)return;
                currentIndex = 2;
                mBinding.tabIv.setImageResource(R.mipmap.yhzq_tab_3);
                mBinding.ptqgScroll.setVisibility(View.GONE);
                mBinding.xsmsScroll.setVisibility(View.GONE);
                mBinding.lqzxScroll.setVisibility(View.VISIBLE);
                mBinding.lqzxScroll.scrollTo(0,0);
                break;
            case R.id.tmzq_v:
                Toast ts = Toast.makeText(this,"敬请期待~",Toast.LENGTH_SHORT);
                ts.setGravity(Gravity.CENTER_VERTICAL,0,0);
                ts.show();
                break;
            case R.id.btn_service:
                Starter.startCustomerServiceActivity(this,null);
                break;
        }
    }
}
