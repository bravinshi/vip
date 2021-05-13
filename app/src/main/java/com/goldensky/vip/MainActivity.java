package com.goldensky.vip;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.goldensky.vip.adapter.FragmentAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.AreaListBean;
import com.goldensky.vip.bean.ShoppingCartGoodsBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.databinding.ActivityMainBinding;
import com.goldensky.vip.fragment.main.HomeFragment;
import com.goldensky.vip.fragment.main.MineFragment;
import com.goldensky.vip.fragment.main.ShoppingCartFragment;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.helper.ShoppingCartHelper;
import com.goldensky.vip.helper.UserAddressHelper;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, PublicViewModel> implements View.OnClickListener {
    private FragmentAdapter fragmentAdapter;
    private List<Fragment> fragments;

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
//        ImmersionBar.with(this).statusBarDarkFont(true)
//                .statusBarView(mBinding.vStatusBar).init();
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
//        fragments.add(new MessageFragment());
//        fragments.add(new CircleFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new MineFragment());
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        mBinding.viewPagerMain.setAdapter(fragmentAdapter);
        mBinding.viewPagerMain.setUserInputEnabled(false);
        mBinding.bottomTabMain.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.item_home:
                    mBinding.viewPagerMain.setCurrentItem(0, false);
                    break;
//                case R.id.item_message:
//                    mBinding.viewPagerMain.setCurrentItem(1, false);
//                    break;
//                case R.id.item_circle:
//                    mBinding.viewPagerMain.setCurrentItem(2, false);
//                    break;
                case R.id.item_shopping_cart:
                    mBinding.viewPagerMain.setCurrentItem(1, false);
                    break;
                case R.id.item_mine:
                    mBinding.viewPagerMain.setCurrentItem(2, false);
                    break;
            }
            return true;
        });
        mBinding.bottomTabMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        mBinding.viewPagerMain.setOffscreenPageLimit(5);
        mBinding.setListener(this);
        if (!UserAddressHelper.getInstance().isAreaLoad()) {
            mViewModel.getAreaList();
        }
        if(!ShoppingCartHelper.getInstance().isShoppingCartLoad()){
            mViewModel.getShoppingCartList(AccountHelper.getUserId());
        }
        if(!UserAddressHelper.getInstance().isUserAddressLoad()){
            mViewModel.getUserAddress(AccountHelper.getUserId());
        }
    }

    @Override
    public void observe() {
        mViewModel.areaListLive.observe(this, new Observer<List<AreaListBean>>() {
            @Override
            public void onChanged(List<AreaListBean> areaListBean) {
                UserAddressHelper.getInstance().loadAddressList(areaListBean);
            }
        });
        mViewModel.userAddressLive.observe(this, new Observer<List<UserAddressBean>>() {
            @Override
            public void onChanged(List<UserAddressBean> userAddressBeans) {
                UserAddressHelper.getInstance().setUserAddressList(userAddressBeans);
            }
        });
        mViewModel.shoppingCartListLive.observe(this, new Observer<List<ShoppingCartGoodsBean>>() {
            @Override
            public void onChanged(List<ShoppingCartGoodsBean> shoppingCartGoodsBeans) {
                ShoppingCartHelper.getInstance().setShoppingCartGoodsBeanList(shoppingCartGoodsBeans);
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        Starter.startCustomerServiceActivity(this, null);
    }
}