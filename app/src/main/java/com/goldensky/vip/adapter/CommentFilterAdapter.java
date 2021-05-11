package com.goldensky.vip.adapter;

import android.graphics.Color;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.CommentFilterBean;
import com.goldensky.vip.databinding.ItemCommentFilterBinding;
import com.goldensky.vip.databinding.ItemHomeMsgBinding;

import java.util.List;

import static com.goldensky.vip.R.drawable.shape_comment_filter_enable;
import static com.goldensky.vip.R.drawable.shape_comment_filter_unenable;

public class CommentFilterAdapter extends BaseQuickAdapter<CommentFilterBean, BaseViewHolder> {

    public CommentFilterAdapter(List<CommentFilterBean> data) {
        super(R.layout.item_comment_filter, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentFilterBean item) {
        ItemCommentFilterBinding binding = DataBindingUtil.bind(helper.itemView);
        if (item.isSelected()) {
           binding.tvBtn.setTextColor(Color.parseColor("#EA483F"));
           binding.tvBtn.setBackgroundResource(shape_comment_filter_enable);
        } else {
            binding.tvBtn.setTextColor(Color.parseColor("#333333"));
            binding.tvBtn.setBackgroundResource(shape_comment_filter_unenable);
        }
        binding.setBean(item);
    }
}
