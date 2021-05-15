package com.goldensky.vip.helper;

import com.goldensky.vip.bean.AreaListBean;
import com.goldensky.vip.bean.AreaPickerBean;
import com.goldensky.vip.bean.DeleteUserAddressReqBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.event.AddAddressEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class UserAddressHelper {
    private List<UserAddressBean> userAddressList;
    private List<AreaPickerBean> provinceList = new ArrayList<>();
    private List<String> provinceNameList = new ArrayList<>();
    private List<List<AreaPickerBean>> cityList = new ArrayList<>();
    private List<List<String>> cityNameList = new ArrayList<>();
    private List<List<List<AreaPickerBean>>> areaList = new ArrayList<>();
    private List<List<List<String>>> areaNameList = new ArrayList<>();
    private static UserAddressHelper userAddressHelper;

    private UserAddressHelper() {
    }

    public List<AreaPickerBean> getProvinceList() {
        return provinceList;
    }

    public List<String> getProvinceNameList() {
        return provinceNameList;
    }

    public List<List<AreaPickerBean>> getCityList() {
        return cityList;
    }

    public List<List<String>> getCityNameList() {
        return cityNameList;
    }

    public List<List<List<AreaPickerBean>>> getAreaList() {
        return areaList;
    }

    public List<List<List<String>>> getAreaNameList() {
        return areaNameList;
    }


    public static UserAddressHelper getInstance() {
        if (userAddressHelper == null) {
            userAddressHelper = new UserAddressHelper();
        }
        return userAddressHelper;
    }

    public void setUserAddressList(List<UserAddressBean> list) {
        userAddressList = new ArrayList<>();
        userAddressList.addAll(list);
        onChangeAddressList();
    }

    public Boolean isUserAddressLoad() {
        return userAddressList != null;
    }

    public Boolean isAreaLoad() {
        return provinceList.size() != 0;
    }

    public List<UserAddressBean> getUserAddressList() {
        return userAddressList;
    }

    public void clear() {
        userAddressList.clear();
    }

    public void editUserAddress(UserAddressBean bean) {
        int editPosition = 0;
        for (UserAddressBean userAddressBean : userAddressList) {
            if (userAddressBean.getUseraddressid() == bean.getUseraddressid()) {
                editPosition = userAddressList.indexOf(userAddressBean);
            }
        }
        userAddressList.remove(editPosition);
        userAddressList.add(editPosition, bean);
        onChangeAddressList();
    }

    /**
     * 添加
     *
     * @param bean
     */
    public void addUserAddress(UserAddressBean bean) {
        if (bean.getUseraddressdefault() == 1) {
            for (UserAddressBean userAddressBean : userAddressList) {
                if (userAddressBean.getUseraddressdefault() == 1) {
                    userAddressBean.setUseraddressdefault(0);
                }
            }
            userAddressList.add(0, bean);
        } else {
            userAddressList.add(bean);
        }
        onChangeAddressList();
    }

    /**
     * 删除收货地址
     *
     * @param bean
     */
    public void deleteUserAddress(DeleteUserAddressReqBean bean) {
        int deletePosition = 0;
        for (UserAddressBean userAddressBean : userAddressList) {
            if (userAddressBean.getUseraddressid().equals(bean.getUseraddressid())) {
                deletePosition = userAddressList.indexOf(userAddressBean);
            }
        }
        userAddressList.remove(deletePosition);
        onChangeAddressList();
    }

    /**
     * 收货地址列表改变响应
     */
    private void onChangeAddressList() {
        EventBus.getDefault().post(new AddAddressEvent(true));
    }

    /**
     * 缓存省市县列表
     *
     * @param areaListBean
     */
    public void loadAddressList(List<AreaListBean> areaListBean) {
        for (AreaListBean listBean : areaListBean) {
            AreaPickerBean provincebean = new AreaPickerBean(listBean.getAreaid(), listBean.getAreaname());
            provinceList.add(provincebean);
            provinceNameList.add(provincebean.getName());
            List<AreaListBean.ChildrenDTOX> childrenCitys = listBean.getChildren();
            List<AreaPickerBean> citys = new ArrayList<>();
            List<String> cityNames = new ArrayList<>();
            List<List<AreaPickerBean>> areaLists = new ArrayList<>();
            List<List<String>> areaNameLists = new ArrayList<>();
            for (AreaListBean.ChildrenDTOX city : childrenCitys) {
                AreaPickerBean cityBean = new AreaPickerBean(city.getAreaid(), city.getAreaname());
                citys.add(cityBean);
                cityNames.add(city.getAreaname());
                List<AreaPickerBean> areas = new ArrayList<>();
                List<String> areaNames = new ArrayList<>();
                List<AreaListBean.ChildrenDTOX.ChildrenDTO> childrenAreas = city.getChildren();
                for (AreaListBean.ChildrenDTOX.ChildrenDTO area : childrenAreas) {
                    AreaPickerBean areaBean = new AreaPickerBean(area.getAreaid(), area.getAreaname());
                    areas.add(areaBean);
                    areaNames.add(area.getAreaname());
                }
                areaLists.add(areas);
                areaNameLists.add(areaNames);
            }
            cityList.add(citys);
            cityNameList.add(cityNames);
            areaList.add(areaLists);
            areaNameList.add(areaNameLists);
        }
    }

}
