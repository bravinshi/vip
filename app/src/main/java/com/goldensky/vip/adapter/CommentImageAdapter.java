package com.goldensky.vip.adapter;

import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.databinding.ItemCommentImageBinding;
import com.goldensky.vip.databinding.ItemCommentStarBinding;
import com.luck.picture.lib.entity.LocalMedia;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class CommentImageAdapter extends BaseQuickAdapter<LocalMedia, BaseViewHolder> {

    public static final int TYPE_ADD = 1;
    public static final int TYPE_PICTURE = 2;
    private int maxNum = 6;

    public CommentImageAdapter(List<LocalMedia> data) {
        super(R.layout.item_comment_image, data);
    }


    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, LocalMedia localMedia) {
    }

    @Override
    public void onBindViewHolder(@NotNull BaseViewHolder holder, int position) {
        ItemCommentImageBinding binding = DataBindingUtil.bind(holder.itemView);
        if (getItemViewType(position) == TYPE_ADD) {
            binding.ivPic.setImageResource(R.mipmap.icon_upload_pic);
            binding.ivDelete.setVisibility(View.INVISIBLE);
        } else {
            binding.ivDelete.setVisibility(View.VISIBLE);
            LocalMedia media = getData().get(position);
            String path;
            if (media.isCompressed()) {
                //压缩
                path = media.getCompressPath();
            } else {
                //原图
                path = media.getPath();
            }
            Glide.with(getContext()).load(path).into(binding.ivPic);
        }
    }

    @Override
    public int getItemCount() {
        if (getData().size() < maxNum) {
            return getData().size() + 1;
        }
        return getData().size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getData().size()) {
            return TYPE_ADD;
        }
        return TYPE_PICTURE;
    }

}
