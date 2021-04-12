package com.goldensky.vip.fragment.mine;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldensky.vip.R;
import com.goldensky.vip.base.fragment.BaseFragment;
import com.goldensky.vip.databinding.FragmentMineBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;


public class MineFragment extends BaseFragment<FragmentMineBinding, PublicViewModel> {


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }
}