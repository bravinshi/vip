package com.goldensky.vip.fragment.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.goldensky.framework.ui.view.NumberButton;
import com.goldensky.vip.R;

import com.goldensky.vip.Starter;
import com.goldensky.vip.activity.accountManager.JsAccountActivity;
import com.goldensky.vip.activity.accountManager.JyAccountActivity;
import com.goldensky.vip.activity.brandcompany.BrandCompanyActivity;
import com.goldensky.vip.activity.order.OrderActivity;
import com.goldensky.vip.adapter.HomeAdapter;
import com.goldensky.vip.base.fragment.BaseFragment;
import com.goldensky.vip.bean.HomeBean;
import com.goldensky.vip.bean.UserBean;
import com.goldensky.vip.databinding.FragmentHomeBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.home.HomeViewModel;

import java.util.List;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements View.OnClickListener {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mBinding.orderLayout.setOnClickListener(this);
        mBinding.sjV.setOnClickListener(this);
        mBinding.styqIv.setOnClickListener(this);
        mBinding.scglIv.setOnClickListener(this);
        mBinding.zyspIv.setOnClickListener(this);
        mBinding.ddglIv.setOnClickListener(this);
        mBinding.allIv.setOnClickListener(this);
        mBinding.jyzhV.setOnClickListener(this);
        mBinding.jszhV.setOnClickListener(this);
    }



    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.order_layout:
                Intent intent = new Intent(getContext(), OrderActivity.class);
                startActivity(intent);
                break;
            case R.id.sj_v:
                Intent compInten = new Intent(getContext(), BrandCompanyActivity.class);
                startActivity(compInten);
                break;
            case R.id.jyzh_v:
                Intent jyIntent = new Intent(getContext(), JyAccountActivity.class);
                startActivity(jyIntent);
                break;
            case R.id.jszh_v:
                Intent jsIntent = new Intent(getContext(), JsAccountActivity.class);
                startActivity(jsIntent);
                break;
            case R.id.styq_iv: //VIP管理
                Starter.startVipManageActivity(getContext(),null);
                break;
            case R.id.scgl_iv: //商城管理
                Starter.startMallMangeActivity(getContext(),null);
                break;
            case R.id.zysp_iv://主营商品
                Starter.startMainGoodsActivity(getContext(),null);
                break;
            case R.id.ddgl_iv: //订单管理
                Starter.startOrderListActivity(getContext(),null);
                break;
            case R.id.all_iv: //全部
                Starter.startMyToolsActivity(getContext(),null);
                break;
        }
    }
}