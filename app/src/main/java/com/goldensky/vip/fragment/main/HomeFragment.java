package com.goldensky.vip.fragment.main;

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
import com.goldensky.framework.ui.view.NumberButton;
import com.goldensky.vip.R;

import com.goldensky.vip.adapter.HomeAdapter;
import com.goldensky.vip.base.fragment.BaseFragment;
import com.goldensky.vip.bean.HomeBean;
import com.goldensky.vip.bean.UserBean;
import com.goldensky.vip.databinding.FragmentHomeBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.home.HomeViewModel;

import java.util.List;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    private HomeAdapter mHomeAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mViewModel.getUserData().observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(UserBean userBean) {
                Glide.with(getContext()).load(userBean.getPortraitId()).into(mBinding.portaritIv);
                mBinding.setUserInfo(userBean);
            }
        });
        mViewModel.initUserData();

        mBinding.recycleView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mViewModel.getHomeBeans().observe(this, new Observer<List<HomeBean>>() {
            @Override
            public void onChanged(List<HomeBean> homeBeans) {
                mHomeAdapter = new HomeAdapter(homeBeans);
                mBinding.recycleView.setAdapter(mHomeAdapter);
            }
        });
        mViewModel.initHomeBeans();
    }
}