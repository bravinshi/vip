package com.goldensky.vip.viewmodel.account;

import androidx.lifecycle.MutableLiveData;

import com.goldensky.framework.net.RetrofitAgent;
import com.goldensky.vip.api.account.AddressService;
import com.goldensky.vip.bean.AddUserAddressReqBean;
import com.goldensky.vip.bean.AreaListBean;
import com.goldensky.vip.bean.ChangeUserAddressReqBean;
import com.goldensky.vip.bean.DeleteUserAddressReqBean;
import com.goldensky.vip.bean.EditAddressBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.bean.UserAddressListReqBean;
import com.goldensky.vip.viewmodel.PublicViewModel;

import java.util.List;

public class AddressViewModel extends PublicViewModel {

    public MutableLiveData<EditAddressBean> deleteAddressLive = new MutableLiveData<>();
    public MutableLiveData<EditAddressBean> editAddressLive = new MutableLiveData<>();
    public MutableLiveData<EditAddressBean> addAddressLive = new MutableLiveData<>();





    /**
     * 删除收货地址
     * @param body
     */
    public void deleteUserAddress(DeleteUserAddressReqBean body){

        RetrofitAgent.create(AddressService.class)
                .deleteUserAddress(body)
                .subscribe(new ToastNetObserver<EditAddressBean>() {
                    @Override
                    public void onSuccess(EditAddressBean data) {
                        deleteAddressLive.postValue(data);
                    }
                });
    }

    /**
     * 编辑收货地址
     * @param body
     */
    public void editUserAddress(ChangeUserAddressReqBean body){

        RetrofitAgent.create(AddressService.class)
                .changeUserAddress(body)
                .subscribe(new ToastNetObserver<EditAddressBean>() {
                    @Override
                    public void onSuccess(EditAddressBean data) {
                        editAddressLive.postValue(data);
                    }
                });
    }



    /**
     * 新建收货地址
     * @param body
     */
    public void addUserAddress(AddUserAddressReqBean body){
        RetrofitAgent.create(AddressService.class)
                .addUserAddress(body)
                .subscribe(new ToastNetObserver<EditAddressBean>() {
                    @Override
                    public void onSuccess(EditAddressBean data) {
                        addAddressLive.postValue(data);
                    }
                });
    }
}
