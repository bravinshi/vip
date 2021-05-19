package com.goldensky.vip.adapter;

import android.text.Editable;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.framework.util.ToastUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.base.ui.view.PurchaseNumView;
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
        mBinding.tvPrice.setText("¥"+String.valueOf(confirmOrderItemBean.getPrice()));
        mBinding.tvSpecification.setText(confirmOrderItemBean.getSpecification());
        mBinding.pnv.setTag(confirmOrderItemBean);
        if (confirmOrderItemBean.getPurchaseNum() != 0) {
            mBinding.pnv.setPurchaseNum(confirmOrderItemBean.getPurchaseNum());
        }
        mBinding.pnv.setOnNumChangedListener((fromBtn, oldNum, newNum) -> {
            ConfirmOrderItemBean item = (ConfirmOrderItemBean) mBinding.pnv.getTag();
            PurchaseNumChangeEvent purchaseNumChangeEvent = new PurchaseNumChangeEvent();
            purchaseNumChangeEvent.setNewNum(newNum);
            item.setPurchaseNum(newNum);
            purchaseNumChangeEvent.setNotify(fromBtn);
            purchaseNumChangeEvent.setConfirmOrderItemBean(item);
            EventBus.getDefault().post(purchaseNumChangeEvent);

        });
    }
}
