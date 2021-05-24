package com.goldensky.vip.base.ui.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.framework.ui.dialog.BottomDialog;
import com.goldensky.vip.R;
import com.goldensky.vip.databinding.DialogGoodsAttributeBinding;
import com.goldensky.vip.databinding.ItemGoodsAttributeBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/22 9:28
 * 包名： com.goldensky.vip.base.ui.dialog
 * 类说明：
 */
public class GoodsAttributeDialog extends BottomDialog {

    private final GoodsAttributeAdapter goodsAttributeAdapter = new GoodsAttributeAdapter();

    public void setData(List<DataModel> dataModels) {
        goodsAttributeAdapter.setNewInstance(dataModels);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogGoodsAttributeBinding mBinding = DataBindingUtil
                .inflate(inflater, R.layout.dialog_goods_attribute, container, false);
        mBinding.rv.setAdapter(goodsAttributeAdapter);
        return mBinding.getRoot();
    }

    private static class GoodsAttributeAdapter extends BaseQuickAdapter<DataModel, BaseViewHolder> {

        public GoodsAttributeAdapter() {
            super(R.layout.item_goods_attribute);
        }

        @Override
        protected void convert(@NotNull BaseViewHolder baseViewHolder, DataModel dataModel) {
            ItemGoodsAttributeBinding itemGoodsAttributeBinding = DataBindingUtil.bind(baseViewHolder.itemView);
            itemGoodsAttributeBinding.tvAttribute.setText(dataModel.getAttribute());
            itemGoodsAttributeBinding.tvContent.setText(dataModel.getContent());

            itemGoodsAttributeBinding.executePendingBindings();
        }
    }

    public static class DataModel {
        private String attribute;
        private String content;

        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
