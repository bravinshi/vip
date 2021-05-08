package com.goldensky.vip.activity.mine.tools.adress;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.UserAddressAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.AreaListBean;
import com.goldensky.vip.bean.DeleteUserAddressReqBean;
import com.goldensky.vip.bean.EditAddressBean;
import com.goldensky.vip.bean.GetUserAddressReqBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.databinding.ActivityMyAddressBinding;
import com.goldensky.vip.enumerate.StatusTypeEnum;
import com.goldensky.vip.enumerate.UserAddressChangeEnum;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.helper.UserAddressHelper;
import com.goldensky.vip.viewmodel.account.AddressViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MyAddressActivity extends BaseActivity<ActivityMyAddressBinding, AddressViewModel> implements View.OnClickListener {
    private List<UserAddressBean> list=new ArrayList<>();
    private UserAddressAdapter adapter;
    private AlertDialog dialog;
    private DeleteUserAddressReqBean bean;
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarMyDress.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        EventBus.getDefault().register(this);
        mBinding.setListener(this);
        mBinding.rvMyAddress.setLayoutManager(new LinearLayoutManager(this));
        adapter=new UserAddressAdapter(list);
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.edit_item_myaddress:
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("addressbean",list.get(position));
                        Starter.startEditAddressActivity(MyAddressActivity.this,bundle);
                        break;
                    case R.id.delete_item_myaddress:
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyAddressActivity.this);
                        View popView = LayoutInflater.from(MyAddressActivity.this).inflate(R.layout.pop_delete_user_address, null);
                        popView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                switch (v.getId()){
                                    case R.id.btn_confirm_delete_address:
                                        bean = new DeleteUserAddressReqBean(list.get(position).getUseraddressid());
                                        mViewModel.deleteUserAddress(bean);
                                        break;
                                    case R.id.btn_cancel_delete_address:
                                        dialog.dismiss();
                                        break;
                                }
                            }
                        });
                        builder.setView(popView);
                        dialog = builder.create();
                        dialog.show();
                        break;
                }
            }
        });
        mBinding.rvMyAddress.setAdapter(adapter);
        String userId = AccountHelper.getUserId();
        GetUserAddressReqBean bean = new GetUserAddressReqBean(userId);
        if(UserAddressHelper.getInstance().isUserAddressLoad()){
           list=UserAddressHelper.getInstance().getUserAddressList();
           adapter.notifyDataSetChanged();
        }else {
            mViewModel.getUserAddress(bean);
        }
        if(!UserAddressHelper.getInstance().isAreaLoad()){
            mViewModel.getAreaList();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshAddressList(String action){
        if(action.equals(UserAddressChangeEnum.USERADDRESSCHANGE.value)){
            adapter.notifyDataSetChanged();
        }
    }
    @Override
    public void observe() {
        mViewModel.userAddressLive.observe(this, new Observer<List<UserAddressBean>>() {
            @Override
            public void onChanged(List<UserAddressBean> userAddressBeans) {
                UserAddressHelper.getInstance().setUserAddressList(userAddressBeans);
                list=UserAddressHelper.getInstance().getUserAddressList();
                adapter.notifyDataSetChanged();
            }
        });
        mViewModel.areaListLive.observe(this, new Observer<List<AreaListBean>>() {
            @Override
            public void onChanged(List<AreaListBean> areaListBean) {
                UserAddressHelper.getInstance().loadAddressList(areaListBean);
            }
        });
        mViewModel.deleteAddressLive.observe(this, new Observer<EditAddressBean>() {
            @Override
            public void onChanged(EditAddressBean editAddressBean) {
                Integer status = editAddressBean.getStatus();
                if(status== StatusTypeEnum.SUCCSESS.value){
                    UserAddressHelper.getInstance().deleteUserAddress(bean);
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_address;
    }

    @Override
    public void onClick(View v) {
        Starter.startNewAddressActivity(this,null);
    }
}