package com.goldensky.vip.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.databinding.ItemCommentStarBinding;

import java.util.List;

public class CommentStartAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public CommentStartAdapter(List<Integer> data) {
        super(R.layout.item_comment_star, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Integer integer) {
        ItemCommentStarBinding binding = DataBindingUtil.bind(baseViewHolder.itemView);
        Glide.with(getContext()).load(integer).into(binding.ivStar);
    }
}
