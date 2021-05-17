package com.goldensky.vip.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.vip.api.PublicService;
import com.goldensky.vip.api.account.AccountService;
import com.goldensky.vip.api.account.AddressService;
import com.goldensky.vip.api.goods.GoodsService;
import com.goldensky.vip.api.shoppingcart.ShoppingCartService;
import com.goldensky.vip.base.error.FailCallback;
import com.goldensky.vip.base.viewmodel.NetWorkViewModel;
import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.bean.AreaListBean;
import com.goldensky.vip.bean.CheckVersionResBean;
import com.goldensky.vip.bean.CommodityBean;
import com.goldensky.vip.bean.GoodsCommentResBean;
import com.goldensky.vip.bean.JoinIntoShoppingCartReqBean;
import com.goldensky.vip.bean.ShoppingCartGoodsBean;
import com.goldensky.vip.bean.SuperStBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.bean.UserIdReqBean;
import com.google.gson.JsonObject;

import java.io.File;
import java.net.URLConnection;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Query;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/14 10:37
 * 包名： com.goldensky.together.viewmodel
 * 类说明：
 */
public class PublicViewModel extends NetWorkViewModel {

    public MutableLiveData<Boolean> verificationCodeLive = new MutableLiveData<>();
    public MutableLiveData<Boolean> checkCodeLive = new MutableLiveData<>();

    public MutableLiveData<String> uploadPicLiveData = new MutableLiveData<>();
    public MutableLiveData<GoodsCommentResBean> goodsCommentLive = new MutableLiveData<>();
    public MutableLiveData<List<UserAddressBean>> userAddressLive = new MutableLiveData<>();
    public MutableLiveData<Boolean> joinIntoShoppingCartResultLive = new MutableLiveData<>();
    public MutableLiveData<List<CommodityBean>> normalGoodsLive = new MutableLiveData<>();

    public MutableLiveData<List<AreaListBean>> areaListLive = new MutableLiveData<>();
    public MutableLiveData<List<ShoppingCartGoodsBean>> shoppingCartListLive = new MutableLiveData<>();
    public MutableLiveData<SuperStBean> mSuperStBean = new MutableLiveData<>();

    public MutableLiveData<CheckVersionResBean> checkVersionLive = new MutableLiveData<>();


    public void uploadPic(String filePath, final FailCallback callback) {
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse(URLConnection.guessContentTypeFromName(filePath)), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RetrofitAgent.create(PublicService.class).uploadPic(part).subscribe(new ToastNetObserver<String>() {

            @Override
            public void onSuccess(String data) {
                uploadPicLiveData.postValue(data);
            }

            @Override
            public boolean onFail(NetResponse<String> data) {
                super.onFail(data);
                if (callback != null) {
                    callback.onFail(data);
                }

                return false;
            }
        });
    }

    /**
     * 获取验证码
     *
     * @param mobile 手机号
     */
    public void getVerificationCode(String mobile) {
        RetrofitAgent.create(PublicService.class)
                .getVerifyCode(mobile)
                .subscribe(new ToastNetObserver<Boolean>() {
                    @Override
                    public void onSuccess(Boolean data) {
                        verificationCodeLive.postValue(data);
                    }
                });
    }

