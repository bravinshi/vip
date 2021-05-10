package com.goldensky.vip;

import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.goldensky.vip.adapter.FragmentAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.AreaListBean;
import com.goldensky.vip.databinding.ActivityMainBinding;
import com.goldensky.vip.fragment.main.CircleFragment;
import com.goldensky.vip.fragment.main.HomeFragment;
import com.goldensky.vip.fragment.main.MessageFragment;
import com.goldensky.vip.fragment.main.MineFragment;
import com.goldensky.vip.fragment.main.ShoppingCartFragment;
import com.goldensky.vip.helper.UserAddressHelper;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.gyf.immersionbar.ImmersionBar;

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
        if(!UserAddressHelper.getInstance().isAreaLoad()){
            mViewModel.getAreaList();
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
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onClick(View v) {
        Starter.startCustomerServiceActivity(this,null);
    }
}