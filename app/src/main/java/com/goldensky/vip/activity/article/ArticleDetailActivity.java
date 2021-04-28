package com.goldensky.vip.activity.article;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.goldensky.framework.viewmodel.BaseViewModel;
import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityArticleDetailBinding;
import com.gyf.immersionbar.ImmersionBar;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/22 11:22
 * 包名： com.goldensky.vip.activity.article
 * 类说明：
 */
public class ArticleDetailActivity extends BaseActivity<ActivityArticleDetailBinding, BaseViewModel> {
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        ImmersionBar.with(this).statusBarDarkFont(true)
                .statusBarView(mBinding.vStatusBar).init();
        Intent intent = getIntent();
        if(intent!=null){
            int type = intent.getIntExtra("goods", 0);
            if(type==2){
                mBinding.ivOneDetail.setImageResource(R.mipmap.my_pic_wenzhangxiangqingfeiniu1);
                mBinding.ivTwoDetail.setImageResource(R.mipmap.my_pic_wenzhangxiangqingfeiniu2);
                mBinding.ivThreeDetail.setImageResource(R.mipmap.my_pic_wenzhangxiangqingfeiniu3);
            }else if(type==3){
                mBinding.ivOneDetail.setImageResource(R.mipmap.my_pic_wenzhangxiangqingbinggan1);
                mBinding.ivTwoDetail.setImageResource(R.mipmap.my_pic_wenzhangxiangqingbinggan2);
                mBinding.ivThreeDetail.setImageResource(R.mipmap.my_pic_wenzhangxiangqingbinggan3);
            }
        }
        mBinding.viewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_article_detail;
    }
}
