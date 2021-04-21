package com.goldensky.vip.activity.mine.focus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.goldensky.vip.R;
import com.goldensky.vip.adapter.CircleFocusAdapter;
import com.goldensky.vip.adapter.GoodsFocusAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityCircleFocusBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class CircleFocusActivity extends BaseActivity<ActivityCircleFocusBinding, PublicViewModel> {
    private CircleFocusAdapter adapter;
    private List<Integer> list=new ArrayList<>();

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarCircleFocus.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.tabCircleFocus.addTab(mBinding.tabCircleFocus.newTab().setText(getString(R.string.text_all)));
        for (int i = 0; i < 10; i++) {
            list.add(R.mipmap.my_pic_quanziguanzhu2);
        }
        adapter=new CircleFocusAdapter(list);
        mBinding.rvGoodsFocus.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvGoodsFocus.setAdapter(adapter);
        View inflate = LayoutInflater.from(this).inflate(R.layout.rvhead_circle_focus,null);
        Glide.with(this).load(R.mipmap.my_pic_quanziguanzhu1).into((ImageView) inflate.findViewById(R.id.iv_head_rv));
        adapter.addHeaderView(inflate);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_circle_focus;
    }
}