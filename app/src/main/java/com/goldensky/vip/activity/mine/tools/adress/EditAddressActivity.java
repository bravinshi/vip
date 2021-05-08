package com.goldensky.vip.activity.mine.tools.adress;

import androidx.appcompat.app.AppCompatActivity;
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
import com.goldensky.vip.bean.AddUserAddressReqBean;
import com.goldensky.vip.bean.ChangeUserAddressReqBean;
import com.goldensky.vip.bean.EditAddressBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.databinding.ActivityEditAddressBinding;
import com.goldensky.vip.enumerate.StatusTypeEnum;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.helper.UserAddressHelper;
import com.goldensky.vip.model.EditAddressModel;
import com.goldensky.vip.viewmodel.account.AddressViewModel;

import java.io.Serializable;

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
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        addressbean = (UserAddressBean) intent.getSerializableExtra("addressbean");
    }

    @Override
    public void observe() {

        mViewModel.addAddressLive.observe(this, new Observer<EditAddressBean>() {
            @Override
            public void onChanged(EditAddressBean editAddressBean) {
                Integer status = editAddressBean.getStatus();
                if(status== StatusTypeEnum.SUCCSESS.value){
                    UserAddressBean addressBean = new UserAddressBean(bean.getArea(),bean.getAreaid(),bean.getCity(),bean.getCityid(),bean.getIsdel(),bean.getProvince(),bean.getProvinceid(),bean.getUseraddress(),bean.getUseraddressdefault(),bean.getUseraddressid(),bean.getUseraddressname(),bean.getUseraddressphone(),"",bean.getUserid());
                    UserAddressHelper.getInstance().editUserAddress(addressBean);
                }
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
                editAddress();
                break;
        }
    }

    private void editAddress() {
        if(isNotNull(editAddressModel.getConsigneeName())){
            if(isNotNull(editAddressModel.getConsigneePhone())&&!notPhoneNumber(editAddressModel.getConsigneePhone())){
                if(isNotNull(editAddressModel.getArea())&&isNotNull(editAddressModel.getLocation())){
                    if(editAddressModel.isDefault()){
                        isDefaultCode=1;
                    }
                    bean = new ChangeUserAddressReqBean(selectAreaName, selectAreaId, selectCityName, selectCityId,0, selectProvinceName, selectProvinceId, editAddressModel.getLocation(), isDefaultCode,addressbean.getUseraddressid(), editAddressModel.getConsigneeName(), editAddressModel.getConsigneePhone(), AccountHelper.getUserId());
                    mViewModel.editUserAddress(bean);
                }else {
                    toast(getResources().getString(R.string.hint_input_consignee_area_nonull));
                }
            }else {
                toast(getResources().getString(R.string.hint_input_effective_phone));
            }
        }else {
            toast(getResources().getString(R.string.hint_input_consignee_name_nonull));
        }
    }

    private boolean isNotNull(String str) {
        return !str.equals("")&&str!=null;
    }
    private boolean notPhoneNumber(String paddingTestText) {
        return StringUtils.isTrimEmpty(paddingTestText)
                || paddingTestText.trim().length() != 11;
    }
    private void showCityPicker() {
        UserAddressHelper instance = UserAddressHelper.getInstance();
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3 , View v) {
                //返回的分别是三个级别的选中位置
                if(instance.getProvinceNameList().get(options1).equals("北京")||instance.getProvinceNameList().get(options1).equals("重庆")||instance.getProvinceNameList().get(options1).equals("天津")||instance.getProvinceNameList().get(options1).equals("上海")){
                    editAddressModel.setArea(instance.getProvinceNameList().get(options1)+"市"
                            + instance.getAreaNameList().get(options1).get(option2).get(options3));
                }else {
                    editAddressModel.setArea(instance.getProvinceNameList().get(options1)+"省"
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
                .setTitleBgColor(R.color.colorWhite)//标题背景颜色 Night mode
                .setBgColor(R.color.colorWhite)//滚轮背景颜色 Night mode
                .setTitleBgColor(R.color.colorWhite)
                .setContentTextSize(18)//滚轮文字大小
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(0, 0, 0)  //设置默认选中项
                .setOutSideCancelable(true)//点击外部dismiss default true
                .isDialog(true)//是否显示为对话框样式
                .isRestoreItem(true)//切换时是否还原，设置默认选中第一项。
                .isAlphaGradient(true)
                .build();

        pvOptions.setPicker(instance.getProvinceNameList(),instance.getCityNameList(),instance.getAreaNameList());//添加数据源
        pvOptions.show(true);
    }
}