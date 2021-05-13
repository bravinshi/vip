package com.goldensky.vip.helper;

import com.goldensky.vip.bean.ShoppingCartGoodsBean;
import com.goldensky.vip.event.ShoppingCartChangeEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCartHelper {
    private static ShoppingCartHelper shoppingCartHelper;
    private List<ShoppingCartGoodsBean> goodsBeanList = new ArrayList<>();
    private List<String> shoppingcartIds = new ArrayList<>();
    private Double sumMoney = 0.00;

    private ShoppingCartHelper() {
    }

    public static ShoppingCartHelper getInstance() {
        if (shoppingCartHelper == null) {
            shoppingCartHelper = new ShoppingCartHelper();
        }
        return shoppingCartHelper;
    }

    public void changeCartGoodsNumber(String shoppingcartId, int count) {
        for (ShoppingCartGoodsBean shoppingCartGoodsBean : goodsBeanList) {
            if (shoppingCartGoodsBean.getShoppingcartid().equals(shoppingcartId)) {
                shoppingCartGoodsBean.setPurchasenum(count);
            }
        }
        noRefreshEvent();
    }

    public boolean isGoodsChecked(String shoppingcartId) {
        return shoppingcartIds.contains(shoppingcartId);
    }

    public void changeGoodsChecked(String shoppingcartId, boolean isChecked) {
        if (isChecked) {
            shoppingcartIds.add(shoppingcartId);
        } else {
            shoppingcartIds.remove(shoppingcartId);
        }

        noRefreshEvent();
    }

    public void changeSelectAllGoods(boolean isSelectAll) {
        shoppingcartIds.clear();
        if (isSelectAll) {
            for (ShoppingCartGoodsBean shoppingCartGoodsBean : getGoodsBeanList()) {
                shoppingcartIds.add(shoppingCartGoodsBean.getShoppingcartid());
            }
        }
    }

    public boolean isSelectAll() {
        return shoppingcartIds.size() == goodsBeanList.size();
    }

    public void deleteCartGoods() {
        Iterator<ShoppingCartGoodsBean> iterator = goodsBeanList.iterator();
        while (iterator.hasNext()) {
            ShoppingCartGoodsBean next = iterator.next();
            if (shoppingcartIds.contains(next.getShoppingcartid())) {
                iterator.remove();
            }
        }
        shoppingcartIds.clear();
        onShopingCartListChange();
    }

    public Double getSumMoney() {
        sumMoney = 0.00;
        for (ShoppingCartGoodsBean shoppingCartGoodsBean : goodsBeanList) {
            if (shoppingcartIds.contains(shoppingCartGoodsBean.getShoppingcartid())) {
                sumMoney += shoppingCartGoodsBean.getCommodityoldprice() * shoppingCartGoodsBean.getPurchasenum();
            }
        }
        return sumMoney;
    }

    public boolean isShoppingCartLoad() {
        return goodsBeanList.size() > 0;
    }

    public void setShoppingCartGoodsBeanList(List<ShoppingCartGoodsBean> list) {
        goodsBeanList.clear();
        goodsBeanList.addAll(list);
        onShopingCartListChange();
    }

    public String getShoppingCartIds() {
        String shoppingCartIdsStr = "";
        for (int i = 0; i < shoppingcartIds.size(); i++) {
            if (i == 0) {
                shoppingCartIdsStr += shoppingcartIds.get(i);
            } else {
                shoppingCartIdsStr += "," + shoppingcartIds.get(i);
            }
        }
        return shoppingCartIdsStr;
    }

    public void onShopingCartListChange() {
        EventBus.getDefault().post(new ShoppingCartChangeEvent(true, true));
    }

    public void noRefreshEvent() {
        EventBus.getDefault().post(new ShoppingCartChangeEvent(true, false));
    }

    public List<ShoppingCartGoodsBean> getGoodsBeanList() {
        return goodsBeanList;
    }
}
