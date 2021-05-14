package com.goldensky.vip.fragment.main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.HomeAdapter;
import com.goldensky.vip.base.error.FailCallback;
import com.goldensky.vip.base.fragment.BaseFragment;
import com.goldensky.vip.bean.HomeBean;
import com.goldensky.vip.bean.SuperStBean;
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
    private SuperStBean mSuperStBean;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mBinding.gxtjIv.setOnClickListener(this);

        mHomeAdapter = new HomeAdapter(mViewModel.homeBeans);
        mHomeAdapter.addChildClickViewIds(new int[]{ R.id.more_iv, R.id.more_tv});
        mHomeAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                HomeBean homeBean = mHomeAdapter.getItem(position);
                if (homeBean.getItemType() == HomeAdapter.ITEM_TYPE_RMD && view.getId() == R.id.more_iv) {
                    Starter.startRecommendActivity(getContext(), null);
                } else if (homeBean.getItemType() == HomeAdapter.ITEM_TYPE_JRBK && view.getId() == R.id.more_iv) {
                    Starter.startHotTodayActivity(getContext(), null);
                } else if (homeBean.getItemType() == HomeAdapter.ITEM_TYPE_JTYX && view.getId() == R.id.more_tv) {
                    Starter.startOptimizationActivity(getContext(), null);
                }
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
        mBinding.swHeader.setEnableLastTime(false);

        mViewModel.mSuperStBean.observe(this, stBean -> {
            if (stBean != null) {
                if (!StringUtils.isEmpty(stBean.getStorename())) {
                    mSuperStBean.setStorename(stBean.getStorename());
                }
                if (!StringUtils.isEmpty(stBean.getUserpic())) {
                    mSuperStBean.setUserpic(stBean.getUserpic());
                }

                if (!StringUtils.isEmpty(stBean.getEnterprisename())) {
                    mSuperStBean.setEnterprisename(stBean.getEnterprisename());
                }
                updateStInfo();
            }
        });

        mViewModel.initLbData();
        mSuperStBean = new SuperStBean("https://file.jtmsh.com/data/jintianhezong/mini-program/jthzLogo/jthzLogo.png","名食汇金天合纵商城","");
        updateStInfo();
        getHomeData();
    }

    private void updateStInfo() {
        mBinding.nicknameTv.setText(mSuperStBean.getStorename());
        Glide.with(getContext()).load(mSuperStBean.getUserpic()).into(mBinding.portaritIv);
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
    public void onResume() {
        super.onResume();
        mViewModel.getSuperSt(AccountHelper.getUserSuperiorId());
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