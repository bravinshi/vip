package com.goldensky.vip.fragment.main;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldensky.vip.R;
import com.goldensky.vip.adapter.CircleFocusAdapter;
import com.goldensky.vip.adapter.GoodsFocusAdapter;
import com.goldensky.vip.base.fragment.BaseFragment;
import com.goldensky.vip.databinding.FragmentShoppingCartBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;


public class ShoppingCartFragment extends BaseFragment<FragmentShoppingCartBinding, PublicViewModel> {
    private boolean isEdit=false;
    private CircleFocusAdapter adapter;
    private List<Integer> list=new ArrayList<>();
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_shopping_cart;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mBinding.tvSum.setText(Html.fromHtml("合计:<font color=\"#EA483F\">¥0.00</font>"));
        mBinding.topBarShoppingCart.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        list.add(R.mipmap.gwc1);
        list.add(R.mipmap.gwc2);
        list.add(R.mipmap.gwc3);
        list.add(R.mipmap.gwc4);
        adapter=new CircleFocusAdapter(list);
        mBinding.rvShoppingCart.setLayoutManager(new LinearLayoutManager(getContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        mBinding.rvShoppingCart.setAdapter(adapter);

    }
}