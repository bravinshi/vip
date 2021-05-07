package com.goldensky.vip.viewmodel.account;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.api.account.AddressService;
import com.goldensky.vip.bean.EditAddressBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.viewmodel.PublicViewModel;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddressViewModel extends PublicViewModel {
    public MutableLiveData<List<UserAddressBean>> userAddressLive = new MutableLiveData<>();
    public MutableLiveData<EditAddressBean> deleteAddressLive = new MutableLiveData<>();
    public MutableLiveData<EditAddressBean> editAddressLive = new MutableLiveData<>();
    public void getUserAddress(String userId){
        RetrofitAgent.create(AddressService.class)
                .getUserAddressList(userId)
                .subscribe(new ToastNetObserver<List<UserAddressBean>>() {
                    @Override
                    public void onSuccess(List<UserAddressBean> data) {
                        userAddressLive.postValue(data);
                    }
                });
    }
    public void deleteUserAddress(String useraddressid){
        RetrofitAgent.create(AddressService.class)
                .deleteUserAddress(useraddressid)
                .subscribe(new ToastNetObserver<EditAddressBean>() {
                    @Override
                    public void onSuccess(EditAddressBean data) {
                        deleteAddressLive.postValue(data);
                    }
                });
    }
    public void editUserAddress(HashMap<String,Object> map){
        JsonObject jsonObject = new JsonObject();
        Set<String> keys = map.keySet();
        for (String key : keys) {
            if(map.get(key)instanceof Integer){
                jsonObject.addProperty(key, (Integer) map.get(key));
            }else if(map.get(key) instanceof String){
                jsonObject.addProperty(key, (String) map.get(key));
            }

        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=utf-8"), jsonObject.toString());
        RetrofitAgent.create(AddressService.class)
                .changeUserAddress(requestBody)
                .subscribe(new ToastNetObserver<EditAddressBean>() {
                    @Override
                    public void onSuccess(EditAddressBean data) {
                        deleteAddressLive.postValue(data);
                    }
                });
    }

}
