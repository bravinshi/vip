package com.goldensky.vip.activity.mine.tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.framework.ui.view.NoScrollStaggeredGridLayoutManager;
import com.goldensky.vip.R;
import com.goldensky.vip.adapter.MyToolAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityMyToolsTestBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyToolsTestActivity extends BaseActivity<ActivityMyToolsTestBinding, PublicViewModel> {
    private MyToolAdapter serviceAdapter;
    private MyToolAdapter goodsAdapter;
    private MyToolAdapter mallAdapter;
    private MyToolAdapter accountAdapter;
    private List<Integer> serviseList=new ArrayList<>();
    private List<Integer> goodsList=new ArrayList<>();
    private List<Integer> mallList=new ArrayList<>();
    private List<Integer> accountList=new ArrayList<>();
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        serviseList.add(R.mipmap.my_pic_vipguanli);
        serviseList.add(R.mipmap.my_pic_shangchengguanli);
        goodsList.add(R.mipmap.my_pic_jinhuoguanli);
        goodsList.add(R.mipmap.my_pic_dingdanguanli);
        goodsList.add(R.mipmap.my_pic_fenxiaodingdan);
        mallList.add(R.mipmap.my_pic_shangchengshouye);
        mallList.add(R.mipmap.my_pic_zhanghaoguanli);
        mallList.add(R.mipmap.my_pic_gongsiziliao);
        accountList.add(R.mipmap.my_pic_jiaoyizhanghu);
        accountList.add(R.mipmap.my_pic_jiesuanzhanghu);
        serviceAdapter=new MyToolAdapter(serviseList);
        goodsAdapter=new MyToolAdapter(goodsList);
        mallAdapter=new MyToolAdapter(mallList);
        accountAdapter=new MyToolAdapter(accountList);
        mBinding.rvServiceManageToolsTest.setLayoutManager(new NoScrollStaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL,false));
        mBinding.rvGoodsManageToolsTest.setLayoutManager(new NoScrollStaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL,false));
        mBinding.rvMallManageToolsTest.setLayoutManager(new NoScrollStaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL,false));
        mBinding.rvAccountManageToolsTest.setLayoutManager(new NoScrollStaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL,false));
        mBinding.rvServiceManageToolsTest.setAdapter(serviceAdapter);
        mBinding.rvAccountManageToolsTest.setAdapter(accountAdapter);
        mBinding.rvGoodsManageToolsTest.setAdapter(goodsAdapter);
        mBinding.rvMallManageToolsTest.setAdapter(mallAdapter);
        mBinding.topBarMyToolsTest.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_tools_test;
    }
}