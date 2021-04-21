package com.goldensky.vip.fragment.mine;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldensky.vip.R;
import com.goldensky.vip.adapter.MineToolAdapter;
import com.goldensky.vip.base.fragment.BaseFragment;
import com.goldensky.vip.bean.MineToolBean;
import com.goldensky.vip.databinding.FragmentMineBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;


public class MineFragment extends BaseFragment<FragmentMineBinding, PublicViewModel> {
    private List<MineToolBean> orderList=new ArrayList<>();
    private List<MineToolBean> toolList=new ArrayList<>();
    private MineToolAdapter orderAdapter;
    private MineToolAdapter toolAdapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        orderList.add(new MineToolBean(R.mipmap.my_icon_daifukuan,"待付款"));
        orderList.add(new MineToolBean(R.mipmap.my_icon_daishouhuo,"待收货"));
        orderList.add(new MineToolBean(R.mipmap.my_icon_daipingjia,"待评价"));
        orderList.add(new MineToolBean(R.mipmap.my_icon_dingdan,"我的订单"));
        toolList.add(new MineToolBean(R.mipmap.my_icon_dizhi,"我的地址"));
        toolList.add(new MineToolBean(R.mipmap.my_icon_fenxiang,"好友分享"));
        mBinding.rvOrderMine.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        mBinding.rvToolMine.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
        orderAdapter=new MineToolAdapter(orderList);
        toolAdapter=new MineToolAdapter(toolList);
        mBinding.rvOrderMine.setAdapter(orderAdapter);
        mBinding.rvToolMine.setAdapter(toolAdapter);
    }
}