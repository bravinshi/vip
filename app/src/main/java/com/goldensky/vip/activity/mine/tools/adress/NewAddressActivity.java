package com.goldensky.vip.activity.mine.tools.adress;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;


import androidx.lifecycle.Observer;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.AddUserAddressReqBean;
import com.goldensky.vip.bean.AreaListBean;
import com.goldensky.vip.bean.NewAddressResponseBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.databinding.ActivityNewAddressBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.helper.UserAddressHelper;
import com.goldensky.vip.model.EditAddressModel;
import com.goldensky.vip.viewmodel.account.AddressViewModel;

import java.util.List;


public class NewAddressActivity extends BaseActivity<ActivityNewAddressBinding, AddressViewModel> implements View.OnClickListener {
    private int selectProvinceId=0;
    private int selectCityId=0;
    private int selectAreaId=0;
    private String selectProvinceName="";
    private String selectCityName="";
    private String selectAreaName="";
    private EditAddressModel newAddressModel=new EditAddressModel();
    private  int isDefaultCode=0;
    private AddUserAddressReqBean bean;
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarNewAddress.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.setNewAdressModel(newAddressModel);
        mBinding.setListener(this);
        if (!UserAddressHelper.getInstance().isAreaLoad()) {
            mViewModel.getAreaList();
        }
    }

    @Override
    public void observe() {

        mViewModel.addAddressLive.observe(this, new Observer<NewAddressResponseBean>() {
            @Override
            public void onChanged(NewAddressResponseBean editAddressBean) {
                    UserAddressBean bean = new UserAddressBean(selectAreaName, selectAreaId, selectCityName, selectCityId, 0, selectProvinceName, selectProvinceId, newAddressModel.getLocation(), isDefaultCode, editAddressBean.getUseraddressid(), newAddressModel.getConsigneeName(), newAddressModel.getConsigneePhone(), editAddressBean.getUseraddresstime(), AccountHelper.getUserId());
                    UserAddressHelper.getInstance().onChangeAddressList();
                    toast("????????????");
                    finish();
            }
        });
        mViewModel.areaListLive.observe(this, new Observer<List<AreaListBean>>() {
            @Override
            public void onChanged(List<AreaListBean> areaListBean) {
                UserAddressHelper.getInstance().loadAddressList(areaListBean);
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
                showCityPicker();
                break;
            case R.id.btn_save_new_address:
                saveAddress(v);
                break;
        }
    }

    private void saveAddress(View v) {
        if(isNotNull(newAddressModel.getConsigneeName())){
            if(isNotNull(newAddressModel.getConsigneePhone())&&!notPhoneNumber(newAddressModel.getConsigneePhone())){
                if(isNotNull(newAddressModel.getLocation())&&isNotNull(mBinding.etRegionNewAddress.getText().toString())){
                    newAddressModel.setDefault(mBinding.isDefaultNewAddress.isChecked());
                    if(newAddressModel.isDefault()){
                        isDefaultCode=1;
                    }
                    bean = new AddUserAddressReqBean(selectAreaName, selectAreaId, selectCityName, selectCityId,0, selectProvinceName, selectProvinceId, newAddressModel.getLocation(), isDefaultCode, newAddressModel.getConsigneeName(), newAddressModel.getConsigneePhone(), AccountHelper.getUserId());
                    mViewModel.addUserAddress(bean,v);
                }else {
                    toast(getResources().getString(R.string.hint_input_consignee_area));
                }
            }else {
                toast(getResources().getString(R.string.hint_input_effective_phone));
            }
        }else {
            toast(getResources().getString(R.string.hint_input_consignee_name_nonull));
        }
    }

    private boolean isNotNull(String str) {
        return str!=null&&!str.equals("");
    }
    private boolean notPhoneNumber(String paddingTestText) {
        return StringUtils.isTrimEmpty(paddingTestText)
                || paddingTestText.trim().length() != 11;
    }
    private void showCityPicker() {
        InputMethodManager manager =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if (manager != null){
            manager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
        if(UserAddressHelper.getInstance().isAreaLoad()){
            UserAddressHelper instance = UserAddressHelper.getInstance();
            OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3 , View v) {
                    //?????????????????????????????????????????????
                    if(instance.getProvinceNameList().get(options1).equals("??????")||instance.getProvinceNameList().get(options1).equals("??????")||instance.getProvinceNameList().get(options1).equals("??????")||instance.getProvinceNameList().get(options1).equals("??????")){
                        mBinding.etRegionNewAddress.setText(instance.getProvinceNameList().get(options1)+"???"
                                + instance.getAreaNameList().get(options1).get(option2).get(options3));
                    }else {
                        mBinding.etRegionNewAddress.setText(instance.getProvinceNameList().get(options1)+"???"
                                + instance.getCityNameList().get(options1).get(option2)+"???"
                                +instance.getAreaNameList().get(options1).get(option2).get(options3));
                    }
                    selectProvinceId=instance.getProvinceList().get(options1).getId();
                    selectCityId=instance.getCityList().get(options1).get(option2).getId();
                    selectAreaId=instance.getAreaList().get(options1).get(option2).get(options3).getId();
                    selectProvinceName=instance.getProvinceList().get(options1).getName();
                    selectCityName=instance.getCityList().get(options1).get(option2).getName();
                    selectAreaName=instance.getAreaList().get(options1).get(option2).get(options3).getName();
                }
            }) .setOptionsSelectChangeListener(new OnOptionsSelectChangeListener() {
                @Override
                public void onOptionsSelectChanged(int options1, int options2, int options3) {
                    String str = "options1: " + options1 + "\noptions2: " + options2 + "\noptions3: " + options3;

                }
            })
                    .setSubmitText(getResources().getString(R.string.text_confirm))//??????????????????
                    .setCancelText(getResources().getString(R.string.text_cancel))//??????????????????
                    .setTitleText(getResources().getString(R.string.text_city_select))//??????
                    .setSubCalSize(18)//???????????????????????????
                    .setTitleSize(20)//??????????????????
                    .setTitleColor(Color.BLACK)//??????????????????
                    .setSubmitColor(Color.BLACK)//????????????????????????
                    .setCancelColor(Color.BLACK)//????????????????????????
                    .setTitleBgColor(Color.WHITE)//?????????????????? Night mode
                    .setBgColor(Color.WHITE)//?????????????????? Night mode
                    .setTitleBgColor(Color.WHITE)
                    .setContentTextSize(18)//??????????????????
                    .isCenterLabel(false) //?????????????????????????????????label?????????false?????????item???????????????label???
                    .setCyclic(false, false, false)//????????????
                    .setSelectOptions(0, 0, 0)  //?????????????????????
                    .setOutSideCancelable(true)//????????????dismiss default true
                    .isRestoreItem(true)//??????????????????????????????????????????????????????
                    .isAlphaGradient(true)
                    .build();

            pvOptions.setPicker(instance.getProvinceNameList(),instance.getCityNameList(),instance.getAreaNameList());//???????????????
            pvOptions.show(true);
        }
    }

}