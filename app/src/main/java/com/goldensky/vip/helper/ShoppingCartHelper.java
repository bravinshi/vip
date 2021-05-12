package com.goldensky.vip.helper;

import com.goldensky.vip.bean.ShoppingCartGoodsBean;
import com.goldensky.vip.event.ShoppingCartChangeEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartHelper {
    private static ShoppingCartHelper shoppingCartHelper;
    private List<ShoppingCartGoodsBean> goodsBeanList = new ArrayList<>();
    private ShoppingCartHelper() {
    }
    public static ShoppingCartHelper getInstance(){
        if(shoppingCartHelper==null){
            shoppingCartHelper=new ShoppingCartHelper();
        }
        return shoppingCartHelper;
    }
    public void changeCartGoodsNumber(String shoppingcartId,int count){
        for (ShoppingCartGoodsBean shoppingCartGoodsBean : goodsBeanList) {
            if(shoppingCartGoodsBean.getShoppingcartid().equals(shoppingcartId)){
                shoppingCartGoodsBean.setInventorynum(count);
            }
        }
    }
    public boolean isShoppingCartLoad(){
        return goodsBeanList.size()>0;
    }
    public void setShoppingCartGoodsBeanList(List<ShoppingCartGoodsBean> list){
        goodsBeanList.clear();
        goodsBeanList.addAll(list);
        onShopingCartListChange();
    }
    public void onShopingCartListChange(){
        EventBus.getDefault().post(new ShoppingCartChangeEvent(true));
    }

    public List<ShoppingCartGoodsBean> getGoodsBeanList() {
        return goodsBeanList;
    }
}
