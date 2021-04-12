package com.goldensky.vip.helper;

import android.content.Context;
import android.text.TextUtils;

import com.goldensky.vip.Starter;
import com.goldensky.vip.bean.LoginResponseBean;
import com.goldensky.framework.util.SPUtils;

public class AccountHelper {

    private static final String SP_FIRST = "first"; //首次打开应用；用于是否显示引导页;false 显示；true不显示
    private static final String SP_NAME = "AccountHelper";
    private static final String SP_TOKEN = "token";
    private static final String SP_PWD = "pwd";
    private static final String SP_LIVENAME = "livename";
    private static final String SP_NICKNAME = "nickname";
    private static final String SP_AVATAR = "avatar";
    private static final String SP_GENDER = "gender";
    private static final String SP_USERID = "userid";
    private static final String SP_USERTYPE = "usertype";
    private static final String SP_MOBILE = "SP_MOBILE";
    private static final String SP_SUPPLIERID = "supplierid";//店铺id
    private static final String SP_CITY = "city";//店铺id
    //用户身份 0未登录 1普通用户 2医生 3咨询师 4医院
    private static final String SP_IDENTITY = "identity";
    //医院->座机电话
    private static final String SP_TELEPHONE = "telephone";
    //4中身份用户的 城市id 城市name 经纬度
    private static final String SP_CITY_ID = "city_id";
    //三方登录的信息
    private static final String SP_OPEN_ID = "open_id";
    private static final String SP_ICONURL = "iconurl";
    private static final String SP_UID = "uid";

    private static final String SP_CITY_NAME = "city_name";
    private static final String SP_LATITUDE = "latitude";
    private static final String SP_LONGITUDE = "longitude";

    private static final String SP_YUNXIN_ACCID = "yunxin_accid";
    private static final String SP_YUNXIN_TOKEN = "yunxin_token";

//    private static final String SP_ONLINE_STATUS = "SP_ONLINE_STATUS";
    //是否是新用户 0是 1不是
//    private static final String SP_ISNEW = "SP_ISNEW";
    private static final String SP_INVITIEDCODE = "SP_INVITIEDCODE";
    private static final String SP_SUPERIORCOMPANY = "SP_SUPERIORCOMPANY";
    private static final String SP_ENTERPRISENAME = "SP_ENTERPRISENAME";//营业执照名称
    private static final String SP_SUPERIOR_ENTERPRISE_NAME = "SUPERIOR_ENTERPRISE_NAME";//营业执照名称
    private static final String SP_DEVELOPMENTTYPE = "developmenttype";//用户类型
    private static final String SP_COMPANY_HEAD_IMG = "companyHeadimg";//用户头像

    private static LoginResponseBean loginInfo;

    private static final AccountHelper INSTANCE = new AccountHelper();

    public static AccountHelper getInstance() {
        return INSTANCE;
    }

    private AccountHelper() {

    }

    public static LoginResponseBean getLoginInfo() {
        return loginInfo;
    }

    public static void setLoginInfo(LoginResponseBean loginInfo) {
        AccountHelper.loginInfo = loginInfo;
    }

    public static boolean isLogin() {
        return !TextUtils.isEmpty(getToken());
    }

    public static void login(LoginResponseBean loginResponseBean) {
        SPUtils.getInstance(SP_NAME).put(SP_TOKEN, loginResponseBean.getTokenHead() + loginResponseBean.getToken());
        SPUtils.getInstance(SP_NAME).put(SP_USERID, loginResponseBean.getMap().getMbuser().getUserid());
        SPUtils.getInstance(SP_NAME).put(SP_LIVENAME, "jintianhezong_" + loginResponseBean.getMap().getMbuser().getUserid());
        SPUtils.getInstance(SP_NAME).put(SP_MOBILE, loginResponseBean.getMap().getMbuser().getUsermobile());
        SPUtils.getInstance(SP_NAME).put(SP_GENDER, loginResponseBean.getMap().getMbuser().getUsersex());
        SPUtils.getInstance(SP_NAME).put(SP_AVATAR, loginResponseBean.getMap().getMbuser().getUserpic());
        SPUtils.getInstance(SP_NAME).put(SP_NICKNAME, loginResponseBean.getMap().getMbuser().getUsernick());

        AccountHelper.setSpInvitiedcode(loginResponseBean.getMap().getMbuser().getInvitationcode());
        AccountHelper.setSpSUPERIORCOMPANY(loginResponseBean.getMap().getSuperiorcompany());
        AccountHelper.setSpSuperiorEnterpriseName(loginResponseBean.getMap().getSuperiorenterprisename());
        AccountHelper.setSpEnterprisename(loginResponseBean.getMap().getMbuser().getEnterprisename());
        AccountHelper.setSpDevelopmenttype(loginResponseBean.getMap().getMbuser().getDevelopmenttype());
        AccountHelper.setSpCompanyHeadimg(loginResponseBean.getMap().getCompanyHeadimg());
    }

