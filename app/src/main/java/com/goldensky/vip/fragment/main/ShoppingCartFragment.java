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
    private GoodsFocusAdapter adapter;
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
//        mBinding.tvEditFinish.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(isEdit){
//                    mBinding.tvSum.setVisibility(View.VISIBLE);
//                    mBinding.tvCloseAccount.setText("去结算");
//                    mBinding.tvEditFinish.setText("编辑");
//                    mBinding.tvEditFinish.setTextColor(Color.parseColor("#333333"));
//                    mBinding.tvCloseAccount.setTextColor(Color.parseColor("#ffffff"));
//                    mBinding.tvCloseAccount.setBackgroundResource(R.drawable.shape_btn_red);
//                }else {
//                    mBinding.tvSum.setVisibility(View.GONE);
//                    mBinding.tvCloseAccount.setText("删除");
//                    mBinding.tvEditFinish.setText("完成");
//                    mBinding.tvEditFinish.setTextColor(Color.parseColor("#E65858"));
//                    mBinding.tvCloseAccount.setTextColor(Color.parseColor("#888888"));
//                    mBinding.tvCloseAccount.setBackgroundResource(R.drawable.shape_btn_gray);
//                }
//                isEdit=!isEdit;
//            }
//        });
        list.add(R.mipmap.gwuc_img_cha);
        list.add(R.mipmap.gwc_img_mangguo);
        list.add(R.mipmap.gwc_img_chengzi);
        list.add(R.mipmap.gwc_img_hongchang);
        adapter=new GoodsFocusAdapter(list);
        mBinding.rvShoppingCart.setLayoutManager(new LinearLayoutManager(getContext()));
        mBinding.rvShoppingCart.setAdapter(adapter);

    }
}