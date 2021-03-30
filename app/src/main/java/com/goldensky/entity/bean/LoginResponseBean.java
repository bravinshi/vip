package com.goldensky.entity.bean;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/11 9:28
 * 包名： com.goldensky.together.bean
 * 类说明：
 */
public class LoginResponseBean {

    private String superiorcompany;//上一级公司名称
    private String superiorenterprisename;
    private UserBean mbuser;
    private String token;
    private String companyHeadimg;//公司头像名称

    public String getSuperiorcompany() {
        return superiorcompany;
    }

    public void setSuperiorcompany(String superiorcompany) {
        this.superiorcompany = superiorcompany;
    }

    public String getSuperiorenterprisename() {
        return superiorenterprisename;
    }

    public void setSuperiorenterprisename(String superiorenterprisename) {
        this.superiorenterprisename = superiorenterprisename;
    }

    public UserBean getMbuser() {
        return mbuser;
    }

    public void setMbuser(UserBean mbuser) {
        this.mbuser = mbuser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCompanyHeadimg() {
        return companyHeadimg;
    }

    public void setCompanyHeadimg(String companyHeadimg) {
        this.companyHeadimg = companyHeadimg;
    }

    public static class UserBean {
        /**
         * userid : 299
         * usernick : jintian_XEOGA5
         * usermobile : 13315487933
         * userpassword : null
         * userpic : null
         * usersex : null
         * userregisttime : 1602140339000
         * usergrade : 0
         * usertotalintegral : 0
         * usersurplusintegral : 0
         * userip : null
         * usercurrentlogintime : 1602140356000
         * usersuperiorid : null
         * easemobuuid : null
         * infotips : 1
         * userban : 0
         * userdel : 0
         * easemobid : null
         * isnew : 0
         * invitationcode : XSILYARV
         * uservip : 0
         * userentitytype : 0
         * developmenttype : 0
         * usertype : 0
         * invitecode : null
         * islegalperson : 0
         * enterprisename : 北京日益通速递有限责任公司
         * egalpersonname : 郑彩英
         * registrationnumber : 110105001842873
         * businesslicense : https://jintianhezong-1302324525.cos.ap-nanjing.myqcloud.com/IMG_20201008_145924.jpg
         */

        private String commodityperformance;// 代理商品业绩
        private String authorizationcertificate;// 授权证明
        private String userid;
        private String usernick;
        private String usermobile;
        private String userpassword;
        private String userpic;
        private String usersex;
        private long userregisttime;
        private int usergrade;
        private Double usertotalintegral;
        private Double usersurplusintegral;
        private String userip;
        private long usercurrentlogintime;
        private String usersuperiorid;
        private String easemobuuid;
        private int infotips;
        private int userban;
        private int userdel;
        private String easemobid;
        private int isnew;
        private String invitationcode;
        private int uservip;
        private int userentitytype;
        private int developmenttype;
        private int usertype;
        private String invitecode;
        private int islegalperson;
        private String enterprisename;
        private String egalpersonname;
        private String registrationnumber;
        private String businesslicense;// 营业执照
        private Integer isofficial;
        private String identitycardpositive;
        private String identitycardback;
        private String securepassword;
        private Double limitaccount;
        private Double nonagencyachievement;
        private Double presettlementaccount;
        private Double settlementaccount;
        private Double unlimitedaccount;
        private String isfictitious;// 用户是孵化用户

        public String getCommodityperformance() {
            return commodityperformance;
        }

        public void setCommodityperformance(String commodityperformance) {
            this.commodityperformance = commodityperformance;
        }

        public String getAuthorizationcertificate() {
            return authorizationcertificate;
        }

        public void setAuthorizationcertificate(String authorizationcertificate) {
            this.authorizationcertificate = authorizationcertificate;
        }

        public String getSecurepassword() {
            return securepassword;
        }

        public void setSecurepassword(String securepassword) {
            this.securepassword = securepassword;
        }

        public Double getLimitaccount() {
            return limitaccount;
        }

        public void setLimitaccount(Double limitaccount) {
            this.limitaccount = limitaccount;
        }

        public Double getNonagencyachievement() {
            return nonagencyachievement;
        }

        public void setNonagencyachievement(Double nonagencyachievement) {
            this.nonagencyachievement = nonagencyachievement;
        }

        public Double getPresettlementaccount() {
            return presettlementaccount;
        }

        public void setPresettlementaccount(Double presettlementaccount) {
            this.presettlementaccount = presettlementaccount;
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

        public String getIsfictitious() {
            return isfictitious;
        }

        public void setIsfictitious(String isfictitious) {
            this.isfictitious = isfictitious;
        }

        public String getIdentitycardpositive() {
            return identitycardpositive;
        }

        public void setIdentitycardpositive(String identitycardpositive) {
            this.identitycardpositive = identitycardpositive;
        }

        public String getIdentitycardback() {
            return identitycardback;
        }

        public void setIdentitycardback(String identitycardback) {
            this.identitycardback = identitycardback;
        }

        public Integer getIsofficial() {
            return isofficial;
        }

        public void setIsofficial(Integer isofficial) {
            this.isofficial = isofficial;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getUsernick() {
            return usernick;
        }

        public void setUsernick(String usernick) {
            this.usernick = usernick;
        }

        public String getUsermobile() {
            return usermobile;
        }

        public void setUsermobile(String usermobile) {
            this.usermobile = usermobile;
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

        public String getUsersex() {
            return usersex;
        }

        public void setUsersex(String usersex) {
            this.usersex = usersex;
        }

        public long getUserregisttime() {
            return userregisttime;
        }

        public void setUserregisttime(long userregisttime) {
            this.userregisttime = userregisttime;
        }

        public int getUsergrade() {
            return usergrade;
        }

        public void setUsergrade(int usergrade) {
            this.usergrade = usergrade;
        }

        public Double getUsertotalintegral() {
            return usertotalintegral;
        }

        public void setUsertotalintegral(Double usertotalintegral) {
            this.usertotalintegral = usertotalintegral;
        }

        public Double getUsersurplusintegral() {
            return usersurplusintegral;
        }

        public void setUsersurplusintegral(Double usersurplusintegral) {
            this.usersurplusintegral = usersurplusintegral;
        }

        public String getUserip() {
            return userip;
        }

        public void setUserip(String userip) {
            this.userip = userip;
        }

        public long getUsercurrentlogintime() {
            return usercurrentlogintime;
        }

        public void setUsercurrentlogintime(long usercurrentlogintime) {
            this.usercurrentlogintime = usercurrentlogintime;
        }

        public String getUsersuperiorid() {
            return usersuperiorid;
        }

        public void setUsersuperiorid(String usersuperiorid) {
            this.usersuperiorid = usersuperiorid;
        }

        public String getEasemobuuid() {
            return easemobuuid;
        }

        public void setEasemobuuid(String easemobuuid) {
            this.easemobuuid = easemobuuid;
        }

        public int getInfotips() {
            return infotips;
        }

        public void setInfotips(int infotips) {
            this.infotips = infotips;
        }

        public int getUserban() {
            return userban;
        }

        public void setUserban(int userban) {
            this.userban = userban;
        }

        public int getUserdel() {
            return userdel;
        }

        public void setUserdel(int userdel) {
            this.userdel = userdel;
        }

        public String getEasemobid() {
            return easemobid;
        }

        public void setEasemobid(String easemobid) {
            this.easemobid = easemobid;
        }

        public int getIsnew() {
            return isnew;
        }

        public void setIsnew(int isnew) {
            this.isnew = isnew;
        }

        public String getInvitationcode() {
            return invitationcode;
        }

        public void setInvitationcode(String invitationcode) {
            this.invitationcode = invitationcode;
        }

        public int getUservip() {
            return uservip;
        }

        public void setUservip(int uservip) {
            this.uservip = uservip;
        }

        public int getUserentitytype() {
            return userentitytype;
        }

        public void setUserentitytype(int userentitytype) {
            this.userentitytype = userentitytype;
        }

        public int getDevelopmenttype() {
            return developmenttype;
        }

        public void setDevelopmenttype(int developmenttype) {
            this.developmenttype = developmenttype;
        }

        public int getUsertype() {
            return usertype;
        }

        public void setUsertype(int usertype) {
            this.usertype = usertype;
        }

        public String getInvitecode() {
            return invitecode;
        }

        public void setInvitecode(String invitecode) {
            this.invitecode = invitecode;
        }

        public int getIslegalperson() {
            return islegalperson;
        }

        public void setIslegalperson(int islegalperson) {
            this.islegalperson = islegalperson;
        }

        public String getEnterprisename() {
            return enterprisename;
        }

        public void setEnterprisename(String enterprisename) {
            this.enterprisename = enterprisename;
        }

        public String getEgalpersonname() {
            return egalpersonname;
        }

        public void setEgalpersonname(String egalpersonname) {
            this.egalpersonname = egalpersonname;
        }

        public String getRegistrationnumber() {
            return registrationnumber;
        }

        public void setRegistrationnumber(String registrationnumber) {
            this.registrationnumber = registrationnumber;
        }

        public String getBusinesslicense() {
            return businesslicense;
        }

        public void setBusinesslicense(String businesslicense) {
            this.businesslicense = businesslicense;
        }
    }
}
