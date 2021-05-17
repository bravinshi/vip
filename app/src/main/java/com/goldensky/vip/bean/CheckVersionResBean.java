package com.goldensky.vip.bean;

public class CheckVersionResBean {
    // ApiModelProperty(value = "版本描述")
    private String versionexplain;
    // ApiModelProperty(value = "下载地址")
    private String versionurl;
    // ApiModelProperty(value = "版本号")
    private String version;
    // ApiModelProperty(value = "版本内容")
    private String versionname;
    // ApiModelProperty(value = "更新状态 1.强制更新 2.选择更新 3.暂无更新包")
    private Integer versionstats;
    // ApiModelProperty(value = "App类型1.android 2. ios")
    private Integer apptype;

    public String getVersionexplain() {
        return versionexplain;
    }

    public void setVersionexplain(String versionexplain) {
        this.versionexplain = versionexplain;
    }

    public String getVersionurl() {
        return versionurl;
    }

    public void setVersionurl(String versionurl) {
        this.versionurl = versionurl;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public Integer getVersionstats() {
        return versionstats;
    }

    public void setVersionstats(Integer versionstats) {
        this.versionstats = versionstats;
    }

    public Integer getApptype() {
        return apptype;
    }

    public void setApptype(Integer apptype) {
        this.apptype = apptype;
    }
}