    public void checkCode(String phone, String code, final FailCallback callback) {
        RetrofitAgent.create(PublicService.class).checkVerifyCode(phone, code)
                .subscribe(new ToastNetObserver<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        checkCodeLive.postValue(true);
                    }

                    @Override
                    public boolean onFail(NetResponse<Object> data) {
                        super.onFail(data);
                        if (callback != null) {
                            callback.onFail(data);
                        }

                        return false;
                    }
                });
    }

    /**
     * 获取商品评价列表
     *
     * @param currentPage  当前页
     * @param pageSize     页大小
     * @param commodityId  商品id
     * @param evaluateType 筛选条件
     */
    public void getGoodsComment(Integer currentPage, Integer pageSize,
                                String commodityId, Integer evaluateType, final FailCallback callback) {
        RetrofitAgent.create(GoodsService.class)
                .getGoodsComment(currentPage, pageSize, commodityId, evaluateType)
                .subscribe(new ToastNetObserver<GoodsCommentResBean>() {
                    @Override
                    public void onSuccess(GoodsCommentResBean data) {
                        goodsCommentLive.postValue(data);
                    }

                    @Override
                    public boolean onFail(NetResponse<GoodsCommentResBean> data) {
                        super.onFail(data);
                        if (callback != null) {
                            callback.onFail(data);
                        }
                        return false;
                    }
                });
    }

    /**
     * 加载收货地址列表
     *
     * @param userId
     */
    public void getUserAddress(String userId) {
        UserIdReqBean reqBean = new UserIdReqBean();
        reqBean.setUserid(userId);
        RetrofitAgent.create(AddressService.class)
                .getUserAddressList(reqBean)
                .subscribe(new ToastNetObserver<List<UserAddressBean>>() {
                    @Override
                    public void onSuccess(List<UserAddressBean> data) {
                        userAddressLive.postValue(data);
                    }
                });
    }

    // 加入购物车
    public void joinIntoShoppingCart(JoinIntoShoppingCartReqBean reqBean) {
        RetrofitAgent.create(GoodsService.class)
                .joinIntoShoppingCart(reqBean)
                .subscribe(new ToastNetObserver<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        joinIntoShoppingCartResultLive.postValue(true);
                    }

                    @Override
                    public boolean onFail(NetResponse<Object> data) {
                        super.onFail(data);
                        joinIntoShoppingCartResultLive.postValue(false);
                        return false;
                    }
                });
    }

    /**
     * 获取省市县列表
     */
    public void getAreaList() {
        RetrofitAgent.create(AddressService.class)
                .getAreaList()
                .subscribe(new ToastNetObserver<List<AreaListBean>>() {
                    @Override
                    public void onSuccess(List<AreaListBean> data) {
                        areaListLive.postValue(data);
                    }
                });
    }


    /**
     * 获取购物车列表
     *
     * @param userId
     */
    public void getShoppingCartList(String userId) {
        UserIdReqBean userIdReqBean = new UserIdReqBean();
        userIdReqBean.setUserid(userId);
        RetrofitAgent.create(ShoppingCartService.class)
                .getVipShoppingCartList(userIdReqBean)
                .subscribe(new ToastNetObserver<List<ShoppingCartGoodsBean>>() {
                    @Override
                    public void onSuccess(List<ShoppingCartGoodsBean> data) {
                        shoppingCartListLive.postValue(data);
                    }
                });

    }

    // 今日爆款
    public void hotGoodsToady(Integer currentPage, Integer pageSize, String userId, FailCallback failCallback) {
        RetrofitAgent.create(GoodsService.class)
                .hotGoodsToady(currentPage, pageSize, userId)
                .subscribe(new ToastNetObserver<List<CommodityBean>>(){
                    @Override
                    public void onSuccess(List<CommodityBean> data) {
                        normalGoodsLive.postValue(data);
                    }

                    @Override
                    public boolean onFail(NetResponse<List<CommodityBean>> data) {
                        super.onFail(data);
                        if (failCallback != null) {
                         failCallback.onFail(data);
                        }
                        return true;
                    }
                });
    }

    // 今日优选
    public void optimization(Integer currentPage, Integer pageSize, String userId, FailCallback failCallback) {
        RetrofitAgent.create(GoodsService.class)
                .optimization(currentPage, pageSize, userId)
                .subscribe(new ToastNetObserver<List<CommodityBean>>(){
                    @Override
                    public void onSuccess(List<CommodityBean> data) {
                        normalGoodsLive.postValue(data);
                    }

                    @Override
                    public boolean onFail(NetResponse<List<CommodityBean>> data) {
                        super.onFail(data);
                        if (failCallback != null) {
                            failCallback.onFail(data);
                        }
                        return true;
                    }
                });
    }

    // 为你推荐
    public void recommend(Integer currentPage, Integer pageSize, String userId, FailCallback failCallback) {
        RetrofitAgent.create(GoodsService.class)
                .recommend(currentPage, pageSize, userId)
                .subscribe(new ToastNetObserver<List<CommodityBean>>(){
                    @Override
                    public void onSuccess(List<CommodityBean> data) {
                        normalGoodsLive.postValue(data);
                    }

                    @Override
                    public boolean onFail(NetResponse<List<CommodityBean>> data) {
                        super.onFail(data);
                        if (failCallback != null) {
                            failCallback.onFail(data);
                        }
                        return true;
                    }
                });
    }

    public void getSuperSt(String userId) {
        UserIdReqBean bean = new UserIdReqBean();
        bean.setUserid(userId);
        RetrofitAgent.create(AccountService.class)
                .getUserAndMerchant(bean)
                .subscribe(new ToastNetObserver<SuperStBean>(){
                    @Override
                    public void onSuccess(SuperStBean data) {
                        mSuperStBean.postValue(data);
                    }
                });
    }

    //版本更新
    public void checkVersion(Integer version, FailCallback failCallback) {
        RetrofitAgent.create(PublicService.class)
                .checkVision(1, version)
                .subscribe(new ToastNetObserver<CheckVersionResBean>(){
                    @Override
                    public void onSuccess(CheckVersionResBean data) {
                        checkVersionLive.postValue(data);
                    }

                    @Override
                    public boolean onFail(NetResponse<CheckVersionResBean> data) {
                        super.onFail(data);
                        if (failCallback != null) {
                            failCallback.onFail(data);
                        }
                        return true;
                    }
                });
    }
}
