package com.goldensky.vip.helper;

import com.goldensky.framework.util.SPUtils;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.framework.util.ToastUtils;
import com.goldensky.vip.bean.LoginResponseBean;
import com.goldensky.vip.event.VipUserChangeEvent;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

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

    /**
     * 退出登录
     */
    public static void loginOut() {
        loginResponse.clear();
        serialization();
    }

    public static String getUserId() {
        if (loginResponse.getVipUser() != null)
            return loginResponse.getVipUser().getUserid();
        return "";
    }
    public static String getUserNick() {
        if (loginResponse.getVipUser() != null)
            return loginResponse.getVipUser().getUserNick();
        return "";
    }
    public static String getUserPic() {
        if (loginResponse.getVipUser() != null)
            return loginResponse.getVipUser().getUserpic();
        return "";
    }
    public static String getUserMobile() {
        if (loginResponse.getVipUser() != null)
            return loginResponse.getVipUser().getUsermobile();
        return "";
    }

    public static String getToken() {
        if (StringUtils.isTrimEmpty(loginResponse.getTokenHead())
        || StringUtils.isTrimEmpty(loginResponse.getToken())) {
            return "";
        }

        return loginResponse.getTokenHead() + loginResponse.getToken();
    }
    public static void setNick(String nick){
        loginResponse.getVipUser().setUserNick(nick);
        onVipUserChanged();
    }
    public static void setUserPic(String path){
        loginResponse.getVipUser().setUserpic(path);
        onVipUserChanged();
    }
    private static void onVipUserChanged(){
        EventBus.getDefault().post(new VipUserChangeEvent(true));
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