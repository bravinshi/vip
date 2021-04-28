package com.goldensky.vip.fragment.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.MineToolAdapter;
import com.goldensky.vip.base.fragment.BaseFragment;
import com.goldensky.vip.base.fragment.LazyLoadFragment;
import com.goldensky.vip.bean.MineToolBean;
import com.goldensky.vip.databinding.FragmentMineBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;


public class MineFragment extends LazyLoadFragment<FragmentMineBinding, PublicViewModel> implements View.OnClickListener {
    private List<MineToolBean> orderList=new ArrayList<>();
    private List<MineToolBean> toolList=new ArrayList<>();
    private MineToolAdapter orderAdapter;
    private MineToolAdapter toolAdapter;
    private boolean flag=true;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(flag){
            orderList.add(new MineToolBean(R.mipmap.my_icon_daifukuan,"待付款"));
            orderList.add(new MineToolBean(R.mipmap.my_icon_daishouhuo,"待收货"));
            orderList.add(new MineToolBean(R.mipmap.my_icon_daipingjia,"待评价"));
            orderList.add(new MineToolBean(R.mipmap.my_icon_shouhou,"退款/售后"));
            orderList.add(new MineToolBean(R.mipmap.my_icon_dingdan,"我的订单"));
//            toolList.add(new MineToolBean(R.mipmap.my_icon_youhuijuan,"优惠券"));
            toolList.add(new MineToolBean(R.mipmap.my_icon_dizhi,"我的地址"));
            toolList.add(new MineToolBean(R.mipmap.my_icon_fenxiang,"好友分享"));
//            toolList.add(new MineToolBean(R.mipmap.my_icon_registered2,"邀请企业"));
            mBinding.rvOrderMine.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.VERTICAL){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            mBinding.rvToolMine.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL){
                @Override
                public boolean canScrollVertically() {
                    return false;
                }
            });
            orderAdapter=new MineToolAdapter(orderList);
            toolAdapter=new MineToolAdapter(toolList);
            mBinding.rvOrderMine.setAdapter(orderAdapter);
            mBinding.rvToolMine.setAdapter(toolAdapter);
            mBinding.setListener(this);
            orderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                    switch (position){
                        case 0:
                            startOrderList(1);
                            break;
                        case 1:
                            startOrderList(2);
                            break;
                        case 2:
                            startOrderList(4);
                            break;
                        case 3:
////                            startOrderList(4);
//                            break;
//                        case 4:
                            startOrderList(0);
                            break;
                    }

                }
            });
            toolAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (position){
//                        case 0:
//                            Starter.startCouponActivity(getContext(),null);
//                            break;
//                        case 1:
//                            Starter.startMyAddressActivity(getContext(),null);
//                            break;
//                        case 2:
//                            Starter.startShareToFriendActivity(getContext(),null);
//                            break;
//                        case 3:
//                            Starter.startInviteCompanyActivity(getContext(),null);
//                            break;
                        case 0:
                            Starter.startMyAddressActivity(getContext(),null);
                            break;
                        case 1:
                            Starter.startShareToFriendActivity(getContext(),null);
                            break;

                    }
                }
            });

            orderAdapter.notifyDataSetChanged();
            toolAdapter.notifyDataSetChanged();
            flag=false;
        }

    }

    private void startOrderList(Integer orderType) {
        Bundle bundle = new Bundle();
        bundle.putInt("orderType",orderType);
        Starter.startOrderListActivity(getContext(),bundle);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_settings_mine:
                Starter.startSettingsActivity(getContext(),null);
                break;
//            case R.id.goods_focus_mine:
//                Starter.startGoodsFocusActivity(getContext(),null);
//                break;
//            case R.id.circle_focus_mine:
//                Starter.startCircleFocusActivity(getContext(),null);
//                break;
//            case R.id.recent_browse_mine:
//                Starter.startRecentBrowseActivity(getContext(),null);
//                break;
        }
    }

    @Override
    public void onLazyLoad() {

    }
}