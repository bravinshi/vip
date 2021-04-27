package com.goldensky.vip.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldensky.vip.R;

import java.util.List;

public class JhManageAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {
    public JhManageAdapter(@Nullable List<Integer> data) {
        super(R.layout.item_jinhuo_manage,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        Glide.with(mContext).load(item).into((ImageView) helper.getView(R.id.item_iv));
    }
}
