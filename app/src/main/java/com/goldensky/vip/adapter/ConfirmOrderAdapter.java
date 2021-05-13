package com.goldensky.vip.adapter;

import android.text.Editable;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.ConfirmOrderItemBean;
import com.goldensky.vip.databinding.ItemConfirmOrderBinding;
import com.goldensky.vip.event.PurchaseNumChangeEvent;
import com.goldensky.vip.helper.ImageLoaderHelper;
import com.goldensky.vip.listener.DoNothingTextWatcher;

import org.greenrobot.eventbus.EventBus;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/11 16:17
 * 包名： com.goldensky.vip.adapter
 * 类说明：
 */
public class ConfirmOrderAdapter extends BaseQuickAdapter<ConfirmOrderItemBean, BaseViewHolder> {
    public ConfirmOrderAdapter() {
        super(R.layout.item_confirm_order);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ConfirmOrderItemBean confirmOrderItemBean) {
        ItemConfirmOrderBinding mBinding = DataBindingUtil.bind(baseViewHolder.itemView);
        // pic
        ImageLoaderHelper.loadImage(mBinding.ivPic, confirmOrderItemBean.getPic());
        //
        mBinding.tvDesc.setText(confirmOrderItemBean.getCommodityName());
        mBinding.tvPrice.setText(String.valueOf(confirmOrderItemBean.getPrice()));
        mBinding.tvSpecification.setText(confirmOrderItemBean.getSpecification());
        mBinding.etCount.setTag(confirmOrderItemBean);
        mBinding.etCount.setText(String.valueOf(confirmOrderItemBean.getPurchaseNum()));
        mBinding.btnIncrease.setOnClickListener(v -> {
            String count = mBinding.etCount.getText().toString().trim();
            if (StringUtils.isTrimEmpty(count)) {
                return;
            }

            try {
                int temp = Integer.parseInt(count);
                temp++;
                mBinding.etCount.setText(String.valueOf(temp));
                ConfirmOrderItemBean item = (ConfirmOrderItemBean) mBinding.etCount.getTag();
                PurchaseNumChangeEvent purchaseNumChangeEvent = new PurchaseNumChangeEvent();
                purchaseNumChangeEvent.setNewNum(temp);
                item.setPurchaseNum(temp);
                purchaseNumChangeEvent.setNotify(true);
                purchaseNumChangeEvent.setConfirmOrderItemBean(item);
                EventBus.getDefault().post(purchaseNumChangeEvent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mBinding.btnDecrease.setOnClickListener(v -> {
            String count = mBinding.etCount.getText().toString().trim();
            if (StringUtils.isTrimEmpty(count)) {
                return;
            }

            try {
                int temp = Integer.parseInt(count);
                temp--;
                if (temp < 0) {
                    temp = 0;
                }
                ConfirmOrderItemBean item = (ConfirmOrderItemBean) mBinding.etCount.getTag();
                PurchaseNumChangeEvent purchaseNumChangeEvent = new PurchaseNumChangeEvent();
                purchaseNumChangeEvent.setNewNum(temp);
                purchaseNumChangeEvent.setNotify(true);
                purchaseNumChangeEvent.setConfirmOrderItemBean(item);
                item.setPurchaseNum(temp);
                EventBus.getDefault().post(purchaseNumChangeEvent);
                mBinding.etCount.setText(String.valueOf(temp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mBinding.etCount.addTextChangedListener(new DoNothingTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                String temp = s.toString().trim();
                Integer count = 0;
                int length = temp.length();
                if (length > 0) {
                    count = Integer.valueOf(temp);
                    if (length > 1 && temp.startsWith("0")){
                        temp = temp.substring(1);
                        mBinding.etCount.setText(temp);
                        mBinding.etCount.setSelection(1);
                        return;
                    }
                }
                try {
                    if (StringUtils.isTrimEmpty(temp)
                    || (length > 1 && count == 0)) {
                        mBinding.etCount.setText("0");
                        mBinding.etCount.setSelection(1);
                    }

                    ConfirmOrderItemBean item = (ConfirmOrderItemBean) mBinding.etCount.getTag();
                    PurchaseNumChangeEvent purchaseNumChangeEvent = new PurchaseNumChangeEvent();
                    purchaseNumChangeEvent.setNewNum(count);
                    purchaseNumChangeEvent.setConfirmOrderItemBean(item);
                    purchaseNumChangeEvent.setNotify(false);
                    item.setPurchaseNum(count);
                    EventBus.getDefault().post(purchaseNumChangeEvent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
