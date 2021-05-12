package com.goldensky.vip.viewmodel.home;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.vip.bean.HomeBean;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.List;

public class HomeViewModel extends PublicViewModel {
    public MutableLiveData<List<HomeBean>> homeBeans = new MutableLiveData();
}
