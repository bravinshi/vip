package com.goldensky.vip.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.chad.library.adapter.base.BaseViewHolder;
import com.goldensky.framework.util.ImageUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.MineToolBean;
import com.goldensky.vip.databinding.ItemToolMineBinding;
import com.goldensky.vip.databinding.ItemToolMineBindingImpl;

import java.util.List;

public class MineToolAdapter extends BaseQuickAdapter<MineToolBean, BaseViewHolder> {
    public MineToolAdapter(@Nullable List<MineToolBean> data) {
        super(R.layout.item_tool_mine,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MineToolBean item) {
        ItemToolMineBinding bind = DataBindingUtil.<ItemToolMineBindingImpl>bind(helper.itemView);
        bind.setBean(item);
        bind.notifyChange();
        Glide.with(mContext).load(item.getSign()).into(bind.ivSignItemMine);
    }
}
