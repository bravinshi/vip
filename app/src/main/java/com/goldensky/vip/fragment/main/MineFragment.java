package com.goldensky.vip.fragment.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.MineToolAdapter;
import com.goldensky.vip.base.fragment.BaseFragment;
import com.goldensky.vip.base.fragment.LazyLoadFragment;
import com.goldensky.vip.bean.MineToolBean;
import com.goldensky.vip.databinding.FragmentMineBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;


public class MineFragment extends LazyLoadFragment<FragmentMineBinding, PublicViewModel> implements View.OnClickListener {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void startOrderList(Integer orderType) {
        Bundle bundle = new Bundle();
        bundle.putInt("orderType",orderType);
        Starter.startOrderListActivity(getContext(),bundle);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onLazyLoad() {

    }
}