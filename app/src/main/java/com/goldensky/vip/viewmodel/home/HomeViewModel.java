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


    public void initUserData() {
        UserBean userBean = new UserBean("111", "阿妹水果", "金天合纵商城");
        userBean.setPortraitId(R.mipmap.home_portarit_icon);
//        userData.setValue(userBean);
        userData.postValue(userBean);
    }

    public  void initHomeBeans() {
        List<HomeBean> thomeBeans = new ArrayList<>();

        HomeBean bannerBean = new HomeBean(HomeAdapter.ITEM_TYPE_LB);
        bannerBean.setSingleImgId(R.mipmap.home_lb_banner);
        thomeBeans.add(bannerBean);

        HomeBean msgBean = new HomeBean(HomeAdapter.ITEM_TYPE_MSG);
        msgBean.setMessage("金天合纵-为您提供一站式优质私人订制服务");
        thomeBeans.add(msgBean);

        HomeBean rmdBean = new HomeBean(HomeAdapter.ITEM_TYPE_RMD);
        rmdBean.setItemTitle("为你推荐");
        List<Integer>rmdImgIds = new ArrayList();
        rmdImgIds.add(R.mipmap.home_test_pg);
        rmdImgIds.add(R.mipmap.home_test_cz);
        rmdImgIds.add(R.mipmap.home_test_mg);
        rmdImgIds.add(R.mipmap.home_test_bz);

        List<String> rmdNames = new ArrayList();
        rmdNames.add("高端红富士");
        rmdNames.add("赣南脐橙");
        rmdNames.add("切芒果神器");
        rmdNames.add("榨汁杯马卡龙色");

        List<String> rmdPrices = new ArrayList();
        rmdPrices.add("49.80");
        rmdPrices.add("30.80");
        rmdPrices.add("19.90");
        rmdPrices.add("159.80");

        List<Integer>rmdPdIds = new ArrayList();
        rmdPdIds.add(1);
        rmdPdIds.add(0);
        rmdPdIds.add(0);
        rmdPdIds.add(0);

        List<HomeBean.ProductBean> rmdProducts = new ArrayList<>();
        for (int i = 0 ; i < rmdImgIds.size(); i ++) {
            HomeBean.ProductBean productBean = new HomeBean.ProductBean(HomeProductAdapter.TYPE_RMD_PD, rmdImgIds.get(i), rmdNames.get(i), rmdPrices.get(i), rmdPdIds.get(i));
            rmdProducts.add(productBean);
        }
        rmdBean.setProductList(rmdProducts);

        thomeBeans.add(rmdBean);


        HomeBean jrbkBean = new HomeBean(HomeAdapter.ITEM_TYPE_JRBK);
        jrbkBean.setItemTitle("今日爆款");
        List<Integer>jrbkImgIds = new ArrayList();
        jrbkImgIds.add(R.mipmap.home_test_mg_1);
        jrbkImgIds.add(R.mipmap.home_test_bj);
        jrbkImgIds.add(R.mipmap.home_test_yadan);

        List<String> jrbkNames = new ArrayList();
        jrbkNames.add("三亚芒果新鲜采摘");
        jrbkNames.add("金天喝睿酒");
        jrbkNames.add("宿迁骆马湖咸鸭蛋");

        List<String> jrbkPrices = new ArrayList();
        jrbkPrices.add("34.90");
        jrbkPrices.add("299.00");
        jrbkPrices.add("29.90");

        List<Integer>jrbkPdIds = new ArrayList();
        jrbkPdIds.add(2);
        jrbkPdIds.add(5);
        jrbkPdIds.add(3);

        List<HomeBean.ProductBean> jrbkProducts = new ArrayList<>();
        for (int i = 0 ; i < jrbkImgIds.size(); i ++) {
            HomeBean.ProductBean productBean = new HomeBean.ProductBean(HomeProductAdapter.TYPE_JRBK_PD, jrbkImgIds.get(i), jrbkNames.get(i), jrbkPrices.get(i),jrbkPdIds.get(i));
            jrbkProducts.add(productBean);
        }
        jrbkBean.setProductList(jrbkProducts);

        thomeBeans.add(jrbkBean);



        HomeBean yhzqBean = new HomeBean(HomeAdapter.ITEM_TYPE_YHZQ);
        yhzqBean.setSingleImgId(R.mipmap.home_test_yhzq);
        thomeBeans.add(yhzqBean);



        HomeBean jtyxBean = new HomeBean(HomeAdapter.ITEM_TYPE_JTYX);
        jtyxBean.setItemTitle("金天优选");


        List<Integer>jtyxImgIds1 = new ArrayList();
        jtyxImgIds1.add(R.mipmap.home_test11);
        jtyxImgIds1.add(R.mipmap.home_test12);
        jtyxImgIds1.add(R.mipmap.home_test13);

        List<String> jtyxNames1 = new ArrayList();
        jtyxNames1.add("哈尔滨风味红肠");
        jtyxNames1.add("碳烤鱿鱼条");
        jtyxNames1.add("可口饼干天然…");

        List<String> jtyxPrices1 = new ArrayList();
        jtyxPrices1.add("19.90");
        jtyxPrices1.add("14.80");
        jtyxPrices1.add("46.00");

        List<String> jtyxOPrices1 = new ArrayList();
        jtyxOPrices1.add("28.90");
        jtyxOPrices1.add("18.80");
        jtyxOPrices1.add("88.00");

        List<Integer>jtyxPdIds1 = new ArrayList();
        jtyxPdIds1.add(4);
        jtyxPdIds1.add(0);
        jtyxPdIds1.add(0);

        List<HomeBean.ProductBean> jtyxProducts1 = new ArrayList<>();
        for (int i = 0 ; i < jtyxImgIds1.size(); i ++) {
            HomeBean.ProductBean productBean = new HomeBean.ProductBean(HomeProductAdapter.TYPE_JTYX_PD, jtyxImgIds1.get(i), jtyxNames1.get(i), jtyxPrices1.get(i),jtyxPdIds1.get(i));
            productBean.setOriginPrice(jtyxOPrices1.get(i));
            jtyxProducts1.add(productBean);
        }


        List<Integer>jtyxImgIds2 = new ArrayList();
        jtyxImgIds2.add(R.mipmap.home_test21);
        jtyxImgIds2.add(R.mipmap.home_test222);
        jtyxImgIds2.add(R.mipmap.home_test23);

        List<String> jtyxNames2 = new ArrayList();
        jtyxNames2.add("USB-C数据线");
        jtyxNames2.add("高硼硅耐热玻");
        jtyxNames2.add("景德镇陶瓷碗");

        List<String> jtyxPrices2 = new ArrayList();
        jtyxPrices2.add("9.90");
        jtyxPrices2.add("15.90");
        jtyxPrices2.add("586.00");

        List<String> jtyxOPrices2 = new ArrayList();
        jtyxOPrices2.add("19.60");
        jtyxOPrices2.add("22.60");
        jtyxOPrices2.add("988.00");

        List<Integer>jtyxPdIds2 = new ArrayList();
        jtyxPdIds2.add(0);
        jtyxPdIds2.add(0);
        jtyxPdIds2.add(0);

        List<HomeBean.ProductBean> jtyxProducts2 = new ArrayList<>();
        for (int i = 0 ; i < jtyxImgIds2.size(); i ++) {
            HomeBean.ProductBean productBean = new HomeBean.ProductBean(HomeProductAdapter.TYPE_JTYX_PD, jtyxImgIds2.get(i), jtyxNames2.get(i), jtyxPrices2.get(i),jtyxPdIds2.get(i));
            productBean.setOriginPrice(jtyxOPrices2.get(i));
            jtyxProducts2.add(productBean);
        }


        List<Integer>jtyxImgIds3 = new ArrayList();
        jtyxImgIds3.add(R.mipmap.home_test31);
        jtyxImgIds3.add(R.mipmap.home_test32);
        jtyxImgIds3.add(R.mipmap.home_test33);

        List<String> jtyxNames3 = new ArrayList();
        jtyxNames3.add("蜂蜜正品农家");
        jtyxNames3.add("鸡蛋松饼松可");
        jtyxNames3.add("熨斗界的颜值");

        List<String> jtyxPrices3 = new ArrayList();
        jtyxPrices3.add("29.90");
        jtyxPrices3.add("14.90");
        jtyxPrices3.add("244.90");

        List<String> jtyxOPrices3 = new ArrayList();
        jtyxOPrices3.add("39.60");
        jtyxOPrices3.add("20.60");
        jtyxOPrices3.add("360.60");

        List<Integer>jtyxPdIds3 = new ArrayList();
        jtyxPdIds3.add(0);
        jtyxPdIds3.add(0);
        jtyxPdIds3.add(0);

        List<HomeBean.ProductBean> jtyxProducts3 = new ArrayList<>();
        for (int i = 0 ; i < jtyxImgIds3.size(); i ++) {
            HomeBean.ProductBean productBean = new HomeBean.ProductBean(HomeProductAdapter.TYPE_JTYX_PD, jtyxImgIds3.get(i), jtyxNames3.get(i), jtyxPrices3.get(i),jtyxPdIds3.get(i));
            productBean.setOriginPrice(jtyxOPrices3.get(i));
            jtyxProducts3.add(productBean);
        }

        List<List<HomeBean.ProductBean>> jtyxProductsList = new ArrayList<>();
        jtyxProductsList.add(jtyxProducts1);
        jtyxProductsList.add(jtyxProducts2);
        jtyxProductsList.add(jtyxProducts3);
        jtyxBean.setJtyxProductList(jtyxProductsList);

        thomeBeans.add(jtyxBean);

        homeBeans.postValue(thomeBeans);

//        homeBeans.setValue(thomeBeans);
    }
}
