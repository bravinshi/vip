package com.goldensky.vip.bean;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/11 9:28
 * 包名： com.goldensky.together.bean
 * 类说明：
 */
public class LoginResponseBean {

    private long expiresIn;
    private UserMap map;
    private String refreshToken;
    private String token;
    private String tokenHead;
    private String userid;

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public UserMap getMap() {
        return map;
    }

    public void setMap(UserMap map) {
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public static class UserMap {
        private String superiorenterprisename;
        private String companyHeadimg;
        private String superiorcompany;
        private String token;
        private LoginUserBean mbuser;

        public String getSuperiorenterprisename() {
            return superiorenterprisename;
        }

        public void setSuperiorenterprisename(String superiorenterprisename) {
            this.superiorenterprisename = superiorenterprisename;
        }

        public String getCompanyHeadimg() {
            return companyHeadimg;
        }

        public void setCompanyHeadimg(String companyHeadimg) {
            this.companyHeadimg = companyHeadimg;
        }

        public String getSuperiorcompany() {
            return superiorcompany;
        }

        public void setSuperiorcompany(String superiorcompany) {
            this.superiorcompany = superiorcompany;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public LoginUserBean getMbuser() {
            return mbuser;
        }

        public void setMbuser(LoginUserBean mbuser) {
            this.mbuser = mbuser;
        }
    }

    public static class LoginUserBean {
        private String authorizationcertificate;
        private String businesslicense;
        private Double commodityperformance;
        private int developmenttype;
        private String easemobid;
        private String easemobuuid;
        private String egalpersonname;
        private String enterprisename;
        private String identitycardback;
        private String identitycardpositive;
        private int infotips;
        private String invitationcode;
        private String invitecode;
        private String isfictitious;
        private int islegalperson;
        private int isnew;
        private Double limitaccount;
        private int nonagencyachievement;
        private Double presettlementaccount;
        private String registrationnumber;
        private String securepassword;
        private Double settlementaccount;
        private Double unlimitedaccount;
        private int userban;
        private String usercurrentlogintime;
        private int userdel;
        private int userentitytype;
        private int usergrade;
        private int userid;
        private String userip;
        private String usermobile;
        private String usernick;
        private String userpassword;
        private String userpic;
        private String userregisttime;
        private String usersex;
        private String usersuperiorid;
        private Double usersurplusintegral;
        private Double usertotalintegral;
        private int usertype;
        private int uservip;

        public String getAuthorizationcertificate() {
            return authorizationcertificate;
        }

        public void setAuthorizationcertificate(String authorizationcertificate) {
            this.authorizationcertificate = authorizationcertificate;
        }

        public String getBusinesslicense() {
            return businesslicense;
        }

        public void setBusinesslicense(String businesslicense) {
            this.businesslicense = businesslicense;
        }

        public Double getCommodityperformance() {
            return commodityperformance;
        }

        public void setCommodityperformance(Double commodityperformance) {
            this.commodityperformance = commodityperformance;
        }

        public int getDevelopmenttype() {
            return developmenttype;
        }

        public void setDevelopmenttype(int developmenttype) {
            this.developmenttype = developmenttype;
        }

        public String getEasemobid() {
            return easemobid;
        }

        public void setEasemobid(String easemobid) {
            this.easemobid = easemobid;
        }

        public String getEasemobuuid() {
            return easemobuuid;
        }

        public void setEasemobuuid(String easemobuuid) {
            this.easemobuuid = easemobuuid;
        }

        public String getEgalpersonname() {
            return egalpersonname;
        }

        public void setEgalpersonname(String egalpersonname) {
            this.egalpersonname = egalpersonname;
        }

        public String getEnterprisename() {
            return enterprisename;
        }

        public void setEnterprisename(String enterprisename) {
            this.enterprisename = enterprisename;
        }

        public String getIdentitycardback() {
            return identitycardback;
        }

        public void setIdentitycardback(String identitycardback) {
            this.identitycardback = identitycardback;
        }

        public String getIdentitycardpositive() {
            return identitycardpositive;
        }

        public void setIdentitycardpositive(String identitycardpositive) {
            this.identitycardpositive = identitycardpositive;
        }

        public int getInfotips() {
            return infotips;
        }

        public void setInfotips(int infotips) {
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

        public String getIsfictitious() {
            return isfictitious;
        }

        public void setIsfictitious(String isfictitious) {
            this.isfictitious = isfictitious;
        }

        public int getIslegalperson() {
            return islegalperson;
        }

        public void setIslegalperson(int islegalperson) {
            this.islegalperson = islegalperson;
        }

        public int getIsnew() {
            return isnew;
        }

        public void setIsnew(int isnew) {
            this.isnew = isnew;
        }

        public Double getLimitaccount() {
            return limitaccount;
        }

        public void setLimitaccount(Double limitaccount) {
            this.limitaccount = limitaccount;
        }

        public int getNonagencyachievement() {
            return nonagencyachievement;
        }

        public void setNonagencyachievement(int nonagencyachievement) {
            this.nonagencyachievement = nonagencyachievement;
        }

        public Double getPresettlementaccount() {
            return presettlementaccount;
        }

        public void setPresettlementaccount(Double presettlementaccount) {
            this.presettlementaccount = presettlementaccount;
        }

        public String getRegistrationnumber() {
            return registrationnumber;
        }

        public void setRegistrationnumber(String registrationnumber) {
            this.registrationnumber = registrationnumber;
        }

        public String getSecurepassword() {
            return securepassword;
        }

        public void setSecurepassword(String securepassword) {
            this.securepassword = securepassword;
        }

        public Double getSettlementaccount() {
            return settlementaccount;
        }

        public void setSettlementaccount(Double settlementaccount) {
            this.settlementaccount = settlementaccount;
        }

        public Double getUnlimitedaccount() {
            return unlimitedaccount;
        }

        public void setUnlimitedaccount(Double unlimitedaccount) {
            this.unlimitedaccount = unlimitedaccount;
        }

        public int getUserban() {
            return userban;
        }

        public void setUserban(int userban) {
            this.userban = userban;
        }

        public String getUsercurrentlogintime() {
            return usercurrentlogintime;
        }

        public void setUsercurrentlogintime(String usercurrentlogintime) {
            this.usercurrentlogintime = usercurrentlogintime;
        }

        public int getUserdel() {
            return userdel;
        }

        public void setUserdel(int userdel) {
            this.userdel = userdel;
        }

        public int getUserentitytype() {
            return userentitytype;
        }

        public void setUserentitytype(int userentitytype) {
            this.userentitytype = userentitytype;
        }

        public int getUsergrade() {
            return usergrade;
        }

        public void setUsergrade(int usergrade) {
            this.usergrade = usergrade;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getUserip() {
            return userip;
        }

        public void setUserip(String userip) {
            this.userip = userip;
        }

        public String getUsermobile() {
            return usermobile;
        }

        public void setUsermobile(String usermobile) {
            this.usermobile = usermobile;
        }

        public String getUsernick() {
            return usernick;
        }

        public void setUsernick(String usernick) {
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

        public String getUsersex() {
            return usersex;
        }

        public void setUsersex(String usersex) {
            this.usersex = usersex;
        }

        public String getUsersuperiorid() {
            return usersuperiorid;
        }

        public void setUsersuperiorid(String usersuperiorid) {
            this.usersuperiorid = usersuperiorid;
        }

        public Double getUsersurplusintegral() {
            return usersurplusintegral;
        }

        public void setUsersurplusintegral(Double usersurplusintegral) {
            this.usersurplusintegral = usersurplusintegral;
        }

        public Double getUsertotalintegral() {
            return usertotalintegral;
        }

        public void setUsertotalintegral(Double usertotalintegral) {
            this.usertotalintegral = usertotalintegral;
        }

        public int getUsertype() {
            return usertype;
        }

        public void setUsertype(int usertype) {
            this.usertype = usertype;
        }

        public int getUservip() {
            return uservip;
        }

        public void setUservip(int uservip) {
            this.uservip = uservip;
        }
    }
}
