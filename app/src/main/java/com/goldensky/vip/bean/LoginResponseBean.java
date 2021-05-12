package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/11 9:28
 * 包名： com.goldensky.together.bean
 * 类说明：
 */
public class LoginResponseBean implements Cloneable, Serializable {
    @SerializedName("expiresIn")
    private Integer expiresIn;
    @SerializedName("map")
    private Map map;
    @SerializedName("refreshToken")
    private String refreshToken;
    @SerializedName("token")
    private String token;
    @SerializedName("tokenHead")
    private String tokenHead;
    @SerializedName("userid")
    private Integer userid;
    @SerializedName("vipUser")
    private VipUser vipUser;

    public void clear() {
        expiresIn = null;
        map = null;
        refreshToken = null;
        token = null;
        tokenHead = null;
        userid = null;
        vipUser = null;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenHead() {
        return tokenHead;
    }

    public void setTokenHead(String tokenHead) {
        this.tokenHead = tokenHead;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public VipUser getVipUser() {
        return vipUser;
    }

    public void setVipUser(VipUser vipUser) {
        this.vipUser = vipUser;
    }

    public static class Map implements Serializable {
    }

    public static class VipUser implements Serializable {

        @SerializedName("developmenttype")
        private Integer developmenttype;
        @SerializedName("infotips")
        private Integer infotips;
        @SerializedName("invitationcode")
        private String invitationcode;
        @SerializedName("invitecode")
        private String invitecode;
        @SerializedName("isNew")
        private String isNew;
        @SerializedName("limitaccount")
        private Integer limitaccount;
        @SerializedName("newPwd")
        private String newPwd;
        @SerializedName("openid")
        private String openid;
        @SerializedName("phoneCode")
        private String phoneCode;
        @SerializedName("presettlementaccount")
        private Integer presettlementaccount;
        @SerializedName("securepassword")
        private String securepassword;
        @SerializedName("settlementaccount")
        private Integer settlementaccount;
        @SerializedName("supervipid")
        private String supervipid;
        @SerializedName("totalconsumption")
        private Integer totalconsumption;
        @SerializedName("unlimitedaccount")
        private Integer unlimitedaccount;
        @SerializedName("userban")
        private Integer userban;
        @SerializedName("userdel")
        private Integer userdel;
        @SerializedName("userid")
        private String userid;
        @SerializedName("usermobile")
        private String usermobile;
        @SerializedName("usernick")
        private String usernick;
        @SerializedName("userpassword")
        private String userpassword;
        @SerializedName("userpic")
        private String userpic;
        @SerializedName("userregisttime")
        private String userregisttime;
        @SerializedName("usersex")
        private Integer usersex;
        @SerializedName("usersuperiorid")
        private Integer usersuperiorid;
        @SerializedName("usersurplusintegral")
        private Integer usersurplusintegral;
        @SerializedName("viplevel")
        private String viplevel;
        public VipUser copy() {
            VipUser vipUser = new VipUser();

            vipUser.setDevelopmenttype(developmenttype);
            vipUser.setInfotips(infotips);
            vipUser.setInvitationcode(invitationcode);
            vipUser.setInvitecode(invitecode);
            vipUser.setIsNew(isNew);
            vipUser.setLimitaccount(limitaccount);
            vipUser.setNewPwd(newPwd);
            vipUser.setOpenid(openid);
            vipUser.setPhoneCode(phoneCode);
            vipUser.setPresettlementaccount(presettlementaccount);
            vipUser.setSecurepassword(securepassword);
            vipUser.setSettlementaccount(settlementaccount);
            vipUser.setSupervipid(supervipid);
            vipUser.setTotalconsumption(totalconsumption);
            vipUser.setUnlimitedaccount(unlimitedaccount);
            vipUser.setUserban(userban);
            vipUser.setUserdel(userdel);
            vipUser.setUserid(userid);
            vipUser.setUsermobile(usermobile);
            vipUser.setUserNick(usernick);
            vipUser.setUserpassword(userpassword);
            vipUser.setUserpic(userpic);
            vipUser.setUserregisttime(userregisttime);
            vipUser.setUsersex(usersex);
            vipUser.setUsersuperiorid(usersuperiorid);
            vipUser.setUsersurplusintegral(usersurplusintegral);
            vipUser.setViplevel(viplevel);
            return vipUser;
        }
        public Boolean isNewUser() {
            return "0".equals(isNew);
        }
        public Integer getDevelopmenttype() {
            return developmenttype;
        }

        public void setDevelopmenttype(Integer developmenttype) {
            this.developmenttype = developmenttype;
        }

        public Integer getInfotips() {
            return infotips;
        }

        public void setInfotips(Integer infotips) {
            this.infotips = infotips;
        }

        public String getInvitationcode() {
            return invitationcode;
        }

        public void setInvitationcode(String invitationcode) {
            this.invitationcode = invitationcode;
        }

        public String getInvitecode() {
            return invitecode;
        }

        public void setInvitecode(String invitecode) {
            this.invitecode = invitecode;
        }

        public String getIsNew() {
            return isNew;
        }

        public void setIsNew(String isNew) {
            this.isNew = isNew;
        }

        public Integer getLimitaccount() {
            return limitaccount;
        }

        public void setLimitaccount(Integer limitaccount) {
            this.limitaccount = limitaccount;
        }

        public String getNewPwd() {
            return newPwd;
        }

        public void setNewPwd(String newPwd) {
            this.newPwd = newPwd;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public String getPhoneCode() {
            return phoneCode;
        }

        public void setPhoneCode(String phoneCode) {
            this.phoneCode = phoneCode;
        }

        public Integer getPresettlementaccount() {
            return presettlementaccount;
        }

        public void setPresettlementaccount(Integer presettlementaccount) {
            this.presettlementaccount = presettlementaccount;
        }

        public String getSecurepassword() {
            return securepassword;
        }

        public void setSecurepassword(String securepassword) {
            this.securepassword = securepassword;
        }

        public Integer getSettlementaccount() {
            return settlementaccount;
        }

        public void setSettlementaccount(Integer settlementaccount) {
            this.settlementaccount = settlementaccount;
        }

        public String getSupervipid() {
            return supervipid;
        }

        public void setSupervipid(String supervipid) {
            this.supervipid = supervipid;
        }

        public Integer getTotalconsumption() {
            return totalconsumption;
        }

        public void setTotalconsumption(Integer totalconsumption) {
            this.totalconsumption = totalconsumption;
        }

        public Integer getUnlimitedaccount() {
            return unlimitedaccount;
        }

        public void setUnlimitedaccount(Integer unlimitedaccount) {
            this.unlimitedaccount = unlimitedaccount;
        }

        public Integer getUserban() {
            return userban;
        }

        public void setUserban(Integer userban) {
            this.userban = userban;
        }

        public Integer getUserdel() {
            return userdel;
        }

        public void setUserdel(Integer userdel) {
            this.userdel = userdel;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsermobile() {
            return usermobile;
        }

        public void setUsermobile(String usermobile) {
            this.usermobile = usermobile;
        }

        public String getUserNick() {
            return usernick;
        }

        public void setUserNick(String usernick) {
            this.usernick = usernick;
        }

        public String getUserpassword() {
            return userpassword;
        }

        public void setUserpassword(String userpassword) {
            this.userpassword = userpassword;
        }

        public String getUserpic() {
            return userpic;
        }

        public void setUserpic(String userpic) {
            this.userpic = userpic;
        }

        public String getUserregisttime() {
            return userregisttime;
        }

        public void setUserregisttime(String userregisttime) {
            this.userregisttime = userregisttime;
        }

        public Integer getUsersex() {
            return usersex;
        }

        public void setUsersex(Integer usersex) {
            this.usersex = usersex;
        }

        public Integer getUsersuperiorid() {
            return usersuperiorid;
        }

        public void setUsersuperiorid(Integer usersuperiorid) {
            this.usersuperiorid = usersuperiorid;
        }

        public Integer getUsersurplusintegral() {
            return usersurplusintegral;
        }

        public void setUsersurplusintegral(Integer usersurplusintegral) {
            this.usersurplusintegral = usersurplusintegral;
        }

        public String getViplevel() {
            return viplevel;
        }

        public void setViplevel(String viplevel) {
            this.viplevel = viplevel;
        }
    }



}
