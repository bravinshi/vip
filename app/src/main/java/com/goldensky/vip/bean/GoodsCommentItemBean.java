package com.goldensky.vip.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.goldensky.vip.adapter.CommentAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/7 16:14
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class GoodsCommentItemBean implements MultiItemEntity {
    private List<CommentPicBean> busCommodityCommentPics;
    @SerializedName("commentid")
    private String commentId;
    @SerializedName("commodityid")
    private String commodityId;
    @SerializedName("evaluatecontent")
    private String evaluateContent;
    @SerializedName("evaluatehide")
    private Integer evaluateHide;
    @SerializedName("evaluatetype")
    private Integer evaluateType;
    @SerializedName("isgraphic")
    private Integer isGraphic;
    @SerializedName("isrecommend")
    private Integer isRecommend;
    private String userNick;
    private String userPic;
    @SerializedName("userid")
    private String userId;
    @SerializedName("creationtime")
    private Date creationTime;

    public List<CommentPicBean> getBusCommodityCommentPics() {
        return busCommodityCommentPics;
    }

    public void setBusCommodityCommentPics(List<CommentPicBean> busCommodityCommentPics) {
        this.busCommodityCommentPics = busCommodityCommentPics;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public Integer getEvaluateHide() {
        return evaluateHide;
    }

    public void setEvaluateHide(Integer evaluateHide) {
        this.evaluateHide = evaluateHide;
    }

    public Integer getEvaluateType() {
        return evaluateType;
    }

    public void setEvaluateType(Integer evaluateType) {
        this.evaluateType = evaluateType;
    }

    public Integer getIsGraphic() {
        return isGraphic;
    }

    public void setIsGraphic(Integer isGraphic) {
        this.isGraphic = isGraphic;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public int getItemType() {
        if (busCommodityCommentPics == null || busCommodityCommentPics.size() == 0) {
            return CommentAdapter.COMMENT_TYPE_TEXT;
        }
        return CommentAdapter.COMMENT_TYPE_PIC;
    }

    public static class CommentPicBean {
        @SerializedName("commentid")
        private String commentId;
        @SerializedName("commentpic")
        private String commentPic;
        @SerializedName("commentpicid")
        private String commentPicId;

        public String getCommentId() {
            return commentId;
        }

        public void setCommentId(String commentId) {
            this.commentId = commentId;
        }

        public String getCommentPic() {
            return commentPic;
        }

        public void setCommentPic(String commentPic) {
            this.commentPic = commentPic;
        }

        public String getCommentPicId() {
            return commentPicId;
        }

        public void setCommentPicId(String commentPicId) {
            this.commentPicId = commentPicId;
        }
    }
}
