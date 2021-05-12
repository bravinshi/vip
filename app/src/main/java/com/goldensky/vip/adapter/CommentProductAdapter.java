package com.goldensky.vip.adapter;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.CommentProductBean;
import com.goldensky.vip.databinding.ItemDpjProductBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CommentProductAdapter extends BaseQuickAdapter<CommentProductBean, BaseViewHolder> {

    public CommentProductAdapter(List<CommentProductBean> data) {
        super(R.layout.item_dpj_product, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, CommentProductBean commentProductBean) {
        ItemDpjProductBinding binding = DataBindingUtil.bind(baseViewHolder.itemView);
        Glide.with(getContext()).load(commentProductBean.getInventorypic()).into(binding.ivProduct);
        String inventory = commentProductBean.getInventory();
        if (!StringUtils.isEmpty(inventory)) {
            inventory = inventory.replace("{", "")
                    .replace("}", "")
                    .replace("\"", "");
            binding.tvGg.setText(inventory);
        }
        binding.setBean(commentProductBean);
    }
}
