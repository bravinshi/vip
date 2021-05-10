package com.goldensky.vip.adapter;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.databinding.ItemCommentImageBinding;
import com.goldensky.vip.databinding.ItemCommentStarBinding;

import java.util.List;

public class CommentImageAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public CommentImageAdapter(List<Integer> data) {
        super(R.layout.item_comment_image, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        ItemCommentImageBinding binding = DataBindingUtil.bind(baseViewHolder.itemView);
        Glide.with(getContext()).load(integer).into(binding.ivPic);
    }
}
