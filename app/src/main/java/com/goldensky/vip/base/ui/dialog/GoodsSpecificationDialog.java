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
import com.goldensky.vip.event.JoinOrBuyEvent;
import com.goldensky.vip.event.ShowSpecificationEvent;
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
import java.util.HashSet;
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
    private Integer belongType;

    public GoodsSpecificationDialog(Integer belongType){
        this.belongType = belongType;
    }

    public void setData(List<InventoryBean> inventoryBeans) {
        if (CollectionUtils.nullOrEmpty(inventoryBeans)) {
            return;
        }

        List<First> firsts = transData(inventoryBeans);
        InventoryBean defaultSelectInventory = defaultSelectInventory(inventoryBeans, firsts);
        setSelectedInventory(defaultSelectInventory);
        checkSecondSelectState(firsts);
        firstAdapter.setNewInstance(firsts);
    }

    public void setSelectedInventory(InventoryBean inventory) {
        selectedInventory = inventory;
        ShowSpecificationEvent showSpecificationEvent = new ShowSpecificationEvent();
        showSpecificationEvent.setSpecification(selectedInventory.getSpecificationForShow());
        EventBus.getDefault().post(showSpecificationEvent);
        refresh();
    }

    public void refresh() {
        if (selectedInventory != null && mBinding != null) {
            // 图片
            ImageLoaderHelper.loadImage(mBinding.ivPic, selectedInventory.getInventoryPic());
            // 价格
            mBinding.tvPrice.setText(selectedInventory.getCommodityOldPrice() + "");
            mBinding.tvBuyFromNum.setText("最小购买数量:" + selectedInventory.getBuyFromNum());
        }
    }

    private void join(){
        // 检查规格和购买数量有效性
        if (check()) {
            JoinOrBuyEvent joinOrBuyEvent = new JoinOrBuyEvent();

            joinOrBuyEvent.setJoin(true);
            joinOrBuyEvent.setPurchaseNum(purchaseQuantityModel.getPurchaseQuantityInt());
            joinOrBuyEvent.setInventory(selectedInventory);

            EventBus.getDefault().post(joinOrBuyEvent);
        }
    }

    private void buy(){
        // 检查规格和购买数量有效性
        if (check()) {
            JoinOrBuyEvent joinOrBuyEvent = new JoinOrBuyEvent();

            joinOrBuyEvent.setJoin(false);
            joinOrBuyEvent.setPurchaseNum(purchaseQuantityModel.getPurchaseQuantityInt());
            joinOrBuyEvent.setInventory(selectedInventory);

            EventBus.getDefault().post(joinOrBuyEvent);
        }
    }

    private boolean check() {
        List<First> firsts = firstAdapter.getData();

        int count = 0;
        for (First first : firsts) {
            for (Second second : first.seconds) {
                if (second.selectState.equals(SpecificationItemView.SELECT_STATE_SELECTED)) {
                    count++;
                    break;
                }
            }
        }

        if (count != firsts.size()) {
            // 未成功选择规格
            ToastUtils.showShort("请选择规格");
            return false;
        }

        // 检查购买数量
//        if (purchaseQuantityModel.getPurchaseQuantityInt() < selectedInventory.getBuyFromNum()) {
//            ToastUtils.showShort("购买数量不能低于最小购买数量");
//            return false;
//        }

        if (belongType != null && belongType == 0
                && selectedInventory.getInventoryNum() < purchaseQuantityModel.getPurchaseQuantityInt()) {
            ToastUtils.showShort("库存不足");
            return false;
        }

        return true;
    }

    /**
     * 响应second点击事件
     *
     * @param specificationItemClickEvent 点击事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSecondClick(SpecificationItemClickEvent specificationItemClickEvent) {
        Second second = specificationItemClickEvent.getSecond();

        if (second.selectState.equals(SpecificationItemView.SELECT_STATE_NOT_AVAILABLE)) {
            // 不可点击的状态，忽略
            return;
        }

        int firstPosition = specificationItemClickEvent.getFirstPosition();
        List<First> firsts = firstAdapter.getData();

        if (second.selectState.equals(SpecificationItemView.SELECT_STATE_SELECTED)) {
            // 取消选择
            // 去掉second的选择状态
            second.selectState = SpecificationItemView.SELECT_STATE_UNSELECTED;
            // 检查所有second的选择状态
            checkSecondSelectState(firstAdapter.getData());
            firstAdapter.notifyDataSetChanged();
            return;
        }

        if (second.selectState.equals(SpecificationItemView.SELECT_STATE_UNSELECTED)) {
            // 选择
            // 将同层的其它second设置成未选择状态，将点击的second设置成选择状态
            for (Second secondTemp : firsts.get(firstPosition).seconds) {
                secondTemp.selectState = SpecificationItemView.SELECT_STATE_UNSELECTED;
            }
            second.selectState = SpecificationItemView.SELECT_STATE_SELECTED;
            // 检查所有second的选择状态
            checkSecondSelectState(firstAdapter.getData());
            firstAdapter.notifyDataSetChanged();
        }
    }


    public void checkSecondSelectState(List<First> firsts) {
        if (CollectionUtils.nullOrEmpty(firsts)) {
            return;
        }
        // 1 拿到所有被选择的Second 每层的被选择的second只有一个
        Map<Integer, Second> selectedSecondsMap = new HashMap<>();
        for (int i = 0; i < firsts.size(); i++) {
            for (Second second : firsts.get(i).seconds) {
                if (second.selectState.equals(SpecificationItemView.SELECT_STATE_SELECTED)) {
                    selectedSecondsMap.put(i, second);// 只有一个，所以跳出
                    break;
                }
            }
        }

        if (selectedSecondsMap.size() == 0) {
            for (First first : firsts) {
                for (Second second : first.seconds) {
                    second.selectState = SpecificationItemView.SELECT_STATE_UNSELECTED;
                }
            }

            return;
        }
        // 当判断每层的second的选择状态时
        // 如果定义selectedSeconds中除去待检验层的second后剩余的所有second中的规格交集为A，则有如下结论
        // 遍历待检验层的second，如果second中的规格列表中包含A中所有的元素，则second应置为可选择状态，否则设置为不可选状态
        // 1 拿到除去待检验层的second规格交集
        for (int i = 0; i < firsts.size(); i++) {
            // i 为待检验层的标识
            First first = firsts.get(i);
            Set<InventoryBean> intersection = new HashSet<>();// 去重
            Second removed = selectedSecondsMap.remove(i);
            // 拿到其它层包含的所有的规格
            Set<Second> remainder = new HashSet<>(selectedSecondsMap.values());
            if (remainder.size() == 0) {
                // 其它层没有被选中的second
                // 当前层的所有未选中状态的second都设置成可选择
                for (Second second : first.seconds) {
                    if (!second.selectState.equals(SpecificationItemView.SELECT_STATE_SELECTED)) {
                        second.selectState = SpecificationItemView.SELECT_STATE_UNSELECTED;
                    }
                }

                if (removed != null) {// 放回removed
                    selectedSecondsMap.put(i, removed);
                }

                continue;
            }
            // 先求并集
            for (Second second : remainder) {
                intersection.addAll(second.inventories);
            }
            // 再求交集
            for (Second second : remainder) {
                intersection.retainAll(second.inventories);
            }
            // 逻辑上来说intersection内必然有元素，因为选择的second不可能不在同一个规格内
            // 检查i层的每个second状态
            for (Second second : first.seconds) {
                if (second.selectState.equals(SpecificationItemView.SELECT_STATE_SELECTED)) {
                    // 已选择状态的second忽略
                    continue;
                }

                // 先置为不可选状态
                second.selectState = SpecificationItemView.SELECT_STATE_NOT_AVAILABLE;

                for (InventoryBean inventoryBean : second.inventories) {
                    if (intersection.contains(inventoryBean)) {// 可以被选择
                        second.selectState = SpecificationItemView.SELECT_STATE_UNSELECTED;
                        break;
                    }
                }
            }

            if (removed != null) {// 放回removed
                selectedSecondsMap.put(i, removed);
            }
        }

        // 处理被选中的规格
        if (selectedSecondsMap.size() == firsts.size()) {
            // 每一层都有second被选中，所以必定选中了一个规格
            Set<InventoryBean> intersectionList = new HashSet<>();// 此处需要去重，因为下面会判断交集的size
            Set<Second> remainderSet = new HashSet<>(selectedSecondsMap.values());
            // 先求并集
            for (Second second : remainderSet) {
                intersectionList.addAll(second.inventories);
            }
            // 再求交集
            for (Second second : remainderSet) {
                intersectionList.retainAll(second.inventories);
            }

            if (intersectionList.size() != 1) {
                Log.w(GoodsSpecificationDialog.class.getSimpleName(), "intersectionList.size() != 1");
            } else {
                setSelectedInventory((InventoryBean) intersectionList.toArray()[0]);
            }
        }
    }

    /**
     * 默认选择第一个规格
     *
     * @param inventoryBeans 规格列表
     * @param firsts         渲染的数据结构
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
                // 如果存在second信息，则把这一规格信息保存到second中
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
     * @param buyVis  立刻购买按钮显示状态
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
            join();
        });
        // 立即购买监听
        mBinding.tvBuyRightNow.setOnClickListener(v -> {
            buy();
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
            // 每次convert都重新设置LayoutManager是为了让每个内层的recyclerView都能在外层的adapter notifyDataSetChanged的时候重新绘制
            itemSpecificationBinding.rvSpecification
                    .setLayoutManager(new FlexboxLayoutManager(mBinding.recyclerView.getContext(),
                            FlexDirection.ROW, FlexWrap.WRAP));
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
