package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/28 17:30
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class UpdateVipUserReqBean {
    private String userId;
    private VipUser vipUser;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public VipUser getVipUser() {
        return vipUser;
    }

    public void setVipUser(VipUser vipUser) {
        this.vipUser = vipUser;
    }

    public static class VipUser {
        @SerializedName("infotips")
        private Integer infoTips;
        @SerializedName("userban")
        private Integer userBan;
        @SerializedName("userdel")
        private Integer userDel;
        @SerializedName("usersuperiorid")
        private Integer userSuperiorId;
        private String isNew;
        @SerializedName("supervipid")
        private String superVipId;
        @SerializedName("userid")
        private String userId;
        @SerializedName("usermobile")
        private String userMobile;
        @SerializedName("limitaccount")
        private Double limitAccount;
        @SerializedName("presettlementaccount")
        private Double preSettlementAccount;
        @SerializedName("settlementaccount")
        private Double settlementAccount;
        @SerializedName("unlimitedaccount")
        private Double unlimitedAccount;
        @SerializedName("usersurplusintegral")
        private Double userSurplusIntegral;
        @SerializedName("userregisttime")
        private Date userRegisterTime;
        @SerializedName("userpassword")
        private String userPassword;

        public Integer getInfoTips() {
            return infoTips;
        }

        public void setInfoTips(Integer infoTips) {
            this.infoTips = infoTips;
        }

        public Integer getUserBan() {
            return userBan;
        }

        public void setUserBan(Integer userBan) {
            this.userBan = userBan;
        }

        public Integer getUserDel() {
            return userDel;
        }

        public void setUserDel(Integer userDel) {
            this.userDel = userDel;
        }

        public Integer getUserSuperiorId() {
            return userSuperiorId;
        }

        public void setUserSuperiorId(Integer userSuperiorId) {
            this.userSuperiorId = userSuperiorId;
        }

        public String getIsNew() {
            return isNew;
        }

        public void setIsNew(String isNew) {
            this.isNew = isNew;
        }

        public String getSuperVipId() {
            return superVipId;
        }

        public void setSuperVipId(String superVipId) {
            this.superVipId = superVipId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserMobile() {
            return userMobile;
        }

        public void setUserMobile(String userMobile) {
            this.userMobile = userMobile;
        }

        public Double getLimitAccount() {
            return limitAccount;
        }

        public void setLimitAccount(Double limitAccount) {
            this.limitAccount = limitAccount;
        }

        public Double getPreSettlementAccount() {
            return preSettlementAccount;
        }

        public void setPreSettlementAccount(Double preSettlementAccount) {
            this.preSettlementAccount = preSettlementAccount;
        }

        public Double getSettlementAccount() {
            return settlementAccount;
        }

        public void setSettlementAccount(Double settlementAccount) {
            this.settlementAccount = settlementAccount;
        }

        public Double getUnlimitedAccount() {
            return unlimitedAccount;
        }

        public void setUnlimitedAccount(Double unlimitedAccount) {
            this.unlimitedAccount = unlimitedAccount;
        }

        public Double getUserSurplusIntegral() {
            return userSurplusIntegral;
        }

        public void setUserSurplusIntegral(Double userSurplusIntegral) {
            this.userSurplusIntegral = userSurplusIntegral;
        }

        public Date getUserRegisterTime() {
            return userRegisterTime;
        }

        public void setUserRegisterTime(Date userRegisterTime) {
            this.userRegisterTime = userRegisterTime;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }
    }
}
