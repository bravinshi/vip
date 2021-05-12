package com.goldensky.vip.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.ConfirmOrderItemBean;

import java.util.List;

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

    }
}
