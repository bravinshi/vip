package com.goldensky.vip.activity.goods;

import android.os.Bundle;
import android.view.View;

import com.goldensky.framework.util.CollectionUtils;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.framework.util.ToastUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.BannerImageAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.base.ui.dialog.GoodsSpecificationDialog;
import com.goldensky.vip.base.ui.dialog.SelectAddressDialog;
import com.goldensky.vip.bean.CommodityBean;
import com.goldensky.vip.bean.CommodityPicBean;
import com.goldensky.vip.bean.ConfirmOrderItemBean;
import com.goldensky.vip.bean.InventoryBean;
import com.goldensky.vip.bean.JoinIntoShoppingCartReqBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.constant.BusinessConstant;
import com.goldensky.vip.databinding.ActivityGoodsDetailBinding;
import com.goldensky.vip.event.AddAddressEvent;
import com.goldensky.vip.event.JoinOrBuyEvent;
import com.goldensky.vip.event.RetrieveAddressEvent;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.viewmodel.goods.GoodsDetailViewModel;
import com.youth.banner.indicator.CircleIndicator;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/14 14:21
 * 包名： com.goldensky.vip.activity.goods
 * 类说明：
 */
public class GoodsDetailActivity extends BaseActivity<ActivityGoodsDetailBinding,
        GoodsDetailViewModel> {

    public static final String KEY_GOODS_ID = "KEY_GOODS_ID";
    private String goodsId = "";
    private SelectAddressDialog selectAddressDialog;
    private GoodsSpecificationDialog goodsSpecificationDialog;
    private String selectAddressDialogTag = "selectAddressDialog";
    private String goodsSpecificationDialogTag = "goodsSpecificationDialog";
    private UserAddressBean selectedAddress = null;// 选择的地址
    private boolean showDefaultAddress = false;// 是否展示过默认地址

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
//        Bundle bundle = getIntent().getExtras();
//        if (bundle == null) {
//            return;
//        }
//
//        Integer goodsId = bundle.getInt(KEY_GOODS_ID, -1);
//        if (goodsId == -1) {
//            return;
//        }

        // 获取商品详情
        mViewModel.getGoodsDetail(347);
        // 获取评论信息
        mViewModel.getGoodsComment(1, 1, goodsId, null, null);
        // 获取地址信息
        mViewModel.getUserAddress(AccountHelper.getUserId());
    }

    @Override
    protected void onResume() {
        super.onResume();
//        List<UserAddressBean> userAddresses = new ArrayList<>();
//        UserAddressBean userAddressBean1 = new UserAddressBean();
//        UserAddressBean userAddressBean2 = new UserAddressBean();
//        userAddressBean1.setArea("area1");
//        userAddressBean2.setArea("area2");
//        userAddressBean1.setCity("city1");
//        userAddressBean2.setCity("city2");
//        userAddressBean1.setProvince("province1");
//        userAddressBean2.setProvince("province2");
//        userAddressBean1.setUseraddress("setUseraddress1");
//        userAddressBean2.setUseraddress("setUseraddress2");
//        userAddressBean1.setUseraddressname("addressname1");
//        userAddressBean2.setUseraddressname("addressname2");
//        userAddressBean1.setUseraddressphone("13651862222");
//        userAddressBean2.setUseraddressphone("13651862223");
//        userAddressBean2.setUseraddressdefault(1);
//        userAddresses.add(userAddressBean1);
//        userAddresses.add(userAddressBean2);
//
//        handleAddress(userAddresses);
    }

    /**
     * 订阅添加地址处理结果
     *
     * @param addAddressEvent 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleAddAddress(AddAddressEvent addAddressEvent) {
        if (addAddressEvent.getSuccess()) {
            // 刷新地址信息
            mViewModel.getUserAddress(AccountHelper.getUserId());
        }
    }

    /**
     * 处理规格dialog的加入购物车和立即购买
     *
     * @param joinOrBuyEvent 加入购物车和立即购买事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void joinOrBuy(JoinOrBuyEvent joinOrBuyEvent) {
        if (joinOrBuyEvent.getJoin()) {
            join(joinOrBuyEvent.getInventory(), joinOrBuyEvent.getPurchaseNum());
        } else {
            buy(joinOrBuyEvent.getInventory(), joinOrBuyEvent.getPurchaseNum());
        }
    }

    public void buy(InventoryBean inventory, Integer purchaseNum) {
        ConfirmOrderItemBean confirmOrderItemBean = ConfirmOrderItemBean
                .generateConfirmOrderItem(inventory, mViewModel.goodsDetailLive.getValue(), purchaseNum);

        List<ConfirmOrderItemBean> confirmOrderItemBeans = new ArrayList<>();
        confirmOrderItemBeans.add(confirmOrderItemBean);
    }

    public void join(InventoryBean inventory, Integer purchaseNum) {
        if (mViewModel.goodsDetailLive.getValue() != null) {
            // 发起加入购物车请求
            CommodityBean commodityDetail = mViewModel.goodsDetailLive.getValue();
            JoinIntoShoppingCartReqBean joinIntoShoppingCartReqBean
                    = JoinIntoShoppingCartReqBean.fromInventoryAndGoodsDetail(commodityDetail, inventory);
            joinIntoShoppingCartReqBean.setPurchasenum(purchaseNum);
            mViewModel.joinIntoShoppingCart(joinIntoShoppingCartReqBean);
        }
    }

    /**
     * 订阅选择地址
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeAddress(RetrieveAddressEvent event) {
        selectedAddress = event.getAddressBean();
        mBinding.tvAddress.setText(event.getAddress());
    }

    /**
     * 展示商品详情信息
     *
     * @param detail 详情信息
     */
    public void showGoodsDetail(CommodityBean detail) {
        // 轮播图片
        Set<String> pics = new HashSet<>();
        if (!StringUtils.isTrimEmpty(detail.getCommodityIcon())) {
            pics.add(detail.getCommodityIcon());
        }
        if (!CollectionUtils.nullOrEmpty(detail.getCommodityPicList())) {
            for (CommodityPicBean picBean : detail.getCommodityPicList()) {
                if (!StringUtils.isTrimEmpty(picBean.getPicUrl())) {
                    pics.add(picBean.getPicUrl());
                }
            }
        }
        if (!pics.isEmpty()) {
            mBinding.vpImage.setAdapter(new BannerImageAdapter(new ArrayList<>(pics)))
                    .addBannerLifecycleObserver(this)//添加生命周期观察者
                    .setIndicator(new CircleIndicator(this));
        } else {
            mBinding.vpImage.setVisibility(View.GONE);
        }
        // 价格
        mBinding.tvPrice.setText("￥" + detail.getCommodityOldPrice());
        // 标题
        mBinding.tvTitle.setText(detail.getCommodityName());
        // 详情
        String content = detail.getCommodityDesc()
                .replace("<img", "<img style=\"max-width:100%;height:auto\"");
        mBinding.wvDetail.loadData(content, "text/html", "utf-8");

        handleInventory(detail.getCommodityInventoryList());
    }

    // 处理规格
    private void handleInventory(List<InventoryBean> inventoryBeans) {
        if (CollectionUtils.nullOrEmpty(inventoryBeans)) {
            return;
        }

        if (goodsSpecificationDialog == null) {
            goodsSpecificationDialog = new GoodsSpecificationDialog();
        }

        goodsSpecificationDialog.setData(inventoryBeans);
    }

    /**
     * 处理地址列表
     *
     * @param userAddresses 地址列表
     */
    private void handleAddress(List<UserAddressBean> userAddresses) {
        if (selectAddressDialog == null) {
            selectAddressDialog = new SelectAddressDialog();
        }

        if (CollectionUtils.nullOrEmpty(userAddresses)) {
            return;
        }

        // 检查是否展示过默认地址
        if (!showDefaultAddress) {
            showDefaultAddress = true;
            showDefaultAddress(userAddresses);
        }

        selectAddressDialog.setData(userAddresses);
    }

    /**
     * 第一次进入界面后展示默认地址
     *
     * @param userAddresses 地址列表
     */
    private void showDefaultAddress(List<UserAddressBean> userAddresses) {
        if (CollectionUtils.nullOrEmpty(userAddresses)) {
            return;
        }
        for (UserAddressBean userAddressBean : userAddresses) {
            if (BusinessConstant.VALUE_DEFAULT_ADDRESS.equals(userAddressBean.getUseraddressdefault())) {
                selectedAddress = userAddressBean;
                mBinding.tvAddress.setText(selectedAddress.getAddress());
                return;
            }
        }

        selectedAddress = userAddresses.get(0);
        mBinding.tvAddress.setText(selectedAddress.getAddress());
    }

    @Override
    public void observe() {
        mViewModel.goodsCommentLive.observe(this, goodsCommentResBean -> {
            mBinding.tvCommentNum.setText("(" + goodsCommentResBean.getTotalCount() + ")");
        });

        mViewModel.goodsDetailLive.observe(this, this::showGoodsDetail);

        mViewModel.userAddressLive.observe(this, this::handleAddress);

        mViewModel.joinIntoShoppingCartResultLive.observe(this, aBoolean -> {
            if (aBoolean) {
                ToastUtils.showShort(R.string.text_join_shopping_cart_success);
            }
        });
    }

    @Override
    public void initListener() {
        mBinding.topBarGoodsDetail.setBackListener(v -> finish());

        mBinding.clAddress.setOnClickListener(v -> {
            if (selectAddressDialog != null) {
                selectAddressDialog.show(getSupportFragmentManager(), selectAddressDialogTag);
            }
        });
        // 已选规格区域点击
        mBinding.clSpecification.setOnClickListener(v -> {
            if (goodsSpecificationDialog != null) {
                goodsSpecificationDialog.setBtnState(View.VISIBLE, View.VISIBLE);
                goodsSpecificationDialog.show(getSupportFragmentManager(), goodsSpecificationDialogTag);
            }
        });
        // 加入购物车监听
        mBinding.tvJoin.setOnClickListener(v -> {
            if (goodsSpecificationDialog != null) {
                goodsSpecificationDialog.setBtnState(View.VISIBLE, View.GONE);
                goodsSpecificationDialog.show(getSupportFragmentManager(), goodsSpecificationDialogTag);
            }
        });
        // 立刻购买监听
        mBinding.tvBuy.setOnClickListener(v -> {
            if (goodsSpecificationDialog != null) {
                goodsSpecificationDialog.setBtnState(View.GONE, View.VISIBLE);
                goodsSpecificationDialog.show(getSupportFragmentManager(), goodsSpecificationDialogTag);
            }
        });

        mBinding.tvCommentAll.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString(KEY_GOODS_ID, goodsId);
            Starter.startGoodsCommentActivity(GoodsDetailActivity.this, bundle);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_detail;
    }
}
