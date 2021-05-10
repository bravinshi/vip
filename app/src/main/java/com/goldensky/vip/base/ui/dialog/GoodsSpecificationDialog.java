package com.goldensky.vip.base.ui.dialog;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.framework.ui.dialog.BottomDialog;
import com.goldensky.framework.util.CollectionUtils;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.framework.util.ToastUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.base.ui.view.SpecificationItemView;
import com.goldensky.vip.bean.InventoryBean;
import com.goldensky.vip.databinding.DialogGoodSpecificationBinding;
import com.goldensky.vip.databinding.ItemSpecificationBinding;
import com.goldensky.vip.databinding.ItemSpecificationValueBinding;
import com.goldensky.vip.helper.ImageLoaderHelper;
import com.goldensky.vip.model.PurchaseQuantityModel;
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

    private InventoryBean selectedInventory;// 选择的规格
    private final PurchaseQuantityModel purchaseQuantityModel = new PurchaseQuantityModel("1");

    public void setData(List<InventoryBean> inventoryBeans) {
        if (CollectionUtils.nullOrEmpty(inventoryBeans)) {
            return;
        }

        List<First> firsts = transData(inventoryBeans);
        InventoryBean defaultSelectInventory = defaultSelectInventory(inventoryBeans, firsts);
        setSelectedInventory(defaultSelectInventory);
        firstAdapter.setNewInstance(firsts);
    }

    public void setSelectedInventory(InventoryBean inventory) {
        selectedInventory = inventory;
    }

    public void refresh() {
        if (selectedInventory != null) {
            // 图片
            ImageLoaderHelper.loadImage(mBinding.ivPic, selectedInventory.getInventoryPic());
            // 价格
            mBinding.tvPrice.setText(selectedInventory.getCommodityOldPrice() + "");
            mBinding.tvBuyFromNum.setText("最小购买数量:" + selectedInventory.getBuyFromNum());
        }
    }

    /**
     * 响应second点击事件
     *
     * @param specificationItemClickEvent 点击事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSecondClick(SpecificationItemClickEvent specificationItemClickEvent) {

    }

    /**
     * 默认选择第一个规格
     *
     * @param inventoryBeans 规格列表
     * @param firsts 渲染的数据结构
     *
     * @return 选择的规格
     */
    public InventoryBean defaultSelectInventory(List<InventoryBean> inventoryBeans, List<First> firsts) {
        // 默认选中第一个规格
        InventoryBean inventory = null;
        for (InventoryBean inventoryTemp : inventoryBeans) {
            String inventoryStr = inventoryTemp.getInventory();
            if (StringUtils.isTrimEmpty(inventoryStr)) {
                continue;
            }

            inventory = inventoryTemp;
            break;
        }
        if (inventory != null) {
            selectGivenInventory(inventory, firsts);
        }

        return inventory;
    }

    /**
     *
     * @param inventory
     * @param firsts
     */
    public void selectGivenInventory(InventoryBean inventory, List<First> firsts) {
        if (inventory == null) {
            return;
        }

        String inventoryStr = inventory.getInventory();
        Gson gson = new Gson();
        // 转成map结构
        JsonObject jsonObject = gson.fromJson(inventoryStr, JsonObject.class);
        Set<String> keySet = jsonObject.keySet();
        Map<String, String> jsonObjectMap = new HashMap<>();
        for (String key : keySet) {
            String value = jsonObject.get(key).getAsString();
            jsonObjectMap.put(key, value);
        }

        for (First first : firsts) {
            String name = first.name;
            String value = jsonObjectMap.get(name);
            // 拿到second中对应对应value的second
            for (Second second : first.seconds) {
                if (second.content.equals(value)) {
                    second.selectState = SpecificationItemView.SELECT_STATE_SELECTED;
                    break;
                }
            }
        }
    }

    /**
     * 把规格列表的数据结构转换成用于渲染的列表数据结构
     *
     * @param inventoryBeans 规格列表的数据结构
     * @return 用于渲染的列表数据结构
     */
    public List<First> transData(List<InventoryBean> inventoryBeans) {
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

        return new ArrayList<>(firstTempMap.values());
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

            refresh();
        }
    }

    private void initListener() {
        mBinding.btnClose.setOnClickListener(v -> dismissAllowingStateLoss());
        // 加入购物车监听
        mBinding.tvJoinInfoShoppingCart.setOnClickListener(v -> {

        });
        // 立即购买监听
        mBinding.tvJoinInfoShoppingCart.setOnClickListener(v -> {

        });

        mBinding.btnDecrease.setOnClickListener(v -> purchaseQuantityModel.decrease());

        mBinding.btnIncrease.setOnClickListener(v -> purchaseQuantityModel.increase());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_good_specification, container, false);
        mBinding.recyclerView.setAdapter(firstAdapter);
        mBinding.setModel(purchaseQuantityModel);
        initListener();
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

    public static class SpecificationItemClickEvent {
        private Integer firstPosition;
        private Second second;

        public Integer getFirstPosition() {
            return firstPosition;
        }

        public void setFirstPosition(Integer firstPosition) {
            this.firstPosition = firstPosition;
        }

        public Second getSecond() {
            return second;
        }

        public void setSecond(Second second) {
            this.second = second;
        }
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
                    First firstTag = (First) adapter.getRecyclerView().getTag();
                    Integer index = firstAdapter.getData().indexOf(firstTag);
                    if (index == -1) {
                        // 逻辑上不会出现-1
                        Log.w("SecondAdapter", "the index of secondItem is -1");
                        return;
                    }
                    // 发送点击事件
                    SpecificationItemClickEvent specificationItemClickEvent = new SpecificationItemClickEvent();

                    specificationItemClickEvent.setFirstPosition(index);
                    specificationItemClickEvent.setSecond(secondItem);

                    EventBus.getDefault().post(specificationItemClickEvent);
                });
            }

            itemSpecificationBinding.rvSpecification.setTag(first);
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
