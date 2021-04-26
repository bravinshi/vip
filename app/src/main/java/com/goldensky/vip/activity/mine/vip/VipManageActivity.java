package com.goldensky.vip.activity.mine.vip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.CircleFocusAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityVipManageBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class VipManageActivity extends BaseActivity<ActivityVipManageBinding, PublicViewModel> implements View.OnClickListener {
    private CircleFocusAdapter adapter;
    private List<Integer> list=new ArrayList<>();
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        list.add(R.mipmap.my_pic_vip_guanli2);
        list.add(R.mipmap.my_pic_vip_guanli3);
        list.add(R.mipmap.my_pic_vip_guanli4);
        list.add(R.mipmap.my_pic_vip_guanli5);
        list.add(R.mipmap.my_pic_vip_guanli6);
        list.add(R.mipmap.my_pic_vip_guanli7);
        list.add(R.mipmap.my_pic_vip_guanli8);
        list.add(R.mipmap.my_pic_vip_guanli9);
        adapter=new CircleFocusAdapter(list);
        mBinding.rvVipManage.setLayoutManager(new LinearLayoutManager(this));
        mBinding.rvVipManage.setAdapter(adapter);
        mBinding.setListener(this);
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_vip_manage;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back_vip_manage:
                finish();
                break;
            case R.id.btn_share_vip_manage:
                Starter.startShareToFriendActivity(this,null);
                break;
        }
    }
}