package com.goldensky.vip.fragment.main;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.goldensky.framework.ui.view.NumberButton;
import com.goldensky.framework.util.GsonUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.ShoppingCartListAdapter;
import com.goldensky.vip.base.fragment.LazyLoadFragment;
import com.goldensky.vip.bean.ConfirmOrderItemBean;
import com.goldensky.vip.bean.ShoppingCartGoodsBean;
import com.goldensky.vip.databinding.FragmentShoppingCartBinding;
import com.goldensky.vip.event.ShoppingCartChangeEvent;
import com.goldensky.vip.event.ShoppingCartRefreshEvent;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.helper.ShoppingCartHelper;
import com.goldensky.vip.viewmodel.shoppingcart.ShoppingCartViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class ShoppingCartFragment extends LazyLoadFragment<FragmentShoppingCartBinding, ShoppingCartViewModel> implements View.OnClickListener {
    private boolean isEdit = false;
    private ShoppingCartListAdapter adapter;
    private List<ShoppingCartGoodsBean> shoppingCartGoodsList = new ArrayList<>();
    private PopupWindow mPopupWindow;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mBinding.setListener(this);
        mBinding.rvShoppingCart.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ShoppingCartListAdapter(shoppingCartGoodsList);
        mBinding.rvShoppingCart.setAdapter(adapter);
        adapter.setOnCountChangeListener(new NumberButton.OnCountChangeListener() {
            @Override
            public void onChange(NumberButton button) {
                ShoppingCartGoodsBean shoppingCartGoodsBean = (ShoppingCartGoodsBean) button.getTag();
                mViewModel.updateCartGoodsNumber(shoppingCartGoodsBean.getBelongtype(), shoppingCartGoodsBean.getCommodityid(), shoppingCartGoodsBean.getCommoditytype(), shoppingCartGoodsBean.getInventoryid(), button.getCount(), shoppingCartGoodsBean.getShoppingcartid(), shoppingCartGoodsBean.getUserid(), button);
                setSumMoney();
            }
        });
        adapter.addChildClickViewIds(new int[]{R.id.select_item_shopping_cart});
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(view.getId()==R.id.select_item_shopping_cart){
                    CheckBox box = (CheckBox) view;
                    ShoppingCartHelper.getInstance().changeGoodsChecked(shoppingCartGoodsList.get(position).getShoppingcartid(),box.isChecked());
                }
            }
        });
        mViewModel.deleteCartGoodsLive.observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                mViewModel.getShoppingCartList(AccountHelper.getUserId());
                if(isEdit){
                    toast(getResources().getString(R.string.hint_delete_success));
                    ShoppingCartHelper.getInstance().clearSelect();
                }
            }
        });
        setSumMoney();
        mViewModel.shoppingCartListLive.observe(this, new Observer<List<ShoppingCartGoodsBean>>() {
            @Override
            public void onChanged(List<ShoppingCartGoodsBean> shoppingCartGoodsBeans) {
                ShoppingCartHelper.getInstance().setShoppingCartGoodsBeanList(shoppingCartGoodsBeans);
            }
        });
        mViewModel.updateCartGoodsNumberLive.observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                mViewModel.getShoppingCartList(AccountHelper.getUserId());
            }
        });
        mViewModel.getShoppingCartList(AccountHelper.getUserId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeShoppingCart(ShoppingCartChangeEvent event){
        if(event.isRefresh()){
            refreshShoppingCartList();
        }else {
            mBinding.checkboxSelectAll.setChecked(ShoppingCartHelper.getInstance().isSelectAll());
            setSumMoney();
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void reloadShoppingCart(ShoppingCartRefreshEvent event){
        mViewModel.getShoppingCartList(AccountHelper.getUserId());
    }
    /**
     * 刷新列表并计算
     */
    private void refreshShoppingCartList() {

        shoppingCartGoodsList.clear();
        shoppingCartGoodsList.addAll(ShoppingCartHelper.getInstance().getGoodsBeanList());
        adapter.notifyDataSetChanged();
        mBinding.checkboxSelectAll.setChecked(ShoppingCartHelper.getInstance().isSelectAll());
        setSumMoney();
        if(shoppingCartGoodsList.size()==0){
            mBinding.llShoppingCart.setVisibility(View.GONE);
            mBinding.includeShoppingCart.clShoppingCart.setVisibility(View.VISIBLE);
        }else {
            mBinding.llShoppingCart.setVisibility(View.VISIBLE);
            mBinding.includeShoppingCart.clShoppingCart.setVisibility(View.GONE);
        }
    }

    private void setSumMoney() {
        Double sumMoney = ShoppingCartHelper.getInstance().getSumMoney();
       if(sumMoney==0.00){
           mBinding.tvSum.setText(Html.fromHtml("合计:<font color=\"#EA483F\">¥0.00</font>"));
       }else {
           String format = new DecimalFormat("#.00").format(sumMoney);
           mBinding.tvSum.setText(Html.fromHtml("合计:<font color=\"#EA483F\">¥" + format + "</font>"));
       }
    }

    @Override
    public void onLazyLoad() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_edit_finish:
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
                break;
            case R.id.tv_close_account:
                if(isEdit){
                   if (ShoppingCartHelper.getInstance().hasSelect()){
                       View popView = LayoutInflater.from(getContext()).inflate(R.layout.pop_delete_shopping_cart, null);
                       TextView confirm = popView.findViewById(R.id.btn_confirm_delete);
                       TextView cancel = popView.findViewById(R.id.btn_cancel_delete);
                       confirm.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               mViewModel.deleteCartGoods(ShoppingCartHelper.getInstance().getShoppingCartIds());
                               mPopupWindow.dismiss();
                           }
                       });
                       cancel.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View v) {
                               mPopupWindow.dismiss();
                           }
                       });
                       mPopupWindow = new PopupWindow(popView, -1, -2);
                       mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                       mPopupWindow.setOutsideTouchable(true);
                       mPopupWindow.setFocusable(true);
                       WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                       lp.alpha = 0.5f;
                       getActivity().getWindow().setAttributes(lp);
                       mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                           @Override
                           public void onDismiss() {
                               WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                               lp.alpha = 1f;
                               getActivity().getWindow().setAttributes(lp);
                           }
                       });
                       mPopupWindow.setAnimationStyle(R.style.main_menu_photo_anim);
                       mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                       mPopupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
                       mPopupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, 0);
                   }else {
                       toast("请选择要删除的商品!");
                   }

                }else {
                   if(ShoppingCartHelper.getInstance().hasSelect()){
                       Bundle bundle = new Bundle();
                       List<ConfirmOrderItemBean> confirmOrderList = ShoppingCartHelper.getInstance().getConfirmOrderList();
                       String json = GsonUtils.toJson(confirmOrderList);
                       bundle.putString("KEY_GOODS",json);
                       Starter.startConfirmOrderActivity(getContext(),bundle);
                   }else {
                       toast("请选择要结算的商品!");
                   }
                }
                break;
            case R.id.checkbox_select_all:
                ShoppingCartHelper.getInstance().changeSelectAllGoods(mBinding.checkboxSelectAll.isChecked());
                adapter.notifyDataSetChanged();
                setSumMoney();
                break;

        }

    }
}