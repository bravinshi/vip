package com.goldensky.vip.fragment.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.fragment.LazyLoadFragment;
import com.goldensky.vip.databinding.FragmentCircleBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;


public class CircleFragment extends LazyLoadFragment<FragmentCircleBinding, PublicViewModel> implements View.OnClickListener{


    @Override
    public void onLazyLoad() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_circle;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mBinding.xjIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.xj_iv:
                Starter.startArticleDetailActivity(getContext(), null);
                break;
        }
    }
}