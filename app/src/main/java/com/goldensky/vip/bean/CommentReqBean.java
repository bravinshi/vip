package com.goldensky.vip.bean;

import java.io.Serializable;

public class CommentReqBean implements Serializable {

    /**
     * evaluatecontent :
     * evaluatepic :
     * evaluatescore :
     * l_uid :
     * secondorderid :
     */
    private String evaluatecontent;
    private String evaluatepic;
    private String evaluatescore;
    private String l_uid;
    private String secondorderid;

    public CommentReqBean(String evaluatecontent, String evaluatepic, String evaluatescore, String l_uid, String secondorderid) {
        this.evaluatecontent = evaluatecontent;
        this.evaluatepic = evaluatepic;
        this.evaluatescore = evaluatescore;
        this.l_uid = l_uid;
        this.secondorderid = secondorderid;
    }

    public String getEvaluatecontent() {
        return evaluatecontent;
    }

    public void setEvaluatecontent(String evaluatecontent) {
        this.evaluatecontent = evaluatecontent;
    }

    public String getEvaluatepic() {
        return evaluatepic;
    }

    public void setEvaluatepic(String evaluatepic) {
        this.evaluatepic = evaluatepic;
    }

    public String getEvaluatescore() {
        return evaluatescore;
    }

    public void setEvaluatescore(String evaluatescore) {
        this.evaluatescore = evaluatescore;
    }

    public String getL_uid() {
        return l_uid;
    }

    public void setL_uid(String l_uid) {
        this.l_uid = l_uid;
    }

    public String getSecondorderid() {
        return secondorderid;
    }

    public void setSecondorderid(String secondorderid) {
        this.secondorderid = secondorderid;
    }
}