    public static void saveOther(String open_id, String iconurl, String uid) {
        SPUtils.getInstance(SP_NAME).put(SP_OPEN_ID, open_id);
        SPUtils.getInstance(SP_NAME).put(SP_ICONURL, iconurl);
        SPUtils.getInstance(SP_NAME).put(SP_UID, uid);
    }

    public static void setCity(String city_id, String city_name, String latitude, String longitude) {
        SPUtils.getInstance(SP_NAME).put(SP_CITY_ID, city_id);
        SPUtils.getInstance(SP_NAME).put(SP_CITY_NAME, city_name);
        SPUtils.getInstance(SP_NAME).put(SP_LATITUDE, latitude);
        SPUtils.getInstance(SP_NAME).put(SP_LONGITUDE, longitude);
    }

    public static void setCity(String latitude, String longitude) {
        SPUtils.getInstance(SP_NAME).put(SP_LATITUDE, latitude);
        SPUtils.getInstance(SP_NAME).put(SP_LONGITUDE, longitude);
    }

    public static void setPwd(String pwd) {
        SPUtils.getInstance(SP_NAME).put(SP_PWD, pwd);
    }

    public static String getToken() {
        return SPUtils.getInstance(SP_NAME).getString(SP_TOKEN);
    }

    public static String getPwd() {
        return SPUtils.getInstance(SP_NAME).getString(SP_PWD);
    }

    public static String getCity_Id() {
        return SPUtils.getInstance(SP_NAME).getString(SP_CITY_ID);
    }

    public static String getCity_Name() {
        return SPUtils.getInstance(SP_NAME).getString(SP_CITY_NAME);
    }

    public static String getUserId() {
        return SPUtils.getInstance(SP_NAME).getString(SP_USERID);
    }

    public static String getLiveName() {
        return SPUtils.getInstance(SP_NAME).getString(SP_LIVENAME);
    }

    public static String getGender() {
        return SPUtils.getInstance(SP_NAME).getString(SP_GENDER);
    }

    public static String getAvatar() {
        return SPUtils.getInstance(SP_NAME).getString(SP_AVATAR);
    }

    public static String getTelephone() {
        return SPUtils.getInstance(SP_NAME).getString(SP_TELEPHONE);
    }

    public static String getOpenId() {
        return SPUtils.getInstance(SP_NAME).getString(SP_OPEN_ID);
    }

    public static String getIconurl() {
        return SPUtils.getInstance(SP_NAME).getString(SP_ICONURL);
    }

    public static String getUid() {
        return SPUtils.getInstance(SP_NAME).getString(SP_UID);
    }

    public static void setTelephone(String telephone) {
        SPUtils.getInstance(SP_NAME).put(SP_TELEPHONE, telephone);
    }


    public static void setSpInvitiedcode(String a) {
        SPUtils.getInstance(SP_NAME).put(SP_INVITIEDCODE, a);
    }

    public static String getSpInvitiedcode() {
        return SPUtils.getInstance(SP_NAME).getString(SP_INVITIEDCODE);
    }

    public static void setSpSUPERIORCOMPANY(String a) {
        SPUtils.getInstance(SP_NAME).put(SP_SUPERIORCOMPANY, a);
    }

    public static String getSpSUPERIORCOMPANY() {
        return SPUtils.getInstance(SP_NAME).getString(SP_SUPERIORCOMPANY);
    }

    public static void setSpEnterprisename(String a) {
        SPUtils.getInstance(SP_NAME).put(SP_ENTERPRISENAME, a);
    }

    public static String getSpEnterprisename() {
        return SPUtils.getInstance(SP_NAME).getString(SP_ENTERPRISENAME);
    }
    public static void setSpSuperiorEnterpriseName(String a) {
        SPUtils.getInstance(SP_NAME).put(SP_SUPERIOR_ENTERPRISE_NAME, a);
    }

    public static String getSpSuperiorEnterpriseName() {
        return SPUtils.getInstance(SP_NAME).getString(SP_SUPERIOR_ENTERPRISE_NAME);
    }

    public static void setSpDevelopmenttype(int a) {
        SPUtils.getInstance(SP_NAME).put(SP_DEVELOPMENTTYPE, a);
    }

    public static int getSpDevelopmenttype() {
        return SPUtils.getInstance(SP_NAME).getInt(SP_DEVELOPMENTTYPE);
    }

    public static void setSpCompanyHeadimg(String companyHeadimg) {
        SPUtils.getInstance(SP_NAME).put(SP_COMPANY_HEAD_IMG, companyHeadimg);
    }

    public static String getSpCompanyHeadimg() {
        return SPUtils.getInstance(SP_NAME).getString(SP_COMPANY_HEAD_IMG);
    }

