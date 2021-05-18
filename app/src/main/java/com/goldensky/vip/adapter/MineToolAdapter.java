package com.goldensky.vip.adapter;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.MineToolBean;
import com.goldensky.vip.databinding.ItemToolMineBinding;
import com.goldensky.vip.databinding.ItemToolMineBindingImpl;

import java.util.ArrayList;
import java.util.List;

public class MineToolAdapter extends BaseQuickAdapter<MineToolBean, BaseViewHolder> {
    private List<Integer> countList=new ArrayList<>();
    public MineToolAdapter(@Nullable List<MineToolBean> data) {
        super(R.layout.item_tool_mine,data);
    }

    public void setCountList(List<Integer> countList) {
        this.countList = countList;
    }

    @Override
    protected void convert(BaseViewHolder helper, MineToolBean item) {
        ItemToolMineBinding bind = DataBindingUtil.<ItemToolMineBindingImpl>bind(helper.itemView);
        bind.setBean(item);
        bind.notifyChange();
        Glide.with(getContext()).load(item.getSign()).into(bind.ivSignItemMine);
        if(countList.size()>0){
            Integer count = countList.get(helper.getPosition());
            if(count>0){
                bind.orderCountItemToolMine.setVisibility(View.VISIBLE);
                if(count>99){
                    bind.orderCountItemToolMine.setText("99+");
                }else {
                    bind.orderCountItemToolMine.setText(count+"");
                }
            }else {
                bind.orderCountItemToolMine.setVisibility(View.GONE);
            }
        }else {
            bind.orderCountItemToolMine.setVisibility(View.GONE);
        }
    }
}
