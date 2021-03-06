package com.goldensky.vip.fragment.main;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.MineToolAdapter;
import com.goldensky.vip.base.fragment.LazyLoadFragment;
import com.goldensky.vip.bean.LoginResponseBean;
import com.goldensky.vip.bean.MineToolBean;
import com.goldensky.vip.bean.OrderCountBean;
import com.goldensky.vip.databinding.FragmentMineBinding;
import com.goldensky.vip.enumerate.DefaultUrlEnum;
import com.goldensky.vip.event.VipUserChangeEvent;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.PublicViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;


public class MineFragment extends LazyLoadFragment<FragmentMineBinding, PublicViewModel> implements View.OnClickListener {
    private List<MineToolBean> orderList=new ArrayList<>();
    private List<MineToolBean> toolList=new ArrayList<>();
    private MineToolAdapter orderAdapter;
    private MineToolAdapter toolAdapter;
    private List<Integer> orderCounts=new ArrayList<>();
    private boolean flag=true;
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.getVipUser(AccountHelper.getUserId());
        mViewModel.getOrderCount(AccountHelper.getUserId());
        if(flag){
            orderList.add(new MineToolBean(R.mipmap.my_icon_daifukuan,"待付款"));
            orderList.add(new MineToolBean(R.mipmap.my_icon_daishouhuo,"待收货"));
            orderList.add(new MineToolBean(R.mipmap.my_icon_daipingjia,"已完成"));
//            orderList.add(new MineToolBean(R.mipmap.my_icon_shouhou,"退款/售后"));
            orderList.add(new MineToolBean(R.mipmap.my_icon_dingdan,"我的订单"));
//            toolList.add(new MineToolBean(R.mipmap.my_icon_youhuijuan,"优惠券"));
            toolList.add(new MineToolBean(R.mipmap.my_icon_dizhi,"我的地址"));
            toolList.add(new MineToolBean(R.mipmap.my_icon_fenxiang,"好友分享"));
//            toolList.add(new MineToolBean(R.mipmap.my_icon_registered2,"邀请企业"));
            mBinding.rvOrderMine.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL){
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
            toolAdapter=new MineToolAdapter(toolList);
            orderAdapter=new MineToolAdapter(orderList);
            mBinding.rvOrderMine.setAdapter(orderAdapter);
            mBinding.rvToolMine.setAdapter(toolAdapter);
            mBinding.setListener(this);

            orderAdapter.setOnItemClickListener(new OnItemClickListener() {
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
                            startOrderList(3);
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
            toolAdapter.setOnItemClickListener(new OnItemClickListener() {
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onVipUserChanged(VipUserChangeEvent event){
        if(event.getSuccess()){
            setMSG();
        }
    }
    private void startOrderList(Integer orderType) {
        Bundle bundle = new Bundle();
        bundle.putInt("orderType",orderType);
        Starter.startOrderListActivity(getContext(),bundle);
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mViewModel.orderCountLive.observe(this, new Observer<OrderCountBean>() {
            @Override
            public void onChanged(OrderCountBean orderCountBean) {
                orderCounts.clear();
                orderCounts.add(orderCountBean.getToBePay());
                orderCounts.add(orderCountBean.getReceived());
                orderCounts.add(orderCountBean.getComplete());
                orderCounts.add(orderCountBean.getTotal());
                orderAdapter.setCountList(orderCounts);
                orderAdapter.notifyDataSetChanged();
            }
        });
        mViewModel.vipUserLive.observe(this, new Observer<LoginResponseBean.VipUser>() {
            @Override
            public void onChanged(LoginResponseBean.VipUser vipUser) {
                if(vipUser!= null){
                    if(AccountHelper.getUserNick()!=null){
                        if(!AccountHelper.getUserNick().equals(vipUser.getUserNick())){
                            AccountHelper.setNick(vipUser.getUserNick());
                        }
                    }else {
                        AccountHelper.setNick(vipUser.getUserNick());
                    }
                    if(AccountHelper.getUserPic()!=null){
                        if(!AccountHelper.getUserPic().equals(vipUser.getUserpic())){
                            AccountHelper.setUserPic(vipUser.getUserpic());
                        }

                    }else {
                        AccountHelper.setUserPic(vipUser.getUserpic());
                    }
                    setMSG();
                }
            }
        });
        setMSG();
    }

    private void setMSG() {
        if(AccountHelper.getUserNick()!=null){
            mBinding.tvNickMine.setText(AccountHelper.getUserNick());
        }else {
            mBinding.tvNickMine.setText(AccountHelper.getUserMobile());
        }
        if(AccountHelper.getUserPic()!=null&&!AccountHelper.getUserPic().equals("")){
            Glide.with(getContext()).load(AccountHelper.getUserPic()).apply(new RequestOptions().circleCrop()).into(mBinding.ivHeadMine);
        }else {
            Glide.with(getContext()).load(DefaultUrlEnum.DEFAULTHEADPIC.value).apply(new RequestOptions().circleCrop()).into(mBinding.ivHeadMine);
        }
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