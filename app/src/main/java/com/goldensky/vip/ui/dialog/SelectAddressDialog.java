package com.goldensky.vip.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.framework.ui.dialog.BottomDialog;
import com.goldensky.framework.util.ToastUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.constant.BusinessConstant;
import com.goldensky.vip.databinding.DialogSelectAddressBinding;
import com.goldensky.vip.databinding.ItemSelectAddressBinding;
import com.goldensky.vip.event.RetrieveAddressEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/8 10:02
 * 包名： com.goldensky.vip.ui.dialog
 * 类说明：
 */
public class SelectAddressDialog extends BottomDialog {
    private SelectAddressAdapter addressAdapter = new SelectAddressAdapter(R.layout.item_select_address);

    private DialogSelectAddressBinding mBinding;

    public void setData(List<UserAddressBean> userAddressBeans) {
        addressAdapter.selectedPos = 0;
        for (int i = 0; i < userAddressBeans.size(); i++) {
            UserAddressBean userAddressBean = userAddressBeans.get(i);
            if (BusinessConstant.VALUE_DEFAULT_ADDRESS.equals(userAddressBean.getUseraddressdefault())) {
                addressAdapter.selectedPos = i;
                break;
            }
        }

        addressAdapter.setNewInstance(userAddressBeans);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_select_address, container, false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBinding.btnClose.setOnClickListener(v -> dismissAllowingStateLoss());

        mBinding.btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO
                dismissAllowingStateLoss();
            }
        });

        mBinding.recyclerView.setAdapter(addressAdapter);
        addressAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                addressAdapter.selectedPos = position;
                addressAdapter.notifyDataSetChanged();
                // 发送事件修改地址
                RetrieveAddressEvent event = new RetrieveAddressEvent();
                TextView textView = view.findViewById(R.id.tv_address);
                event.setAddress(textView.getText().toString());
                EventBus.getDefault().post(event);

                dismissAllowingStateLoss();
            }
        });
    }

    private static class SelectAddressAdapter extends BaseQuickAdapter<UserAddressBean, BaseViewHolder> {

        private int selectedPos = 0;

        public SelectAddressAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, UserAddressBean userAddressBean) {
            ItemSelectAddressBinding layoutBinding = DataBindingUtil.bind(baseViewHolder.itemView);
            layoutBinding.setBean(userAddressBean);

            layoutBinding.getRoot().setSelected(baseViewHolder.getAdapterPosition() == selectedPos);
        }
    }
}
