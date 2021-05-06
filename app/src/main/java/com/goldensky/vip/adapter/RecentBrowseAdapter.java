package com.goldensky.vip.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.RecentBrowseBean;

import java.util.List;

public class RecentBrowseAdapter extends BaseQuickAdapter<RecentBrowseBean, BaseViewHolder> {
    private int data=31;
    public RecentBrowseAdapter(@Nullable List<RecentBrowseBean> data) {
        super(R.layout.item_circle_focus,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RecentBrowseBean item) {
        if(item.getPosition()%3==0){
            helper.setVisible(R.id.data_item_circle_focus,true);
            helper.setText(R.id.data_item_circle_focus,"1月"+(data-item.getPosition()/3)+"日");
        }else {
            TextView dataView = helper.getView(R.id.data_item_circle_focus);
            dataView.setVisibility(View.GONE);
        }
        Glide.with(getContext()).load(item.getImage()).into((ImageView) helper.getView(R.id.iv_item_circle_focus));
    }
}
