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
        rmdImgIds.add(R.mipmap.home_test_niunan);
        rmdImgIds.add(R.mipmap.home_test_fangshaifu);
        rmdImgIds.add(R.mipmap.home_test_mg);
        rmdImgIds.add(R.mipmap.home_test_bz);

        List<String> rmdNames = new ArrayList();
        rmdNames.add("进口牛腩块");
        rmdNames.add("高效防晒衣");
        rmdNames.add("切芒果神器");
        rmdNames.add("榨汁杯马卡龙色");

        List<String> rmdPrices = new ArrayList();
        rmdPrices.add("59.90");
        rmdPrices.add("179.80");
        rmdPrices.add("19.90");
        rmdPrices.add("159.80");

        List<Integer>rmdPdIds = new ArrayList();
        rmdPdIds.add(17);
        rmdPdIds.add(18);
        rmdPdIds.add(7);
        rmdPdIds.add(8);

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
        jrbkImgIds.add(R.mipmap.home_test_yusan);

        List<String> jrbkNames = new ArrayList();
        jrbkNames.add("三亚芒果新鲜采摘");
        jrbkNames.add("金天喝睿酒");
        jrbkNames.add("双人雨伞太阳伞");

        List<String> jrbkPrices = new ArrayList();
        jrbkPrices.add("34.90");
        jrbkPrices.add("299.00");
        jrbkPrices.add("39.90");

        List<Integer>jrbkPdIds = new ArrayList();
        jrbkPdIds.add(2);
        jrbkPdIds.add(5);
        jrbkPdIds.add(19);

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
        jtyxImgIds1.add(R.mipmap.home_test_jiuyang);
        jtyxImgIds1.add(R.mipmap.home_test_xiangxun);

        List<String> jtyxNames1 = new ArrayList();
        jtyxNames1.add("哈尔滨风味红肠");
        jtyxNames1.add("九阳榨汁机");
        jtyxNames1.add("车载香薰 加湿…");

        List<String> jtyxPrices1 = new ArrayList();
        jtyxPrices1.add("19.90");
        jtyxPrices1.add("189.00");
        jtyxPrices1.add("34.20");

        List<String> jtyxOPrices1 = new ArrayList();
        jtyxOPrices1.add("28.90");
        jtyxOPrices1.add("205.00");
        jtyxOPrices1.add("88.00");

        List<Integer>jtyxPdIds1 = new ArrayList();
        jtyxPdIds1.add(4);
        jtyxPdIds1.add(20);
        jtyxPdIds1.add(21);

        List<HomeBean.ProductBean> jtyxProducts1 = new ArrayList<>();
        for (int i = 0 ; i < jtyxImgIds1.size(); i ++) {
            HomeBean.ProductBean productBean = new HomeBean.ProductBean(HomeProductAdapter.TYPE_JTYX_PD, jtyxImgIds1.get(i), jtyxNames1.get(i), jtyxPrices1.get(i),jtyxPdIds1.get(i));
            productBean.setOriginPrice(jtyxOPrices1.get(i));
            jtyxProducts1.add(productBean);
        }


        List<Integer>jtyxImgIds2 = new ArrayList();
        jtyxImgIds2.add(R.mipmap.home_test_buxie);
        jtyxImgIds2.add(R.mipmap.home_test22);
        jtyxImgIds2.add(R.mipmap.home_test23);

        List<String> jtyxNames2 = new ArrayList();
        jtyxNames2.add("夏季老北京布…");
        jtyxNames2.add("康佳康u18老…");
        jtyxNames2.add("景德镇陶瓷碗");

        List<String> jtyxPrices2 = new ArrayList();
        jtyxPrices2.add("99.90");
        jtyxPrices2.add("218.00");
        jtyxPrices2.add("586.00");

        List<String> jtyxOPrices2 = new ArrayList();
        jtyxOPrices2.add("119.60");
        jtyxOPrices2.add("329.00");
        jtyxOPrices2.add("988.00");

        List<Integer>jtyxPdIds2 = new ArrayList();
        jtyxPdIds2.add(22);
        jtyxPdIds2.add(23);
        jtyxPdIds2.add(13);

        List<HomeBean.ProductBean> jtyxProducts2 = new ArrayList<>();
        for (int i = 0 ; i < jtyxImgIds2.size(); i ++) {
            HomeBean.ProductBean productBean = new HomeBean.ProductBean(HomeProductAdapter.TYPE_JTYX_PD, jtyxImgIds2.get(i), jtyxNames2.get(i), jtyxPrices2.get(i),jtyxPdIds2.get(i));
            productBean.setOriginPrice(jtyxOPrices2.get(i));
            jtyxProducts2.add(productBean);
        }


        List<Integer>jtyxImgIds3 = new ArrayList();
        jtyxImgIds3.add(R.mipmap.home_test_xiyiye);
        jtyxImgIds3.add(R.mipmap.home_test32);
        jtyxImgIds3.add(R.mipmap.home_test33);

        List<String> jtyxNames3 = new ArrayList();
        jtyxNames3.add("雕牌洗衣液家...");
        jtyxNames3.add("鸡蛋松饼松可");
        jtyxNames3.add("熨斗界的颜值");

        List<String> jtyxPrices3 = new ArrayList();
        jtyxPrices3.add("49.90");
        jtyxPrices3.add("14.90");
        jtyxPrices3.add("244.90");

        List<String> jtyxOPrices3 = new ArrayList();
        jtyxOPrices3.add("69.90");
        jtyxOPrices3.add("20.60");
        jtyxOPrices3.add("360.60");

        List<Integer>jtyxPdIds3 = new ArrayList();
        jtyxPdIds3.add(24);
        jtyxPdIds3.add(15);
        jtyxPdIds3.add(16);

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
