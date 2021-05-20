package com.goldensky.vip.activity.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.CommentProductAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.CommentProductBean;
import com.goldensky.vip.databinding.ActivityCommentSuccessBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.order.OrderCommentViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static com.goldensky.vip.activity.order.OrderCommentActivity.PRODUCT_SECOND_ORDERID_KEY;
import static com.goldensky.vip.activity.order.OrderCommentActivity.PRODUCT_URL_KEY;

public class CommentSuccessActivity extends BaseActivity<ActivityCommentSuccessBinding, OrderCommentViewModel> {

    private List<CommentProductBean> mCommentProductBeans = new ArrayList<>();
    private CommentProductAdapter mCommentProductAdapter;

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarSuccess.setBackListener(v -> {
            finish();
        });
    }


    @Override
    public void observe() {
        mViewModel.commentProducts.observe(this, list -> {
            List<CommentProductBean> newList=new ArrayList<>();
            for (CommentProductBean commentProductBean : list) {
                if(commentProductBean.getIsevaluate()==0){
                    newList.add(commentProductBean);
                }
            }
            mCommentProductBeans.clear();
            mCommentProductBeans.addAll(newList);
            mCommentProductAdapter.notifyDataSetChanged();
        });
        mCommentProductAdapter = new CommentProductAdapter(mCommentProductBeans);
        mCommentProductAdapter.addChildClickViewIds(new int[]{R.id.tv_comment});
        mCommentProductAdapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
                if (view.getId() == R.id.tv_comment) {
                    CommentProductBean commentProductBean = mCommentProductBeans.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putString(PRODUCT_URL_KEY, commentProductBean.getInventorypic());
                    bundle.putString(PRODUCT_SECOND_ORDERID_KEY, commentProductBean.getSecondorderid());
                    Starter.startOrderCommentActivity(CommentSuccessActivity.this, bundle);
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mBinding.rvProducts.setLayoutManager(linearLayoutManager);
        mBinding.rvProducts.setAdapter(mCommentProductAdapter);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_comment_success;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.getVipOrderDetail(AccountHelper.getUserId());
//        mViewModel.getVipOrderDetail("365fc2e065164700a5b1b985e326a766");
    }
}