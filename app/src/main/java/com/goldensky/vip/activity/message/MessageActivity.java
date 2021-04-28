package com.goldensky.vip.activity.message;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.adapter.CircleFocusAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityMessageBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends BaseActivity<ActivityMessageBinding, PublicViewModel> {
    private CircleFocusAdapter adapter;
    private List<Integer> list=new ArrayList<>();

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarMsg.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list.add(R.mipmap.xxtz1);
        list.add(R.mipmap.xxtz2);
        adapter=new CircleFocusAdapter(list);
        mBinding.rvMsg.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvMsg.setAdapter(adapter);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_message;
    }
}