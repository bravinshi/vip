package com.goldensky.vip.helper;

import com.goldensky.framework.util.SPUtils;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.framework.util.ToastUtils;
import com.goldensky.vip.bean.LoginResponseBean;
import com.google.gson.Gson;

public class AccountHelper {

    private static final String KEY_LOGIN_RESPONSE = "KEY_LOGIN_RESPONSE";

    private static final LoginResponseBean loginResponse = new LoginResponseBean();

    private static final AccountHelper instance = new AccountHelper();

    private AccountHelper() {
    }

    public static LoginResponseBean copy() {
        LoginResponseBean responseBean = new LoginResponseBean();

        responseBean.setVipUser(loginResponse.getVipUser().copy());
        responseBean.setTokenHead(loginResponse.getTokenHead());
        responseBean.setToken(loginResponse.getToken());
        responseBean.setRefreshToken(loginResponse.getRefreshToken());
        responseBean.setExpiresIn(loginResponse.getExpiresIn());

        return responseBean;
    }

    public static String getUserId() {
        if (loginResponse.getVipUser() != null)
            return loginResponse.getVipUser().getUserId();
        return null;
    }

    public static void refresh(LoginResponseBean loginResponseBean) {
        loginResponse.setExpiresIn(loginResponseBean.getExpiresIn());
        loginResponse.setRefreshToken(loginResponseBean.getRefreshToken());
        loginResponse.setToken(loginResponseBean.getToken());
        loginResponse.setTokenHead(loginResponseBean.getTokenHead());
        loginResponse.setVipUser(loginResponseBean.getVipUser());

        serialization();
    }

    private static void deserialization() {
        String json = SPUtils.getInstance().getString(KEY_LOGIN_RESPONSE, "");
        if (StringUtils.isTrimEmpty(json)) {
            return;
        }

        Gson gson = new Gson();
        refresh(gson.fromJson(json, LoginResponseBean.class));
    }

    private static void serialization() {
        Gson gson = new Gson();
        SPUtils.getInstance().put(KEY_LOGIN_RESPONSE, gson.toJson(loginResponse), true);
    }

    public static void deserializationAgent() {
        deserialization();
    }

    public static void serializationAgent() {
        serialization();
    }

    public static void login(LoginResponseBean loginResponseBean) {
        refresh(loginResponseBean);
    }
}