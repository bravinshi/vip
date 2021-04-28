package com.goldensky.vip.activity.mine.focus;

import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityRecentBrowseBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecentBrowseActivity extends BaseActivity<ActivityRecentBrowseBinding, PublicViewModel> implements View.OnClickListener {


    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarRecentBrowse.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.topBarRecentBrowse.setRightListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recent_browse;
    }

    @Override
    public void onClick(View v) {
        Starter.startCustomerServiceActivity(this,null);
    }
}