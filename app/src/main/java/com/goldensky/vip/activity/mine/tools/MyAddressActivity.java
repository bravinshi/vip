package com.goldensky.vip.activity.mine.tools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.adapter.CircleFocusAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityMyAddressBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class MyAddressActivity extends BaseActivity<ActivityMyAddressBinding, PublicViewModel> {
    private List<Integer> list=new ArrayList<>();
    private CircleFocusAdapter adapter;
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarMyDress.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        list.add(R.mipmap.my_pic_shouhuodizhi1);
        list.add(R.mipmap.my_pic_shouhuodizhi2);
        adapter=new CircleFocusAdapter(list);
        mBinding.rvMyAddress.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvMyAddress.setAdapter(adapter);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_address;
    }
}