    public static void setAvatar(String avatar) {
        SPUtils.getInstance(SP_NAME).put(SP_AVATAR, avatar);
    }

    public static String getNickname() {
        return SPUtils.getInstance(SP_NAME).getString(SP_NICKNAME);
    }

    public static void setNickname(String nickname) {
        SPUtils.getInstance(SP_NAME).put(SP_NICKNAME, nickname);
    }

    public static void setGender(String nickname) {
        SPUtils.getInstance(SP_NAME).put(SP_GENDER, nickname);
    }

    public static String getMobile() {
        return SPUtils.getInstance(SP_NAME).getString(SP_MOBILE);
    }

    public static void setMobile(String mobile) {
        SPUtils.getInstance(SP_NAME).put(SP_MOBILE, mobile);
    }

    public static void setSupplierId(String supplierId) {
        SPUtils.getInstance(SP_NAME).put(SP_SUPPLIERID, supplierId);
    }

    public static String getSupplierId() {
        return SPUtils.getInstance(SP_NAME).getString(SP_SUPPLIERID);
    }

    public static void setCityName(String cityName) {
        SPUtils.getInstance(SP_NAME).put(SP_CITY, cityName);
    }

    public static String getCityName() {
        return SPUtils.getInstance(SP_NAME).getString(SP_CITY);
    }


    public static void setSpUsertype(String usertype) {
        SPUtils.getInstance(SP_NAME).put(SP_USERTYPE, usertype);
    }

    public static String getSpUsertype() {
        return SPUtils.getInstance(SP_NAME).getString(SP_USERTYPE);
    }

    public static void logout() {

//        if (getInstance().onLogoutListener != null) {
//            getInstance().onLogoutListener.onPreLogout();
//        }

//        login(null, null, null, null, null, null, 0, 1);

        SPUtils.getInstance(SP_NAME).put(SP_TOKEN, (String) null);
        SPUtils.getInstance(SP_NAME).put(SP_USERID, (String) null);
        SPUtils.getInstance(SP_NAME).put(SP_LIVENAME, (String) null);
        SPUtils.getInstance(SP_NAME).put(SP_MOBILE, (String) null);
        SPUtils.getInstance(SP_NAME).put(SP_GENDER, (String) null);
        SPUtils.getInstance(SP_NAME).put(SP_AVATAR, (String) null);
        SPUtils.getInstance(SP_NAME).put(SP_NICKNAME, (String) null);

        setSupplierId(null);
        setCityName(null);
        setCity(null, null, null, null);
        setIdentity(0);
        setYunxinAccid("");
        setYunxinToken("");
        setSpDevelopmenttype(0);
        setSpEnterprisename("");
        setSpSUPERIORCOMPANY("");
        setSpSuperiorEnterpriseName("");
        if (getInstance().onLogoutListener != null) {
            getInstance().onLogoutListener.onLogout();
        }
    }

    public static boolean shouldLogin(Context context) {
        if (!isLogin()) {
            Starter.startLoginActivity(context, null);
//            context.startActivity(new Intent(context.getPackageName() + ".com.action.login"));
            return true;
        }
        return false;
    }

    public static int getIdentity() {
        return SPUtils.getInstance(SP_NAME).getInt(SP_IDENTITY, 1);
    }

    public static void setIdentity(int identity) {
        SPUtils.getInstance(SP_NAME).put(SP_IDENTITY, identity);
    }

    public static boolean isFirst() {
        return SPUtils.getInstance(SP_NAME).getBoolean(SP_FIRST);
    }

    public static void setFisrst() {
        SPUtils.getInstance(SP_NAME).put(SP_FIRST, true);
    }

    public static void setYunxinAccid(String accid) {
        SPUtils.getInstance(SP_NAME).put(SP_YUNXIN_ACCID, accid);
    }

    public static String getYunxinAccid() {
        return SPUtils.getInstance(SP_NAME)
                .getString(SP_YUNXIN_ACCID);
    }

    public static void setYunxinToken(String yunxinToken) {
        SPUtils.getInstance(SP_NAME).put(SP_YUNXIN_TOKEN, yunxinToken);
    }

    public static String getYunxinToken() {
        return SPUtils.getInstance(SP_NAME)
                .getString(SP_YUNXIN_TOKEN);
    }

//    public static int isVipLogin() {
//        return SPUtils.getInstance(SP_NAME).getInt(SP_ISVIP_LOGIN, 2);//默认游客
//    }
//
//    public static void setIsVipLogin(int isVip) {
//        SPUtils.getInstance(SP_NAME).put(SP_ISVIP_LOGIN, isVip);
//    }

    private OnLogoutListener onLogoutListener;

    public void setOnLogoutListener(OnLogoutListener onLogoutListener) {
        this.onLogoutListener = onLogoutListener;
    }

    public interface OnLogoutListener {
        void onPreLogout();

        void onLogout();
    }
}