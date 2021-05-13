package com.goldensky.vip.fragment.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.R;
import com.goldensky.vip.adapter.HomeAdapter;
import com.goldensky.vip.base.error.FailCallback;
import com.goldensky.vip.base.fragment.BaseFragment;
import com.goldensky.vip.bean.HomeBean;
import com.goldensky.vip.databinding.FragmentHomeBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.home.HomeViewModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements View.OnClickListener {

    private HomeAdapter mHomeAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mBinding.gxtjIv.setOnClickListener(this);

        mHomeAdapter = new HomeAdapter(mViewModel.homeBeans);
        mHomeAdapter.addChildClickViewIds(new int[]{ R.id.more_iv});
        mHomeAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mBinding.recycleView.setLayoutManager(linearLayoutManager);
        mBinding.recycleView.setAdapter(mHomeAdapter);

        mViewModel.loadResult.observe(this, result -> {
            mBinding.swRefresh.finishRefresh();
            mHomeAdapter.notifyDataSetChanged();
        });

        mBinding.swRefresh.setEnableLoadMore(false);
        mBinding.swRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull @NotNull RefreshLayout refreshLayout) {
                getHomeData();
            }
        });

        mViewModel.initLbData();
        getHomeData();
    }


    private void getHomeData() {
        mViewModel.getCommodityIndex(AccountHelper.getUserId(), new FailCallback() {
            @Override
            public void onFail(NetResponse netResponse) {
                mBinding.swRefresh.finishRefresh();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.gxtj_iv:
                Toast.makeText(getContext(), "正在开发中", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}