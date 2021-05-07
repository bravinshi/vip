package com.goldensky.vip.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.databinding.ItemMyAddressBinding;

import java.util.List;

public class UserAddressAdapter extends BaseQuickAdapter<UserAddressBean, BaseViewHolder> {
    public UserAddressAdapter(@Nullable List<UserAddressBean> data) {
        super(R.layout.item_my_address,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, UserAddressBean item) {
        ItemMyAddressBinding bind = DataBindingUtil.<ItemMyAddressBinding>bind(helper.itemView);
        bind.setBean(item);
        if(item.getUseraddressdefault()==1){
            bind.isDefaultItemMyaddress.setVisibility(View.VISIBLE);
        }else{
            bind.isDefaultItemMyaddress.setVisibility(View.GONE);
        }
    }
}
