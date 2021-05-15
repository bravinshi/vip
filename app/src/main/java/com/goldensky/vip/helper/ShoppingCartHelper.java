package com.goldensky.vip.helper;

import com.goldensky.vip.bean.ConfirmOrderItemBean;
import com.goldensky.vip.bean.ShoppingCartGoodsBean;
import com.goldensky.vip.event.ShoppingCartChangeEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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
    public boolean hasSelect(){
        return shoppingcartIds.size()!=0;
    }
    public void clearSelect(){
        shoppingcartIds.clear();
        noRefreshChange();
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

        noRefreshChange();
    }
    public List<ConfirmOrderItemBean> getConfirmOrderList(){
        if(shoppingcartIds.size()>0){
            List<ConfirmOrderItemBean> confirmOrderItemBeans = new ArrayList<>();
            for (ShoppingCartGoodsBean shoppingCartGoodsBean : goodsBeanList) {
                if(shoppingcartIds.contains(shoppingCartGoodsBean.getShoppingcartid())){
                    ConfirmOrderItemBean bean = new ConfirmOrderItemBean();
                    bean.setBelongId(shoppingCartGoodsBean.getBelongid());
                    bean.setBelongType(shoppingCartGoodsBean.getBelongtype());
                    bean.setCommodityId(shoppingCartGoodsBean.getCommodityid());
                    bean.setCommodityName(shoppingCartGoodsBean.getCommodityname());
                    bean.setInventoryId(shoppingCartGoodsBean.getInventoryid());
                    bean.setPic(shoppingCartGoodsBean.getInventorypic());
                    bean.setPrice(shoppingCartGoodsBean.getCommodityoldprice());
                    bean.setPurchaseNum(shoppingCartGoodsBean.getPurchasenum());
                    bean.setSpecification(shoppingCartGoodsBean.getInventory());
                    confirmOrderItemBeans.add(bean);
                }
            }
            return confirmOrderItemBeans;
        }
        return null;
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



    public Double getSumMoney() {
        sumMoney = 0.00;
        for (ShoppingCartGoodsBean shoppingCartGoodsBean : goodsBeanList) {
            if (shoppingcartIds.contains(shoppingCartGoodsBean.getShoppingcartid())) {
                sumMoney += shoppingCartGoodsBean.getCommodityoldprice() * shoppingCartGoodsBean.getPurchasenum();
            }
        }
        return sumMoney;
    }

    public void setShoppingCartGoodsBeanList(List<ShoppingCartGoodsBean> list) {
        goodsBeanList.clear();
       if(list!=null){
           goodsBeanList.addAll(list);
       }
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
        EventBus.getDefault().post(new ShoppingCartChangeEvent(true));
    }
    public void noRefreshChange() {
        EventBus.getDefault().post(new ShoppingCartChangeEvent(false));
    }

    public List<ShoppingCartGoodsBean> getGoodsBeanList() {
        return goodsBeanList;
    }
}
