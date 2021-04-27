package com.goldensky.vip.activity.mine.mall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.adapter.GoodsFocusAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityPromoteBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class PromoteActivity extends BaseActivity<ActivityPromoteBinding, PublicViewModel> {
    private GoodsFocusAdapter adapter;
    private List<Integer> list=new ArrayList<>();

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        list.add(R.mipmap.my_pic_tuiguangxiangqing2);
        list.add(R.mipmap.my_pic_tuiguangxiangqing3);
        list.add(R.mipmap.my_pic_tuiguangxiangqing4);
        adapter=new GoodsFocusAdapter(list);
        mBinding.rvPromote.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvPromote.setAdapter(adapter);
        mBinding.topBarPromote.setBackListener(new View.OnClickListener() {
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
        return R.layout.activity_promote;
    }
}