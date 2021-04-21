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
import com.goldensky.vip.adapter.RecentBrowseAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.RecentBrowseBean;
import com.goldensky.vip.databinding.ActivityRecentBrowseBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecentBrowseActivity extends BaseActivity<ActivityRecentBrowseBinding, PublicViewModel> {
    private RecentBrowseAdapter adapter;
    private List<RecentBrowseBean> list=new ArrayList<>();

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
        for (int i = 0; i < 15; i++) {
            list.add(new RecentBrowseBean(R.mipmap.my_pic_zuijinliulan,i));
        }
        adapter=new RecentBrowseAdapter(list);
        mBinding.rvRecentBrowse.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvRecentBrowse.setAdapter(adapter);

    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_recent_browse;
    }
}