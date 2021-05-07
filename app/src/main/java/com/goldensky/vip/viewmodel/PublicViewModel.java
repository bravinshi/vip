package com.goldensky.vip.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.vip.api.PublicService;
import com.goldensky.vip.api.goods.GoodsService;
import com.goldensky.vip.base.error.FailCallback;
import com.goldensky.vip.base.viewmodel.NetWorkViewModel;
import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.bean.GoodsCommentResBean;

import java.io.File;
import java.net.URLConnection;

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
     * @param mobile  手机号
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
     * @param currentPage 当前页
     * @param pageSize 页大小
     * @param commodityId 商品id
     * @param evaluateType 筛选条件
     */
    public void getGoodsComment(String currentPage, Integer pageSize,
                                String commodityId, Integer evaluateType) {
        RetrofitAgent.create(GoodsService.class)
                .getGoodsComment(currentPage, pageSize, commodityId, evaluateType)
                .subscribe(new ToastNetObserver<GoodsCommentResBean>(){
                    @Override
                    public void onSuccess(GoodsCommentResBean data) {
                        goodsCommentLive.postValue(data);
                    }
                });
    }
}
