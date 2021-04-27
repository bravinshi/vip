package com.goldensky.vip.activity.mine.tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.goldensky.framework.ui.view.NoScrollStaggeredGridLayoutManager;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.MyToolAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityMyToolsBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyToolsActivity extends BaseActivity<ActivityMyToolsBinding, PublicViewModel> {
    private MyToolAdapter serviceAdapter;
    private MyToolAdapter goodsAdapter;
    private MyToolAdapter mallAdapter;
    private MyToolAdapter accountAdapter;
    private MyToolAdapter elseAdapter;
    private List<Integer> serviseList=new ArrayList<>();
    private List<Integer> goodsList=new ArrayList<>();
    private List<Integer> mallList=new ArrayList<>();
    private List<Integer> accountList=new ArrayList<>();
    private List<Integer> elseList=new ArrayList<>();

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        serviseList.add(R.mipmap.my_pic_vipguanli);
        serviseList.add(R.mipmap.my_pic_shangchengguanli);
        goodsList.add(R.mipmap.my_pic_jinhuoguanli);
        goodsList.add(R.mipmap.my_pic_dingdanguanli);
        goodsList.add(R.mipmap.my_pic_zhuyingshangping);
        goodsList.add(R.mipmap.my_pic_xiaoshoudingdan);
        goodsList.add(R.mipmap.my_pic_fenxiaodingdan);
        mallList.add(R.mipmap.my_pic_shangchengshouye);
        mallList.add(R.mipmap.my_pic_zhanghaoguanli);
        mallList.add(R.mipmap.my_pic_gongsiziliao);
        mallList.add(R.mipmap.my_pic_wodezhibo);
        accountList.add(R.mipmap.my_pic_jinyinfenxi);
        accountList.add(R.mipmap.my_pic_jiaoyizhanghu);
        accountList.add(R.mipmap.my_pic_jiesuanzhanghu);
        elseList.add(R.mipmap.my_pic_gongsiguanli);
        serviceAdapter=new MyToolAdapter(serviseList);
        goodsAdapter=new MyToolAdapter(goodsList);
        mallAdapter=new MyToolAdapter(mallList);
        accountAdapter=new MyToolAdapter(accountList);
        elseAdapter=new MyToolAdapter(elseList);
        mBinding.rvServiceManageTools.setLayoutManager(new NoScrollStaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL,false));
        mBinding.rvGoodsManageTools.setLayoutManager(new NoScrollStaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL,false));
        mBinding.rvMallManageTools.setLayoutManager(new NoScrollStaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL,false));
        mBinding.rvAccountManageTools.setLayoutManager(new NoScrollStaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL,false));
        mBinding.rvElseManageTools.setLayoutManager(new NoScrollStaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL,false));
        mBinding.rvServiceManageTools.setAdapter(serviceAdapter);
        mBinding.rvAccountManageTools.setAdapter(accountAdapter);
        mBinding.rvGoodsManageTools.setAdapter(goodsAdapter);
        mBinding.rvMallManageTools.setAdapter(mallAdapter);
        mBinding.rvElseManageTools.setAdapter(elseAdapter);
        mBinding.topBarMyTools.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        serviceAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    Starter.startVipManageActivity(MyToolsActivity.this, null);
                } else if (position == 1) {
                    Starter.startMallMangeActivity(MyToolsActivity.this, null);
                }
            }
        });

        goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        Starter.startJhActivity(MyToolsActivity.this);
                        break;
                    case 1:
                        Starter.startOrderListActivity(MyToolsActivity.this,null);
                        break;
                    case 2:
                        Starter.startMainGoodsActivity(MyToolsActivity.this,null);
                        break;
                    case 3:
                        Starter.startSxOrderActivity(MyToolsActivity.this);
                        break;
                    case 4:
                        Starter.startFxOrderActivity(MyToolsActivity.this);
                        break;
                }
            }
        });

        mallAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {
                    Starter.startVipHomeActivity(MyToolsActivity.this);
                } else if (position == 1) {
                    Starter.startAccountManageActivity(MyToolsActivity.this);
                } else if (position == 2) {
                    Starter.startCompanyInfoActivity(MyToolsActivity.this);
                }
            }
        });

        accountAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position == 0) {

                } else if (position == 1) {
                    Starter.startJyAccountActivity(MyToolsActivity.this);
                } else if (position == 2) {
                    Starter.startJsAccountActivity(MyToolsActivity.this);
                }
            }
        });

        elseAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_tools;
    }

}