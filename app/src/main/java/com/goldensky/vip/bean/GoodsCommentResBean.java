package com.goldensky.vip.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/7 16:14
 * 包名： com.goldensky.vip.bean
 * 类说明：
 */
public class GoodsCommentResBean {
    private List<GoodsCommentItemBean> commentList;
    private Integer differenceCount;
    private Integer praiseCount;
    @SerializedName("zhongCount")
    private Integer totalCount;
}
