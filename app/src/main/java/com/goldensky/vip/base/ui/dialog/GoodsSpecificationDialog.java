package com.goldensky.vip.base.ui.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.framework.ui.dialog.BottomDialog;
import com.goldensky.framework.util.CollectionUtils;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.base.ui.view.SpecificationItemView;
import com.goldensky.vip.bean.InventoryBean;
import com.goldensky.vip.databinding.DialogGoodSpecificationBinding;
import com.goldensky.vip.databinding.DialogSelectAddressBinding;
import com.goldensky.vip.databinding.ItemSpecificationBinding;
import com.goldensky.vip.databinding.ItemSpecificationValueBinding;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/8 16:03
 * 包名： com.goldensky.vip.ui.dialog
 * 类说明：
 */
public class GoodsSpecificationDialog extends BottomDialog {

    private final FirstAdapter firstAdapter = new FirstAdapter();

    private DialogGoodSpecificationBinding mBinding;
    private int joinVis = View.VISIBLE;
    private int buyVis = View.VISIBLE;

    public void  setData(List<InventoryBean> inventoryBeans) {
        if (CollectionUtils.nullOrEmpty(inventoryBeans)) {
            return;
        }
        // 转换数据结构
        Gson gson = new Gson();

        Map<String, First> firstTempMap = new HashMap<>();
        First tempFirst = null;

        for (InventoryBean inventory : inventoryBeans) {
            String inventoryStr = inventory.getInventory();
            if (StringUtils.isTrimEmpty(inventoryStr)) {
                continue;
            }
            // 转成map结构
            JsonObject jsonObject = gson.fromJson(inventoryStr, JsonObject.class);
            Set<String> keySet = jsonObject.keySet();
            for (String key : keySet) {
                // 检查firsts是否存在对应的first信息，没有则创建
                tempFirst = firstTempMap.get(key);
                if (tempFirst == null) {
                    tempFirst = new First();
                    tempFirst.name = key;
                    firstTempMap.put(key, tempFirst);// 创建的first存在map中
                }
                // 检查first中是否有对应的second信息，不同的规格可能会有相同的key信息，但不是所有的key都相同
                // 如果存在second信息，则把这一规格信息保存到second中,
                List<Second> seconds = tempFirst.seconds;
                if (seconds == null) {
                    seconds = new ArrayList<>();
                }
                tempFirst.seconds = seconds;
                String keyValue = jsonObject.get(key).getAsString();
                Second sameContentSec = null;
                for (Second tempSec : seconds) {
                    if (tempSec.content.equals(keyValue)) {
                        sameContentSec = tempSec;
                        break;
                    }
                }

                if (sameContentSec == null) {
                    // 没有找到有相同内容的second
                    sameContentSec = new Second();
                    sameContentSec.selectState = SpecificationItemView.SELECT_STATE_UNSELECTED;
                    sameContentSec.content = keyValue;
                    seconds.add(sameContentSec);// 添加到seconds里,相当于存入first里
                }

                List<InventoryBean> secInventories = sameContentSec.inventories;
                if (CollectionUtils.nullOrEmpty(secInventories)) {
                    secInventories = new ArrayList<>();
                }
                secInventories.add(inventory);
                sameContentSec.inventories = secInventories;// 保存规格信息在second中
            }
        }

        List<First> firsts = new ArrayList<>(firstTempMap.values());
        // 初始化规格中每个second的选择状态，如果只有唯一的可选值，默认second为选中状态
        for (First first : firsts) {
            if (first.seconds.size() == 1) {
                first.seconds.get(0).selectState = SpecificationItemView.SELECT_STATE_SELECTED;
            }
        }

        firstAdapter.setNewInstance(firsts);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSecondClick(String item) {

    }

    /**
     * 设置立刻购买和加入购物车按钮显示状态
     *
     * @param joinVis 加入购物车按钮显示状态
     * @param buyVis 立刻购买按钮显示状态
     */
    public void setBtnState(int joinVis, int buyVis) {
        this.joinVis = joinVis;
        this.buyVis = buyVis;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mBinding != null) {
            mBinding.tvBuyRightNow.setVisibility(buyVis);
            mBinding.tvJoinInfoShoppingCart.setVisibility(joinVis);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_good_specification, container, false);
        mBinding.recyclerView.setAdapter(firstAdapter);
        return mBinding.getRoot();
    }

    public static class First {
        public String name;
        public List<Second> seconds;
    }

    public static class Second {
        public String content;
        public Integer selectState;// 1 未选中 2 不可选中 3 选中
        public List<InventoryBean> inventories;// 当前可选项存在这些规格中
    }

    private class FirstAdapter extends BaseQuickAdapter<First, BaseViewHolder> {

        public FirstAdapter() {
            super(R.layout.item_specification);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, First first) {
            ItemSpecificationBinding itemSpecificationBinding = DataBindingUtil.bind(baseViewHolder.itemView);
            itemSpecificationBinding.tvSpecificationName.setText(first.name);
            //
            SecondAdapter secondAdapter = (SecondAdapter) itemSpecificationBinding.rvSpecification.getAdapter();
            if (secondAdapter == null) {
                // 次级的recyclerView绑定adapter
                secondAdapter = new SecondAdapter();
                itemSpecificationBinding.rvSpecification.setAdapter(secondAdapter);
                itemSpecificationBinding.rvSpecification
                        .setLayoutManager(new FlexboxLayoutManager(mBinding.recyclerView.getContext(),
                        FlexDirection.ROW, FlexWrap.WRAP));
                secondAdapter.setNewInstance(first.seconds);
                secondAdapter.setOnItemClickListener((adapter, view, position) -> {
                    // 次级item被点击
                    Second secondItem = (Second) adapter.getItem(position);
                    // 拿到item在外层recyclerView的层级
                    Integer index = firstAdapter.getData().indexOf(secondItem);
                    if (index == -1) {
                        // 逻辑上不会出现-1
                        Log.w("SecondAdapter", "the index of secondItem is -1");
                        return;
                    }
                });
            }
        }
    }

    private static class SecondAdapter extends BaseQuickAdapter<Second, BaseViewHolder> {

        public SecondAdapter() {
            super(R.layout.item_specification_value);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, Second second) {
            ItemSpecificationValueBinding itemSpecificationValueBinding = DataBindingUtil.bind(baseViewHolder.itemView);
            itemSpecificationValueBinding.tvItemContent.setText(second.content);
            itemSpecificationValueBinding.tvItemContent.setSelectState(second.selectState);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
    }
}
