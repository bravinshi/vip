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
        }
    }
}