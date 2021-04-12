package com.goldensky.vip.fragment.shoppingcart;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldensky.vip.R;
import com.goldensky.vip.base.fragment.BaseFragment;
import com.goldensky.vip.databinding.FragmentShoppingCartBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;


public class ShoppingCartFragment extends BaseFragment<FragmentShoppingCartBinding, PublicViewModel> {

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }
}