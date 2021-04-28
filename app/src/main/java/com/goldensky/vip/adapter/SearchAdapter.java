package com.goldensky.vip.adapter;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.MineToolBean;
import com.goldensky.vip.bean.SearchItemBean;
import com.goldensky.vip.databinding.ItemSearchBinding;

import com.goldensky.vip.helper.ImageLoaderHelper;

import java.util.List;

public class SearchAdapter extends BaseQuickAdapter<SearchItemBean, BaseViewHolder> {
    public SearchAdapter(@Nullable List<SearchItemBean> data) {
        super(R.layout.item_search,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchItemBean item) {
        ItemSearchBinding bind = DataBindingUtil.bind(helper.itemView);
        bind.setBean(item);
        ImageLoaderHelper.loadImage(bind.iv, item.getPic());
    }
}
