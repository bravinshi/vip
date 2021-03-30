package com.goldensky.entity.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.entity.api.account.AccountService;
import com.goldensky.entity.base.error.FailCallback;
import com.goldensky.entity.base.net.NetParams;
import com.goldensky.entity.base.viewmodel.NetWorkViewModel;
import com.goldensky.entity.bean.LoginResponseBean;
import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.net.RetrofitAgent;


/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/11 15:40
 * 包名： com.goldensky.together.viewmodel
 * 类说明：
 */
public class LoginViewModel extends PublicViewModel {

    public MutableLiveData<LoginResponseBean> loginResponseLive = new MutableLiveData<>();

    /**
     * 登录
     *
     * @param mobile                     手机号
     * @param passwordOrVerificationCode 密码或者验证码
     * @param loginType                  登录方式
     */
    public void login(String mobile, String passwordOrVerificationCode, String loginType) {
        NetParams netParams = NetParams.create().add("username", mobile)
                .add("password", passwordOrVerificationCode)
                .add("appType", loginType)
                .add("accounttype", "2")
                .add("client_id", "admin-app")
                .add("client_secret", "123456")
                .add("grant_type", "password");
        RetrofitAgent.create(AccountService.class)
                .login(netParams)
                .subscribe(new ToastNetObserver<LoginResponseBean>() {
                    @Override
                    public void onSuccess(LoginResponseBean data) {
                        loginResponseLive.postValue(data);
                    }
                });
    }


}
