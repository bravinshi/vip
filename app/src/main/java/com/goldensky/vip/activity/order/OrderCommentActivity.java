package com.goldensky.vip.activity.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.goldensky.vip.R;
import com.goldensky.vip.adapter.CommentImageAdapter;
import com.goldensky.vip.adapter.CommentStartAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityOrderCommentBinding;
import com.goldensky.vip.viewmodel.order.OrderCommentViewModel;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

public class OrderCommentActivity extends BaseActivity<ActivityOrderCommentBinding, OrderCommentViewModel> implements View.OnClickListener {

    private CommentStartAdapter mCommentStartAdapter;
    private CommentImageAdapter mCommentImageAdapter;
    private int currentStartIndex = -1;
    private String imgUrl = ""; //图片url

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarOrder.setBackListener( v -> { finish(); });
        Glide.with(this).load(imgUrl).into(mBinding.ivProduct);
    }

    @Override
    public void observe() {
        mViewModel.initImages();
        mCommentStartAdapter = new CommentStartAdapter(mViewModel.stars);
        mCommentStartAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter,  View view, int position) {
                if (position == currentStartIndex) return;
                currentStartIndex = position;
                mViewModel.updateStars(position);
                mCommentStartAdapter.notifyDataSetChanged();
            }
        });
        FlexboxLayoutManager lineLayoutManager = new FlexboxLayoutManager(this){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        lineLayoutManager.setFlexDirection(FlexDirection.ROW);
        mBinding.rvStar.setLayoutManager(lineLayoutManager);
        mBinding.rvStar.setAdapter(mCommentStartAdapter);


        mCommentImageAdapter = new CommentImageAdapter(mViewModel.images);
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
        mBinding.rvPics.setLayoutManager(flexboxLayoutManager);
        mBinding.rvPics.setAdapter(mCommentImageAdapter);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_commit:
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_comment;
    }
}