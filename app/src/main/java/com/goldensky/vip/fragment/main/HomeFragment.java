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
import com.goldensky.vip.R;
import com.goldensky.vip.adapter.HomeAdapter;
import com.goldensky.vip.base.fragment.BaseFragment;
import com.goldensky.vip.bean.HomeBean;
import com.goldensky.vip.databinding.FragmentHomeBinding;
import com.goldensky.vip.viewmodel.home.HomeViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements View.OnClickListener {

    private HomeAdapter mHomeAdapter;
    private List<HomeBean> mHomeBeans = new ArrayList<>();


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mBinding.gxtjIv.setOnClickListener(this);
        mHomeAdapter = new HomeAdapter(mHomeBeans);
        mHomeAdapter.addChildClickViewIds(new int[]{ R.id.more_iv});
        mHomeAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mBinding.recycleView.setLayoutManager(linearLayoutManager);
        mBinding.recycleView.setAdapter(mHomeAdapter);


        mViewModel.homeBeans.observe(this, new Observer<List<HomeBean>>() {
            @Override
            public void onChanged(List<HomeBean> homeBeans) {
                mHomeBeans.clear();
                mHomeBeans.addAll(homeBeans);
                mHomeAdapter.notifyDataSetChanged();
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