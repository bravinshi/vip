package com.goldensky.vip.activity.mine.tools.adress;


import android.os.Bundle;
import android.view.View;


import androidx.lifecycle.Observer;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.AreaListBean;
import com.goldensky.vip.databinding.ActivityNewAddressBinding;
import com.goldensky.vip.viewmodel.account.AddressViewModel;



import java.util.ArrayList;
import java.util.List;


public class NewAddressActivity extends BaseActivity<ActivityNewAddressBinding, AddressViewModel> implements View.OnClickListener {
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarNewAddress.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.setListener(this);
        mViewModel.getAreaList();
    }

    @Override
    public void observe() {
        mViewModel.areaListLive.observe(this, new Observer<List<AreaListBean>>() {
            @Override
            public void onChanged(List<AreaListBean> areaListBean) {
                for (AreaListBean listBean : areaListBean) {
                    List<AreaListBean.ChildrenDTOX> childrenCitys = listBean.getChildren();
                    for (AreaListBean.ChildrenDTOX city : childrenCitys) {
                        List<AreaListBean.ChildrenDTOX.ChildrenDTO> childrenAreas = city.getChildren();
                        for (AreaListBean.ChildrenDTOX.ChildrenDTO area : childrenAreas) {
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_new_address;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_region_new_address:


                break;
        }
    }
}