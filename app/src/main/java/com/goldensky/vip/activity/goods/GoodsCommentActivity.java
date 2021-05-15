package com.goldensky.vip.activity.goods;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.goldensky.framework.bean.NetResponse;
import com.goldensky.vip.R;
import com.goldensky.vip.adapter.CommentAdapter;
import com.goldensky.vip.adapter.CommentFilterAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.base.error.FailCallback;
import com.goldensky.vip.bean.CommentFilterBean;
import com.goldensky.vip.bean.GoodsCommentItemBean;
import com.goldensky.vip.bean.GoodsCommentResBean;
import com.goldensky.vip.databinding.ActivityGoodsCommentBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/14 14:21
 * 包名： com.goldensky.vip.activity.goods
 * 类说明：
 */
public class GoodsCommentActivity extends BaseActivity<ActivityGoodsCommentBinding,
        PublicViewModel> {
    public List<CommentFilterBean> filterBeans = new ArrayList<>();
    public List<GoodsCommentItemBean> commentLis = new ArrayList<>();
    private CommentFilterAdapter mFilterAdapter;
    private CommentAdapter mCommentAdapter;
    private int currentPosition = 0;
    private String commodityId = "";
    private int currentPage = 1;
    private int pageSize = 15;
    private Integer evaluateType = null;  //1:好 2:中 3:差 4:是否有图,不传为全部

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        ImmersionBar.with(this).statusBarDarkFont(true)
                .statusBarView(mBinding.vStatusBar).init();
        mBinding.tabBar.setBackListener(v -> { finish();});
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            commodityId = bundle.getString(GoodsDetailActivity.KEY_GOODS_ID, "");
        }
//        commodityId = "139";
        requestComments();
    }

    @Override
    public void observe() {
        initFilterBeans();
        mFilterAdapter = new CommentFilterAdapter(filterBeans);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this) {
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        mBinding.rvFilter.setLayoutManager(flexboxLayoutManager);
        mBinding.rvFilter.setAdapter(mFilterAdapter);
        mFilterAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter,  View view, int position) {
                if (position == currentPosition) return;
                currentPosition = position;
                updateFilterBeans(position);
                mFilterAdapter.notifyDataSetChanged();
                currentPage = 1;
                if (commentLis.size() > 0) {
                    commentLis.clear();
                    mCommentAdapter.notifyDataSetChanged();
                }
                evaluateType = (position == 0) ? null : position;
                mBinding.swRefresh.resetNoMoreData();
                requestComments();
            }
        });

        mCommentAdapter = new CommentAdapter(commentLis);
        mBinding.rvComments.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinding.rvComments.setAdapter(mCommentAdapter);

        //评论数据
        mViewModel.goodsCommentLive.observe(this, goodsCommentResBean -> {
            updateCommentsFilters(goodsCommentResBean);
            if (goodsCommentResBean.getCommentList() != null && goodsCommentResBean.getCommentList().size() > 0) {
                mBinding.swRefresh.finishLoadMore();
                commentLis.addAll(goodsCommentResBean.getCommentList());
                mCommentAdapter.notifyDataSetChanged();
                currentPage ++;
            } else {
                mBinding.swRefresh.finishLoadMoreWithNoMoreData();
            }

            updateViews();
        });

        mBinding.swRefresh.setEnableRefresh(false);
//        mBinding.swRefresh.setEnableLoadMoreWhenContentNotFull(false);
        mBinding.swRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                requestComments();
            }
        });
    }


    private void updateCommentsFilters(GoodsCommentResBean goodsCommentResBean) {
        for (int i = 0; i < filterBeans.size(); i ++) {
            CommentFilterBean filterBean = filterBeans.get(i);
            if (i == 0) {
                Integer count = 0;
                if (goodsCommentResBean.getPraiseCount() != null) {
                    count += goodsCommentResBean.getPraiseCount();
                }

                if (goodsCommentResBean.getTotalCount() != null) {
                    count += goodsCommentResBean.getTotalCount();
                }

                if (goodsCommentResBean.getDifferenceCount() != null) {
                    count += goodsCommentResBean.getDifferenceCount();
                }
//
//                if (goodsCommentResBean.getIsPicCount() != null) {
//                    count += goodsCommentResBean.getIsPicCount();
//                }

                filterBean.setCount(count);
            } else if (i == 1) {
                filterBean.setCount(goodsCommentResBean.getPraiseCount());
            } else if ( i == 2) {
                filterBean.setCount(goodsCommentResBean.getTotalCount());
            } else if ( i == 3) {
                filterBean.setCount(goodsCommentResBean.getDifferenceCount());
            } else if ( i == 4) {
                filterBean.setCount(goodsCommentResBean.getIsPicCount());
            }
        }
        mFilterAdapter.notifyDataSetChanged();
    }

    //请求评论
    private void requestComments() {
        mViewModel.getGoodsComment(currentPage, pageSize, commodityId, evaluateType, new FailCallback() {
            @Override
            public void onFail(NetResponse netResponse) {
                mBinding.swRefresh.finishLoadMore();
                updateViews();
            }
        });
    }

    private void updateViews() {
        if (commentLis.size() > 0) {
            mBinding.rvComments.setVisibility(View.VISIBLE);
            mBinding.clNoData.setVisibility(View.GONE);
        } else {
            mBinding.rvComments.setVisibility(View.GONE);
            mBinding.clNoData.setVisibility(View.VISIBLE);
        }
    }

    public void initFilterBeans() {
        CommentFilterBean allBean = new CommentFilterBean("全部", true, 0);
        CommentFilterBean goodBean = new CommentFilterBean("好评", false, 0);
        CommentFilterBean middleBean = new CommentFilterBean("中评", false, 0);
        CommentFilterBean badBean = new CommentFilterBean("差评", false, 0);
        CommentFilterBean picBean = new CommentFilterBean("有图", false, 0);
        filterBeans.add(allBean);
        filterBeans.add(goodBean);
        filterBeans.add(middleBean);
        filterBeans.add(badBean);
        filterBeans.add(picBean);
    }

    public void updateFilterBeans(int position) {
        for (int i = 0; i < filterBeans.size(); i++) {
            CommentFilterBean filterBean = filterBeans.get(i);
            if (i == position) {
                filterBean.setSelected(true);
            } else {
                filterBean.setSelected(false);
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_comment;
    }
}
