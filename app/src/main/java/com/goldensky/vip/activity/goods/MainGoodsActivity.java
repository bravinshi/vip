package com.goldensky.vip.activity.goods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.adapter.CircleFocusAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityMainGoodsBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainGoodsActivity extends BaseActivity<ActivityMainGoodsBinding, PublicViewModel> {
    private CircleFocusAdapter adapter;
    private List<Integer> list=new ArrayList<>();

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        list.add(R.mipmap.my_pic_zhuyingshangpin1);
        list.add(R.mipmap.my_pic_zhuyingshangpin2);
        list.add(R.mipmap.my_pic_zhuyingshangpin3);
        list.add(R.mipmap.my_pic_zhuyingshangpin4);
        list.add(R.mipmap.my_pic_zhuyingshangpin5);
        adapter=new CircleFocusAdapter(list);
        mBinding.rvMianGoods.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvMianGoods.setAdapter(adapter);
        mBinding.topBarMainGoods.setBackListener(new View.OnClickListener() {
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
        return R.layout.activity_main_goods;
    }
}