package com.goldensky.vip.activity.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.adapter.JhManageAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityJhManageBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class JhManageActivity extends BaseActivity<ActivityJhManageBinding, PublicViewModel> implements View.OnClickListener {

    private  boolean isDaili = true;

    private List<Integer> data = new ArrayList<>();
    private List<Integer> dlList = new ArrayList<>();
    private List<Integer> zyList = new ArrayList<>();
    private JhManageAdapter mAdapter;

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.leftV.setOnClickListener(this);
        mBinding.rightV.setOnClickListener(this);
        mBinding.backV.setOnClickListener(this);

        dlList.add(R.mipmap.my_pic_jinhuoguanlidaili1);
        dlList.add(R.mipmap.my_pic_jinhuoguanlidaili2);
        dlList.add(R.mipmap.my_pic_jinhuoguanlidaili3);
        dlList.add(R.mipmap.my_pic_jinhuoguanlidaili4);

        zyList.add(R.mipmap.my_pic_jinhuoguanzhuying1);
        zyList.add(R.mipmap.my_pic_jinhuoguanzhuying2);
        zyList.add(R.mipmap.my_pic_jinhuoguanzhuying3);
        zyList.add(R.mipmap.my_pic_jinhuoguanzhuying4);

        data.addAll(dlList);
        mAdapter = new JhManageAdapter(data);
        mBinding.recycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mBinding.recycleView.setAdapter(mAdapter);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_jh_manage;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_v:
                if (isDaili) return;
                mBinding.tabIv.setImageResource(R.mipmap.my_pic_jinhuoguanlidailishangpin);
                data.clear();
                data.addAll(dlList);
                mAdapter.notifyDataSetChanged();
                mBinding.recycleView.scrollTo(0, 0);
                isDaili = true;
                break;
            case R.id.right_v:
                if (!isDaili) return;
                mBinding.tabIv.setImageResource(R.mipmap.my_pic_jinhuo_zytab);
                data.clear();
                data.addAll(zyList);
                mAdapter.notifyDataSetChanged();
                mBinding.recycleView.scrollTo(0, 0);
                isDaili = false;
                break;
            case R.id.back_v:
                finish();
                break;
        }
    }
}