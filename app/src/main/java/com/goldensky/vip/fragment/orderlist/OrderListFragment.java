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
import com.goldensky.vip.bean.ExpressBean;
import com.goldensky.vip.bean.LogisticsBean;
import com.goldensky.vip.bean.LogisticsReqBean;
import com.goldensky.vip.bean.OrderListBean;
import com.goldensky.vip.databinding.FragmentOrderListBinding;
import com.goldensky.vip.event.ChangeOrderStatusEvent;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.order.OrderListViewModel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class OrderListFragment extends LazyLoadFragment<FragmentOrderListBinding, OrderListViewModel> {
    private final Integer ALL = 0;
    private final Integer NON_PAYMENT = 1;
    private final Integer WAIT_RECEIVING = 2;
    private final Integer FINISHED = 3;
    private Integer fragmentType = 0;
    private Integer orderListType = null;
    private ExpressBean express;
    private List<OrderListBean> orderDetailLists = new ArrayList<>();

    public OrderListFragment(Integer fragmentType) {
        this.fragmentType = fragmentType;
    }

    private OrderListAdapter adapter;
    private int checkPosition = 0;

    @Override
    public void onLazyLoad() {

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_order_list;
    }


    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
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
        adapter = new OrderListAdapter(orderDetailLists);
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("orderNumber", orderDetailLists.get(position).getOrdernumber());
                bundle.putInt("orderType", orderDetailLists.get(position).getOrderstatus());
                Starter.startOrderDetailActivity(getContext(), bundle);
            }
        });
        adapter.addChildClickViewIds(new int[]{R.id.btn_red_item_orderlist, R.id.btn_gray_item_orderlist});
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if (view.getId() == R.id.btn_red_item_orderlist) {
                    switch (orderDetailLists.get(position).getOrderstatus()) {
                        case 0:
                            Bundle bundle = new Bundle();
                            bundle.putString("orderNumber", orderDetailLists.get(position).getOrdernumber());
                            bundle.putInt("orderType", orderDetailLists.get(position).getOrderstatus());
                            Starter.startOrderDetailActivity(getContext(), bundle);
                            break;
                        case 2:
                            mViewModel.updateOrder(orderDetailLists.get(position).getOrdernumber(), 3);
                            break;
                    }
                } else if(view.getId() == R.id.btn_gray_item_orderlist) {
                    switch (orderDetailLists.get(position).getOrderstatus()) {
                        case 2:
                            mViewModel.getExpress(orderDetailLists.get(position).getOrdernumber());
                            checkPosition = position;
                            break;
                    }
                }
            }
        });
        mViewModel.updateOrderLive.observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                mViewModel.getOrderList(AccountHelper.getUserId(), orderListType);
            }
        });
        mBinding.rvOrderList.setAdapter(adapter);
        mViewModel.expressLive.observe(this, new Observer<ExpressBean>() {
            @Override
            public void onChanged(ExpressBean expressBean) {
                express = expressBean;
                LogisticsReqBean bean = new LogisticsReqBean();
                bean.setTo(expressBean.getProvince() + expressBean.getCity() + expressBean.getArea());
                bean.setCom(expressBean.getExpresscode());
                bean.setNum(expressBean.getExpressnumber());
                bean.setPhone(expressBean.getUseraddressphone());
                bean.setOrder("desc");
                bean.setResultv2("1");
                bean.setShow("0");
                bean.setFrom("");
                mViewModel.getLogistics(bean);
            }
        });
        mViewModel.getLogisticsLive.observe(this, new Observer<LogisticsBean>() {
            @Override
            public void onChanged(LogisticsBean logisticsBean) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("logistics", logisticsBean);
                bundle.putString("pic", orderDetailLists.get(0).getOrderDetailList().get(0).getInventorypic());
                Starter.startLogisticsActivity(getContext(), bundle);
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void orderStatusChange(ChangeOrderStatusEvent event) {
        if (event.getSuccess()) {
            mViewModel.getOrderList(AccountHelper.getUserId(), orderListType);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void setOrderList(List<OrderListBean> list) {
        orderDetailLists.clear();
        orderDetailLists.addAll(list);
        adapter.notifyDataSetChanged();
        if (orderDetailLists.size() == 0) {
            mBinding.rvOrderList.setVisibility(View.GONE);
            mBinding.includeOrder.clEmptyOrder.setVisibility(View.VISIBLE);
        } else {
            mBinding.rvOrderList.setVisibility(View.VISIBLE);
            mBinding.includeOrder.clEmptyOrder.setVisibility(View.GONE);
        }
    }
}