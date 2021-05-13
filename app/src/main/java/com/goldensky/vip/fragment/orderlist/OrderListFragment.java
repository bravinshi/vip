package com.goldensky.vip.fragment.orderlist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.OrderListAdapter;
import com.goldensky.vip.base.fragment.LazyLoadFragment;
import com.goldensky.vip.bean.OrderListBean;
import com.goldensky.vip.databinding.FragmentOrderListBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.order.OrderListViewModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


public class OrderListFragment extends LazyLoadFragment<FragmentOrderListBinding, OrderListViewModel> {
    private final Integer ALL = 0;
    private final Integer NON_PAYMENT = 1;
    private final Integer WAIT_RECEIVING = 2;
    private final Integer FINISHED = 3;
    private Integer fragmentType = 0;
    private Integer orderListType = null;
    private List<OrderListBean> orderDetailLists=new ArrayList<>();
    public OrderListFragment(Integer fragmentType) {
        this.fragmentType = fragmentType;
    }
    private OrderListAdapter adapter;
    @Override
    public void onLazyLoad() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_order_list;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
//        0:未付款 1:待发货  2:待收货 3:已完成 4:关闭 5:已取消',
        if (fragmentType == ALL) {
            orderListType = null;
        } else if (fragmentType == NON_PAYMENT) {
            orderListType = 0;
        } else if (fragmentType == WAIT_RECEIVING) {
            orderListType = 2;
        } else if (fragmentType == FINISHED) {
            orderListType = 3;
        }
        mViewModel.getOrderList(AccountHelper.getUserId(), orderListType);
        mViewModel.getOrderListLive.observe(this, new Observer<List<OrderListBean>>() {
            @Override
            public void onChanged(List<OrderListBean> orderListBean) {
                setOrderList(orderListBean);
                mBinding.smartOrderList.finishRefresh();
            }
        });
        mBinding.smartOrderList.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mViewModel.getOrderList(AccountHelper.getUserId(), orderListType);
            }
        });
        mBinding.rvOrderList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter=new OrderListAdapter(orderDetailLists);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("orderNumber",orderDetailLists.get(position).getOrdernumber());
                bundle.putInt("orderType",orderDetailLists.get(position).getOrderstatus());
                Starter.startOrderDetailActivity(getContext(),bundle);
            }
        });
        adapter.addChildClickViewIds(new int[]{R.id.btn_red_item_orderlist,R.id.btn_gray_item_orderlist});
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(view.getId()==R.id.btn_red_item_orderlist){
                    switch (orderDetailLists.get(position).getOrderstatus()){
                        case 0:

                            break;
                        case 1:
                        case 2:
                            mViewModel.updateOrder(orderDetailLists.get(position).getOrdernumber(),3);
                            break;
                    }
                }
            }
        });
        mViewModel.updateOrderLive.observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                mViewModel.getOrderList(AccountHelper.getUserId(),orderListType);
            }
        });
        mBinding.rvOrderList.setAdapter(adapter);
    }

    private void setOrderList(List<OrderListBean> list) {
        orderDetailLists.clear();
        orderDetailLists.addAll(list);
        adapter.notifyDataSetChanged();
    }
}