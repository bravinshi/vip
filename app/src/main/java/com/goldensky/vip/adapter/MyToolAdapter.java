package com.goldensky.vip.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.MineToolBean;
import com.goldensky.vip.databinding.ItemToolMineBinding;
import com.goldensky.vip.databinding.ItemToolMineBindingImpl;

import java.util.List;

public class MyToolAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    public MyToolAdapter(@Nullable List<Integer> data) {
        super(R.layout.item_my_tools,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        Glide.with(mContext).load(item).into((ImageView) helper.getView(R.id.iv_item_my_tools));
    }
}
