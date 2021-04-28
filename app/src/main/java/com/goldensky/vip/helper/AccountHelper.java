package com.goldensky.vip.helper;

import com.goldensky.framework.util.SPUtils;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.bean.LoginResponseBean;
import com.google.gson.Gson;

public class AccountHelper {

    private static final String KEY_LOGIN_RESPONSE = "KEY_LOGIN_RESPONSE";

    private static final LoginResponseBean loginResponse = new LoginResponseBean();

    private static final AccountHelper instance = new AccountHelper();

    private AccountHelper() {
    }

    public static AccountHelper getInstance() {
        return instance;
    }

    public static void refresh(LoginResponseBean loginResponseBean) {
//        AccountHelper.loginResponse.copy(loginResponseBean);
        serialization();
    }

    public static void deserialization() {
        String json = SPUtils.getInstance().getString(KEY_LOGIN_RESPONSE, "");
        if (StringUtils.isTrimEmpty(json)) {
            return;
        }

        Gson gson = new Gson();
//        loginResponse.copy(gson.fromJson(json, LoginResponseBean.class));
    }

    public static void serialization() {
        Gson gson = new Gson();
        SPUtils.getInstance().put(KEY_LOGIN_RESPONSE, gson.toJson(loginResponse), true);
    }
}