package com.goldensky.vip.fragment.main;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.CircleFocusAdapter;
import com.goldensky.vip.base.fragment.LazyLoadFragment;
import com.goldensky.vip.databinding.FragmentMessageBinding;
import com.goldensky.vip.databinding.FragmentMineBindingImpl;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends LazyLoadFragment<FragmentMessageBinding, PublicViewModel> {
    private CircleFocusAdapter adapter;
    private List<Integer> list=new ArrayList<>();

    @Override
    public void onLazyLoad() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        list.add(R.mipmap.my_pic_xitongxiaoxi);
        list.add(R.mipmap.my_pic_hezongzhibo);
        list.add(R.mipmap.my_pic_tuiguangxiaoxi);
        list.add(R.mipmap.my_pic_kepuxiaoxi);
        list.add(R.mipmap.my_pic_xiaoxitongzhi);
        adapter=new CircleFocusAdapter(list);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        mBinding.rvMessage.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvMessage.setAdapter(adapter);
        mBinding.jywl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Starter.startTradeLogisticsActivity(getContext(),null);
            }
        });
    }
}