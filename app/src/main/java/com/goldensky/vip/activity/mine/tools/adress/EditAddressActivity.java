package com.goldensky.vip.activity.mine.tools.adress;

import androidx.lifecycle.Observer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectChangeListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.bean.AreaListBean;
import com.goldensky.vip.bean.ChangeUserAddressReqBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.databinding.ActivityEditAddressBinding;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.helper.UserAddressHelper;
import com.goldensky.vip.model.EditAddressModel;
import com.goldensky.vip.viewmodel.account.AddressViewModel;

import java.util.List;

public class EditAddressActivity extends BaseActivity<ActivityEditAddressBinding, AddressViewModel> implements View.OnClickListener {
    private int isDefaultCode=0;
    private UserAddressBean addressbean;
    private int selectProvinceId=0;
    private int selectCityId=0;
    private int selectAreaId=0;
    private String selectProvinceName="";
    private String selectCityName="";
    private String selectAreaName="";
    private EditAddressModel editAddressModel=new EditAddressModel();
    private ChangeUserAddressReqBean bean;
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.topBarEditAddress.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mBinding.setEditAddressModel(editAddressModel);
        mBinding.setListener(this);
        addressbean = (UserAddressBean) getIntent().getSerializableExtra("addressbean");
        if(addressbean!=null){
            editAddressModel.setConsigneeName(addressbean.getUseraddressname());
            editAddressModel.setConsigneePhone(addressbean.getUseraddressphone());
            editAddressModel.setLocation(addressbean.getUseraddress());
            selectProvinceId=addressbean.getProvinceid();
            selectCityId=addressbean.getCityid();
            selectAreaId=addressbean.getAreaid();
            selectProvinceName=addressbean.getProvince();
            selectCityName=addressbean.getCity();
            selectAreaName=addressbean.getArea();
            mBinding.etRegionEditAddress.setText(addressbean.getLocation());
            Integer integer = addressbean.getUseraddressdefault();
            if(integer==0){
                mBinding.isDefaultEditAddress.setChecked(false);
                editAddressModel.setDefault(false);
            }else {
                mBinding.isDefaultEditAddress.setChecked(true);
                editAddressModel.setDefault(true);
            }
        }
        if (!UserAddressHelper.getInstance().isAreaLoad()) {
            mViewModel.getAreaList();
        }
    }


    @Override
    public void observe() {

        mViewModel.editAddressLive.observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object editAddressBean) {
                    UserAddressHelper.getInstance().refreshAddressList();
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
        return R.layout.activity_edit_address;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.et_region_edit_address:
                showCityPicker();
                break;
            case R.id.btn_save_edit_address:
                editAddress(v);
                break;
        }
    }

    private void editAddress(View v) {
        if(isNotNull(editAddressModel.getConsigneeName())){
            if(isNotNull(editAddressModel.getConsigneePhone())&&!notPhoneNumber(editAddressModel.getConsigneePhone())){
                if(isNotNull(editAddressModel.getLocation())&isNotNull(mBinding.etRegionEditAddress.getText().toString())){
                    editAddressModel.setDefault(mBinding.isDefaultEditAddress.isChecked());
                    if(editAddressModel.isDefault()){
                        isDefaultCode=1;
                    }
                    bean = new ChangeUserAddressReqBean(selectAreaName, selectAreaId, selectCityName, selectCityId,0, selectProvinceName, selectProvinceId, editAddressModel.getLocation(), isDefaultCode,addressbean.getUseraddressid(), editAddressModel.getConsigneeName(), editAddressModel.getConsigneePhone(), AccountHelper.getUserId());
                    mViewModel.editUserAddress(bean,v);
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
        if(UserAddressHelper.getInstance().isAreaLoad()){
            UserAddressHelper instance = UserAddressHelper.getInstance();
            OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3 , View v) {
                    //返回的分别是三个级别的选中位置
                    if(instance.getProvinceNameList().get(options1).equals("北京")||instance.getProvinceNameList().get(options1).equals("重庆")||instance.getProvinceNameList().get(options1).equals("天津")||instance.getProvinceNameList().get(options1).equals("上海")){
                        mBinding.etRegionEditAddress.setText(instance.getProvinceNameList().get(options1)+"市"
                                + instance.getAreaNameList().get(options1).get(option2).get(options3));
                    }else {
                        mBinding.etRegionEditAddress.setText(instance.getProvinceNameList().get(options1)+"省"
                                + instance.getCityNameList().get(options1).get(option2)+"市"
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
                    .setSubmitText(getResources().getString(R.string.text_confirm))//确定按钮文字
                    .setCancelText(getResources().getString(R.string.text_cancel))//取消按钮文字
                    .setTitleText(getResources().getString(R.string.text_city_select))//标题
                    .setSubCalSize(18)//确定和取消文字大小
                    .setTitleSize(20)//标题文字大小
                    .setTitleColor(Color.BLACK)//标题文字颜色
                    .setSubmitColor(Color.BLACK)//确定按钮文字颜色
                    .setCancelColor(Color.BLACK)//取消按钮文字颜色
                    .setTitleBgColor(Color.WHITE)//标题背景颜色 Night mode
                    .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                    .setTitleBgColor(Color.WHITE)
                    .setContentTextSize(18)//滚轮文字大小
                    .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                    .setCyclic(false, false, false)//循环与否
                    .setSelectOptions(0, 0, 0)  //设置默认选中项
                    .setOutSideCancelable(true)//点击外部dismiss default true
                    .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                    .isAlphaGradient(true)
                    .build();

            pvOptions.setPicker(instance.getProvinceNameList(),instance.getCityNameList(),instance.getAreaNameList());//添加数据源
            pvOptions.show(true);
        }
    }
}