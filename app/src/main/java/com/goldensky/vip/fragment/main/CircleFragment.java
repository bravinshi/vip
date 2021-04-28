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
        mBinding.fnIv.setOnClickListener(this);
        mBinding.bgIv.setOnClickListener(this);
        mBinding.talkIv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.talk_iv:
                Starter.startVIPTalkActivity(getContext(), null);
                break;
            case R.id.xj_iv:
                bundle.putInt("goods",1);
                Starter.startArticleDetailActivity(getContext(), bundle);
                break;
            case R.id.fn_iv:
                bundle.putInt("goods",2);
                Starter.startArticleDetailActivity(getContext(), bundle);
                break;
            case R.id.bg_iv:
                bundle.putInt("goods",3);
                Starter.startArticleDetailActivity(getContext(), bundle);
                break;
        }
    }
}