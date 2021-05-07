package com.goldensky.vip.activity.mine.focus;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityCircleFocusBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class CircleFocusActivity extends BaseActivity<ActivityCircleFocusBinding, PublicViewModel> implements View.OnClickListener {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarCircleFocus.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.tabCircleFocus.addTab(mBinding.tabCircleFocus.newTab().setText(getString(R.string.text_all)));


    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_circle_focus;
    }

    @Override
    public void onClick(View v) {
        Starter.startCustomerServiceActivity(this,null);
    }
}