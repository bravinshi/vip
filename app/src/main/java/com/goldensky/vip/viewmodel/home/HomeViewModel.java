package com.goldensky.vip.viewmodel.home;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.vip.R;
import com.goldensky.vip.adapter.HomeAdapter;
import com.goldensky.vip.adapter.HomeProductAdapter;
import com.goldensky.vip.bean.HomeBean;
import com.goldensky.vip.bean.UserBean;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends PublicViewModel {
    private MutableLiveData<UserBean> userData = new MutableLiveData();
    private MutableLiveData<List<HomeBean>> homeBeans = new MutableLiveData();

    public MutableLiveData<UserBean> getUserData() {
        return userData;
    }

    public MutableLiveData<List<HomeBean>> getHomeBeans() {
        return homeBeans;
    }
}
