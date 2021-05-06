package com.goldensky.vip.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.goldensky.vip.R;
import com.goldensky.vip.bean.HomeBean;

import java.util.List;

public class HomeAdapter extends BaseMultiItemQuickAdapter<HomeBean, BaseViewHolder> {

    public static final int ITEM_TYPE_LB = 1; //轮播
    public static final int ITEM_TYPE_MSG = 2; //消息
    public static final int ITEM_TYPE_RMD = 3; //为你推荐
    public static final int ITEM_TYPE_JRBK = 4; //今日爆款
    public static final int ITEM_TYPE_YHZQ = 5; //优惠专区
    public static final int ITEM_TYPE_JTYX = 6; //金天优选

    private HomeProductAdapter mRmdProductAdapter;
    private HomeProductAdapter mTopProductAdapter;
    private HomeProductAdapter mMiddleProductAdapter;
    private HomeProductAdapter mBottomProductAdapter;
    private HomeProductAdapter mJrbkProductAdapter;

    public HomeAdapter(List<HomeBean> data) {
        super(data);
        addItemType(ITEM_TYPE_LB, R.layout.item_home_lb);
        addItemType(ITEM_TYPE_MSG, R.layout.item_home_msg);
        addItemType(ITEM_TYPE_RMD, R.layout.item_home_product);
        addItemType(ITEM_TYPE_JRBK, R.layout.item_home_product);
        addItemType(ITEM_TYPE_JTYX, R.layout.item_home_jtyx);
        addItemType(ITEM_TYPE_YHZQ, R.layout.item_home_yhzq);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean item) {

    }

    private void startProdutDetailActivity(Integer productId) {

    }
}
