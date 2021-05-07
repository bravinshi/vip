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
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.databinding.ActivityMyAddressBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.account.AddressViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyAddressActivity extends BaseActivity<ActivityMyAddressBinding, AddressViewModel> implements View.OnClickListener {
    private List<UserAddressBean> list=new ArrayList<>();
    private UserAddressAdapter adapter;
    private AlertDialog dialog;
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarMyDress.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                                        mViewModel.deleteUserAddress(list.get(position).getUseraddressid());
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
        mViewModel.getUserAddress(userId);
    }

    @Override
    public void observe() {
        mViewModel.userAddressLive.observe(this, new Observer<List<UserAddressBean>>() {
            @Override
            public void onChanged(List<UserAddressBean> userAddressBeans) {
                list.clear();
                list.addAll(userAddressBeans);
                adapter.notifyDataSetChanged();
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