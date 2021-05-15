package com.goldensky.vip.adapter;

import androidx.databinding.ViewDataBinding;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.LogisticsBean;
import com.goldensky.vip.databinding.ItemLogisticBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LogisticsAdapter extends BaseQuickAdapter<LogisticsBean.DataDTO, BaseDataBindingHolder> {
    public LogisticsAdapter( @Nullable List<LogisticsBean.DataDTO> data) {
        super(R.layout.item_logistic, data);
    }

    @Override
    protected void convert(@NotNull BaseDataBindingHolder baseDataBindingHolder, LogisticsBean.DataDTO dataDTO) {
        ItemLogisticBinding dataBinding = (ItemLogisticBinding) baseDataBindingHolder.getDataBinding();
        dataBinding.setBean(dataDTO);
    }
}
