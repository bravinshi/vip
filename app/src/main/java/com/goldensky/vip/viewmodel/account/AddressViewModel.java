package com.goldensky.vip.viewmodel.account;

import android.view.View;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.api.account.AddressService;
import com.goldensky.vip.bean.AddUserAddressReqBean;
import com.goldensky.vip.bean.ChangeUserAddressReqBean;
import com.goldensky.vip.bean.DeleteUserAddressReqBean;
import com.goldensky.vip.viewmodel.PublicViewModel;

public class AddressViewModel extends PublicViewModel {

    public MutableLiveData<Object> deleteAddressLive = new MutableLiveData<>();
    public MutableLiveData<Object> editAddressLive = new MutableLiveData<>();
    public MutableLiveData<Object> addAddressLive = new MutableLiveData<>();





    /**
     * 删除收货地址
     * @param body
     */
    public void deleteUserAddress(DeleteUserAddressReqBean body){

        RetrofitAgent.create(AddressService.class)
                .deleteUserAddress(body)
                .subscribe(new ToastNetObserver<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        deleteAddressLive.postValue(data);
                    }
                });
    }

    /**
     * 编辑收货地址
     * @param body
     */
    public void editUserAddress(ChangeUserAddressReqBean body, View view){

        RetrofitAgent.create(AddressService.class)
                .changeUserAddress(body)
                .subscribe(new ToastNetObserver<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        editAddressLive.postValue(data);
                    }
                }.watchViewClickable(view));
    }



    /**
     * 新建收货地址
     * @param body
     */
    public void addUserAddress(AddUserAddressReqBean body,View view){
        RetrofitAgent.create(AddressService.class)
                .addUserAddress(body)
                .subscribe(new ToastNetObserver<Object>() {
                    @Override
                    public void onSuccess(Object data) {
                        addAddressLive.postValue(data);
                    }
                }.watchViewClickable(view));
    }
}
