package com.goldensky.vip.activity.mine.tools.adress;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.UserAddressAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.DeleteUserAddressReqBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.databinding.ActivityMyAddressBinding;
import com.goldensky.vip.event.AddAddressEvent;
import com.goldensky.vip.event.RefreshAddressEvent;
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
    private PopupWindow mPopupWindow;
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
        adapter.addChildClickViewIds(new int[]{R.id.edit_item_myaddress,R.id.delete_item_myaddress});
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

                        View popView = LayoutInflater.from(MyAddressActivity.this).inflate(R.layout.pop_delete_user_address, null);
                        TextView confirm = popView.findViewById(R.id.btn_confirm_delete_address);
                        TextView cancel = popView.findViewById(R.id.btn_cancel_delete_address);
                        confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                bean = new DeleteUserAddressReqBean(list.get(position).getUseraddressid());
                                mViewModel.deleteUserAddress(bean);
                                mPopupWindow.dismiss();
                            }
                        });
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mPopupWindow.dismiss();
                            }
                        });

                        mPopupWindow = new PopupWindow(popView, -1, -2);
                        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mPopupWindow.setOutsideTouchable(true);
                        mPopupWindow.setFocusable(true);
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 0.5f;
                        getWindow().setAttributes(lp);
                        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                            @Override
                            public void onDismiss() {
                                WindowManager.LayoutParams lp = getWindow().getAttributes();
                                lp.alpha = 1f;
                                getWindow().setAttributes(lp);
                            }
                        });
                        mPopupWindow.setAnimationStyle(R.style.main_menu_photo_anim);
                        mPopupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
                        mPopupWindow.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
                        mPopupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

                        break;
                }
            }
        });
        mBinding.rvMyAddress.setAdapter(adapter);
        mViewModel.getUserAddress(AccountHelper.getUserId());
        refreshAddressList();

    }

    private void refreshAddressList() {
        list.clear();
        list .addAll(UserAddressHelper.getInstance().getUserAddressList());
        adapter.notifyDataSetChanged();
        if(list.size()==0){
            mBinding.rvMyAddress.setVisibility(View.GONE);
            mBinding.includeAddress.clEmptyAddress.setVisibility(View.VISIBLE);
        }else {
            mBinding.rvMyAddress.setVisibility(View.VISIBLE);
            mBinding.includeAddress.clEmptyAddress.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onChangeAddress(AddAddressEvent addAddressEvent){
        if (addAddressEvent.getSuccess()) {
            mViewModel.getUserAddress(AccountHelper.getUserId());
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshAddress(RefreshAddressEvent refreshAddressEvent){
        if (refreshAddressEvent.getSuccess()) {
            refreshAddressList();
        }

    }
    @Override
    public void observe() {
        mViewModel.deleteAddressLive.observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object editAddressBean) {
                mViewModel.getUserAddress(AccountHelper.getUserId());
            }
        });
        mViewModel.userAddressLive.observe(this, new Observer<List<UserAddressBean>>() {
            @Override
            public void onChanged(List<UserAddressBean> list) {
                UserAddressHelper.getInstance().setUserAddressList(list);
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