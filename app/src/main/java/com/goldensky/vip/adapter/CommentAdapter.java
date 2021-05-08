package com.goldensky.vip.adapter;

import android.util.Log;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.GoodsCommentItemBean;
import com.goldensky.vip.databinding.ItemCommentPicBinding;
import com.goldensky.vip.databinding.ItemCommentTextBinding;
import com.goldensky.vip.helper.ImageLoaderHelper;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.text.SimpleDateFormat;
import java.util.List;

public class CommentAdapter extends BaseMultiItemQuickAdapter<GoodsCommentItemBean, BaseViewHolder> {

    public static final int COMMENT_TYPE_TEXT = 0; //纯文本
    public static final int COMMENT_TYPE_PIC = 1; //有图片

    private CommentPicsAdapter mCommentPicsAdapter;

    public CommentAdapter(List<GoodsCommentItemBean> goodsCommentItemBeans) {
        super(goodsCommentItemBeans);
        addItemType(COMMENT_TYPE_TEXT, R.layout.item_comment_text);
        addItemType(COMMENT_TYPE_PIC, R.layout.item_comment_pic);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, GoodsCommentItemBean goodsCommentItemBean) {
        if (goodsCommentItemBean.getItemType() == COMMENT_TYPE_TEXT) {
            ItemCommentTextBinding binding = DataBindingUtil.bind(baseViewHolder.itemView);
//            ImageLoaderHelper.loadImage(binding.ivPic, goodsCommentItemBean.getUserPic());
            Glide.with(getContext()).load(goodsCommentItemBean.getUserPic()).apply(new RequestOptions().circleCrop()).into(binding.ivPic);
            if (goodsCommentItemBean.getCreationTime() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String timeStr = dateFormat.format(goodsCommentItemBean.getCreationTime());
                binding.tvTime.setText(timeStr);
            } else {
                binding.tvTime.setText("");
            }
            binding.setBean(goodsCommentItemBean);
        } else if (goodsCommentItemBean.getItemType() == COMMENT_TYPE_PIC) {
            ItemCommentPicBinding binding = DataBindingUtil.bind(baseViewHolder.itemView);
//            ImageLoaderHelper.loadImage(binding.ivPic, goodsCommentItemBean.getUserPic());
            Glide.with(getContext()).load(goodsCommentItemBean.getUserPic()).apply(new RequestOptions().circleCrop()).into(binding.ivPic);
            if (goodsCommentItemBean.getCreationTime() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String timeStr = dateFormat.format(goodsCommentItemBean.getCreationTime());
                binding.tvTime.setText(timeStr);
            } else {
                binding.tvTime.setText("");
            }
            binding.setBean(goodsCommentItemBean);
            mCommentPicsAdapter = new CommentPicsAdapter(goodsCommentItemBean.getBusCommodityCommentPics());
            FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext()) {
                @Override
                public boolean canScrollVertically() {
                    return false;
                }

                @Override
                public boolean canScrollHorizontally() {
                    return false;
                }
            };
            flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
            flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
            binding.rvPic.setLayoutManager(flexboxLayoutManager);
            binding.rvPic.setAdapter(mCommentPicsAdapter);
        }
    }
}
