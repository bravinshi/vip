package com.goldensky.vip.viewmodel.home;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.vip.bean.HomeBean;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.goldensky.vip.adapter.HomeAdapter.ITEM_TYPE_LB;

public class HomeViewModel extends PublicViewModel {
    public MutableLiveData<List<HomeBean>> homeBeans = new MutableLiveData();
    private HomeBean lbBean;
    private List<HomeBean> allBeans = new ArrayList<>();
    //轮播
    public void initLbData() {
        lbBean = new HomeBean(ITEM_TYPE_LB);
        List<String> lbList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            lbList.add("testurl");
        }
        lbBean.setLbList(lbList);
        allBeans.add(lbBean);
        homeBeans.postValue(allBeans);
    }
}
