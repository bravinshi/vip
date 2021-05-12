package com.goldensky.vip.viewmodel.shoppingcart;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.api.shoppingcart.ShoppingCartService;
import com.goldensky.vip.bean.DeleteCartGoodsReqBean;
import com.goldensky.vip.bean.ShoppingCartGoodsBean;
import com.goldensky.vip.bean.UpdateCartGoodsNumberReqBean;
import com.goldensky.vip.bean.UserIdReqBean;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.List;

import io.reactivex.observers.TestObserver;

public class ShoppingCartViewModel extends PublicViewModel {
    public MutableLiveData<Object> updateCartGoodsNumberLive=new MutableLiveData<>();
    public MutableLiveData<Object> deleteCartGoodsLive=new MutableLiveData<>();

    /**
     * 修改购物车商品数量
     * @param belongtype
     * @param commodityid
     * @param commoditytype
     * @param inventoryid
     * @param purchasenum
     * @param shoppingcartid
     * @param userid
     * @param view
     */
    public void updateCartGoodsNumber(Integer belongtype, Integer commodityid, Integer commoditytype, Integer inventoryid, Integer purchasenum, String shoppingcartid, String userid, View view){
        UpdateCartGoodsNumberReqBean bean = new UpdateCartGoodsNumberReqBean(belongtype, commodityid, commoditytype, inventoryid, purchasenum, shoppingcartid, userid);
        RetrofitAgent.create(ShoppingCartService.class)
                .updateCartGoodsNumber(bean)
                .subscribe(new ToastNetObserver<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                            updateCartGoodsNumberLive.postValue(data);
                    }
                }.watchViewClickable(view));

    }

    public void deleteCartGoods(String shoppingCartIds){
        DeleteCartGoodsReqBean bean = new DeleteCartGoodsReqBean();
        bean.setShoppingcartids(shoppingCartIds);
        RetrofitAgent.create(ShoppingCartService.class)
                .deleteCartGoods(bean)
                .subscribe(new ToastNetObserver<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        deleteCartGoodsLive.postValue(data);
                    }
                });
    }
}
