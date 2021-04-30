package com.goldensky.vip.viewmodel.account;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.vip.api.account.AccountService;
import com.goldensky.vip.base.net.NetParams;
import com.goldensky.vip.bean.LoginResponseBean;
import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.bean.UpdateVipUserReqBean;
import com.goldensky.vip.viewmodel.PublicViewModel;


/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/11 15:40
 * 包名： com.goldensky.together.viewmodel
 * 类说明：
 */
public class AccountViewModel extends PublicViewModel {

    public MutableLiveData<LoginResponseBean> loginResponseLive = new MutableLiveData<>();
    public MutableLiveData<Integer> userLive = new MutableLiveData<>();

    /**
     * 登录
     *
     * @param mobile                     手机号
     * @param passwordOrVerificationCode 密码或者验证码
     * @param loginType                  登录方式
     */
    public void login(String mobile, String passwordOrVerificationCode, String loginType, View view) {
        NetParams netParams = NetParams.create().add("username", mobile)
                .add("password", passwordOrVerificationCode)
                .add("appType", loginType)
                .add("accounttype", "4")
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
                }.watchViewClickable(view));
    }


    public void updateVipUser(UpdateVipUserReqBean reqBean) {
        RetrofitAgent.create(AccountService.class)
                .updateVipUser(reqBean)
                .subscribe(new ToastNetObserver<Object>(){
                    @Override
                    public void onSuccess(Object data) {
                        userLive.postValue(1);
                    }
                });
    }
}