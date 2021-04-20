package com.goldensky.vip;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.goldensky.vip.adapter.FragmentAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityMainBinding;
import com.goldensky.vip.fragment.home.HomeFragment;
import com.goldensky.vip.fragment.mine.MineFragment;
import com.goldensky.vip.fragment.shoppingcart.ShoppingCartFragment;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, PublicViewModel> {
    private FragmentAdapter fragmentAdapter;
    private List<Fragment> fragments;

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        ImmersionBar.with(this).statusBarDarkFont(true)
                .statusBarView(mBinding.vStatusBar).init();
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ShoppingCartFragment());
        fragments.add(new MineFragment());
        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        mBinding.viewPagerMain.setAdapter(fragmentAdapter);
        mBinding.viewPagerMain.setUserInputEnabled(false);
        mBinding.bottomTabMain.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            switch (itemId) {
                case R.id.item_home:
                    mBinding.viewPagerMain.setCurrentItem(0);
                    break;
                case R.id.item_shopping_cart:
                    mBinding.viewPagerMain.setCurrentItem(1);
                    break;
                case R.id.item_mine:
                    mBinding.viewPagerMain.setCurrentItem(2);
                    break;
            }
            return true;
        });
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}