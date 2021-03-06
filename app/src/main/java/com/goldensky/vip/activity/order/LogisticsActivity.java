package com.goldensky.vip.activity.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.goldensky.vip.R;
import com.goldensky.vip.adapter.LogisticsAdapter;
import com.goldensky.vip.api.PublicService;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.ExpressBean;
import com.goldensky.vip.bean.LogisticsBean;
import com.goldensky.vip.databinding.ActivityLogisticsBinding;
import com.goldensky.vip.utils.GlideRoundTransform;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.io.Serializable;

public class LogisticsActivity extends BaseActivity<ActivityLogisticsBinding, PublicViewModel> {
    private LogisticsBean logistics;
    private LogisticsAdapter adapter;
    private ExpressBean express;
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        Intent intent = getIntent();
        logistics = (LogisticsBean) intent.getSerializableExtra("logistics");
        express = (ExpressBean) intent.getSerializableExtra("express");
        String picUrl = intent.getStringExtra("pic");
        mBinding.topBarLogistics.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.rvLogistics.setLayoutManager(new LinearLayoutManager(this));
        adapter=new LogisticsAdapter(logistics.getData());
        mBinding.rvLogistics.setAdapter(adapter);
        View logisticsHeader = LayoutInflater.from(this).inflate(R.layout.header_logistics_layout,null);
        TextView number = logisticsHeader.findViewById(R.id.tv_number_logistics_header);
        TextView state = logisticsHeader.findViewById(R.id.tv_state_logistics_header);
        TextView name = logisticsHeader.findViewById(R.id.tv_expressname);
        ImageView pic = logisticsHeader.findViewById(R.id.iv_logistics_header);
        number.setText(express.getExpressnumber());
        name.setText(express.getExpressname());
        String[] logisticStatus={"??????","??????","??????","??????","??????","??????","??????","??????","?????????","","?????????","?????????","?????????","????????????","???????????????"};
        int status=0;
        if(logistics.getState()!=null){
            status=Integer.parseInt(logistics.getState());
        }else {
            status=8;
        }
        state.setText(logisticStatus[status]);
        Glide.with(this).load(picUrl).apply(new RequestOptions().transform(new GlideRoundTransform(this,16))).into(pic);
        adapter.addHeaderView(logisticsHeader);
        if(logistics.getData()==null){
            mBinding.includeLogistics.clEmptyLogistics.setVisibility(View.VISIBLE);
        }else {
            mBinding.includeLogistics.clEmptyLogistics.setVisibility(View.GONE);
        }
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_logistics;
    }
}