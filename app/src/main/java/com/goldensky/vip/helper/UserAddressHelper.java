package com.goldensky.vip.helper;

import com.goldensky.vip.bean.AreaListBean;
import com.goldensky.vip.bean.AreaPickerBean;
import com.goldensky.vip.bean.DeleteUserAddressReqBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.event.AddAddressEvent;
import com.goldensky.vip.event.RefreshAddressEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class UserAddressHelper {
    private List<UserAddressBean> userAddressList = new ArrayList<>();;
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
        userAddressList.clear();
        userAddressList.addAll(list);
        refreshAddressList();
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




    /**
     * 收货地址列表改变响应
     */
    public void onChangeAddressList() {
        EventBus.getDefault().post(new AddAddressEvent(true));
    }
    /**
     * 收货地址列表改变响应
     */
    public void refreshAddressList() {
        EventBus.getDefault().post(new RefreshAddressEvent(true));
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
