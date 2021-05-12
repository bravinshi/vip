package com.goldensky.vip.fragment.main;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Html;
import android.view.View;

import com.goldensky.framework.ui.view.NumberButton;
import com.goldensky.vip.R;
import com.goldensky.vip.adapter.ShoppingCartListAdapter;
import com.goldensky.vip.base.fragment.BaseFragment;
import com.goldensky.vip.base.fragment.LazyLoadFragment;
import com.goldensky.vip.bean.ShoppingCartGoodsBean;
import com.goldensky.vip.databinding.FragmentShoppingCartBinding;
import com.goldensky.vip.event.ShoppingCartChangeEvent;
import com.goldensky.vip.helper.ShoppingCartHelper;
import com.goldensky.vip.helper.UserAddressHelper;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.goldensky.vip.viewmodel.shoppingcart.ShoppingCartViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCartFragment extends LazyLoadFragment<FragmentShoppingCartBinding, ShoppingCartViewModel> {
    private boolean isEdit = false;
    private ShoppingCartListAdapter adapter;
    private List<ShoppingCartGoodsBean> shoppingCartGoodsList = new ArrayList<>();

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mBinding.tvEditFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEdit) {
                    mBinding.tvSum.setVisibility(View.VISIBLE);
                    mBinding.tvCloseAccount.setText("去结算");
                    mBinding.tvEditFinish.setText("编辑");
                    mBinding.tvEditFinish.setTextColor(Color.parseColor("#333333"));
                    mBinding.tvCloseAccount.setTextColor(Color.parseColor("#ffffff"));
                    mBinding.tvCloseAccount.setBackgroundResource(R.drawable.shape_btn_red);
                } else {
                    mBinding.tvSum.setVisibility(View.GONE);
                    mBinding.tvCloseAccount.setText("删除");
                    mBinding.tvEditFinish.setText("完成");
                    mBinding.tvEditFinish.setTextColor(Color.parseColor("#E65858"));
                    mBinding.tvCloseAccount.setTextColor(Color.parseColor("#888888"));
                    mBinding.tvCloseAccount.setBackgroundResource(R.drawable.shape_btn_gray);
                }
                isEdit = !isEdit;
            }
        });
        mBinding.rvShoppingCart.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShoppingCartListAdapter(shoppingCartGoodsList);
        mBinding.rvShoppingCart.setAdapter(adapter);
        adapter.setListener(new NumberButton.OnCountChangeListener() {
            @Override
            public void onChange(NumberButton button) {
                ShoppingCartGoodsBean shoppingCartGoodsBean = (ShoppingCartGoodsBean) button.getTag();
                mViewModel.updateCartGoodsNumber(shoppingCartGoodsBean.getBelongtype(),shoppingCartGoodsBean.getCommodityid(),shoppingCartGoodsBean.getCommoditytype(),shoppingCartGoodsBean.getInventoryid(),button.getCount(),shoppingCartGoodsBean.getShoppingcartid(),shoppingCartGoodsBean.getUserid(),button);
                ShoppingCartHelper.getInstance().changeCartGoodsNumber(shoppingCartGoodsBean.getShoppingcartid(),button.getCount());
            }
        });
        mViewModel.updateCartGoodsNumberLive.observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {

            }
        });
        setSumMoney("0.00");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshShoppingCart(ShoppingCartChangeEvent event){
        if(event.getSuccess()){
            refreshShoppingCartList();
        }
    }
    private void refreshShoppingCartList() {
        shoppingCartGoodsList.clear();
        shoppingCartGoodsList .addAll(ShoppingCartHelper.getInstance().getGoodsBeanList());
        adapter.notifyDataSetChanged();
    }
    private void setSumMoney(String s) {
        mBinding.tvSum.setText(Html.fromHtml("合计:<font color=\"#EA483F\">¥" + s + "</font>"));
    }

    @Override
    public void onLazyLoad() {

    }
}