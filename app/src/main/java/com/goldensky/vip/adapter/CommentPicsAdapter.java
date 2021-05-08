package com.goldensky.vip.adapter;

import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.GoodsCommentItemBean;
import com.goldensky.vip.databinding.ItemCommentContentPicBinding;
import com.goldensky.vip.helper.ImageLoaderHelper;

import java.util.List;

public class CommentPicsAdapter extends BaseQuickAdapter<GoodsCommentItemBean.CommentPicBean, BaseViewHolder> {

    public CommentPicsAdapter(List<GoodsCommentItemBean.CommentPicBean> data) {
        super(R.layout.item_comment_content_pic, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GoodsCommentItemBean.CommentPicBean commentPicBean) {
        ItemCommentContentPicBinding binding = DataBindingUtil.bind(baseViewHolder.itemView);
        ImageLoaderHelper.loadImage(binding.ivComment, commentPicBean.getCommentPic());
    }
}
