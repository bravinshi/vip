package com.goldensky.entity.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.entity.api.PublicService;
import com.goldensky.entity.base.error.FailCallback;
import com.goldensky.entity.base.viewmodel.NetWorkViewModel;
import com.goldensky.entity.bean.LoginResponseBean;
import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.net.RetrofitAgent;

import java.io.File;
import java.net.URLConnection;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
    public MutableLiveData<Object> changePasswordLive = new MutableLiveData<>();

    public MutableLiveData<String> uploadPicLiveData = new MutableLiveData<>();
    public MutableLiveData<LoginResponseBean.UserBean> userInfoLive = new MutableLiveData<>();

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
     * @param forWhat 用途
     * @param mobile  手机号
     */
    public void getVerificationCode(String forWhat, String mobile) {
        RetrofitAgent.create(PublicService.class)
                .getVerifyCode(mobile, forWhat)
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
                        if (callback != null) {
                            callback.onFail(data);
                        }

                        return false;
                    }
                });
    }

}
