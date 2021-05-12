package com.goldensky.vip.activity.goods;

import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.goldensky.framework.util.CollectionUtils;
import com.goldensky.framework.util.GsonUtils;
import com.goldensky.framework.util.MathUtils;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.framework.util.ToastUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.adapter.ConfirmOrderAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.base.ui.dialog.SelectAddressDialog;
import com.goldensky.vip.bean.AddOrderReqBean;
import com.goldensky.vip.bean.ConfirmOrderItemBean;
import com.goldensky.vip.bean.UserAddressBean;
import com.goldensky.vip.constant.BusinessConstant;
import com.goldensky.vip.constant.ConfigConstant;
import com.goldensky.vip.databinding.ActivityConfirmOrderBinding;
import com.goldensky.vip.event.AddAddressEvent;
import com.goldensky.vip.event.PurchaseNumChangeEvent;
import com.goldensky.vip.event.RetrieveAddressEvent;
import com.goldensky.vip.helper.AccountHelper;
import com.goldensky.vip.helper.UserAddressHelper;
import com.goldensky.vip.viewmodel.goods.ConfirmOrderViewModel;
import com.google.gson.reflect.TypeToken;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/11 14:08
 * 包名： com.goldensky.vip.activity.goods
 * 类说明：
 */
public class ConfirmOrderActivity extends BaseActivity<ActivityConfirmOrderBinding, ConfirmOrderViewModel> {

    private final ConfirmOrderAdapter confirmOrderAdapter = new ConfirmOrderAdapter();
    private UserAddressBean selectedAddress = null;
    private SelectAddressDialog selectAddressDialog;
    private boolean showDefaultAddress = false;

    public static final String KEY_ADDRESS = "KEY_ADDRESS";
    public static final String KEY_GOODS = "KEY_GOODS";

    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mBinding.rvGoods.setAdapter(confirmOrderAdapter);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            // 检查地址信息
            String addressJson = bundle.getString(KEY_ADDRESS);
            if (!StringUtils.isTrimEmpty(addressJson)) {
                selectedAddress = GsonUtils.fromJson(addressJson, UserAddressBean.class);
                drawAddress();
            }

            // 获取商品列表
            String goodsJson = bundle.getString(KEY_GOODS);
            List<ConfirmOrderItemBean> confirmOrderItemBeans = GsonUtils.fromJson(goodsJson,
                    new TypeToken<List<ConfirmOrderItemBean>>(){}.getType());
            confirmOrderAdapter.setNewInstance(confirmOrderItemBeans);
        }

        retrieveAddress();
    }

    @Override
    public void observe() {
        mViewModel.userAddressLive.observe(this, this::handleAddress);
        mViewModel.submitOrderLive.observe(this, o -> {
            // TODO 支付
            IWXAPI api = WXAPIFactory.createWXAPI(ConfirmOrderActivity.this, ConfigConstant.WX_APP_ID);
            api.registerApp(ConfigConstant.WX_APP_ID);

            PayReq payReq = new PayReq();
            payReq.appId = ConfigConstant.WX_APP_ID;
//            payReq.partnerId =
            payReq.prepayId = o.get("package").getAsString().substring(10);
            payReq.packageValue = "Sign=WXPay";
            payReq.nonceStr = o.get("nonceStr").getAsString();
            payReq.timeStamp = o.get("timeStamp").getAsString();
            payReq.sign = o.get("paySign").getAsString();
            payReq.signType = o.get("signType").getAsString();

            api.sendReq(payReq);
        });
    }

    private void retrieveAddress() {
        List<UserAddressBean> userAddressBeans = UserAddressHelper.getInstance().getUserAddressList();
        if (CollectionUtils.nullOrEmpty(userAddressBeans)) {
            // 请求网络
            mViewModel.getUserAddress(AccountHelper.getUserId());
        } else {
            handleAddress(userAddressBeans);
        }
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

        // 检查是否展示过默认地址
        if (!showDefaultAddress) {
            showDefaultAddress = true;
            showDefaultAddress(userAddresses);
        }

        if (!CollectionUtils.nullOrEmpty(userAddresses)) {
            selectAddressDialog.setData(userAddresses);;
        }
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
                drawAddress();
                return;
            }
        }

        selectedAddress = userAddresses.get(0);
        drawAddress();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPurchaseNumChanged(PurchaseNumChangeEvent purchaseNumChangeEvent) {
        List<ConfirmOrderItemBean> confirmOrderItemBeanList = confirmOrderAdapter.getData();
        // 计算总金额
        double total = 0D;
        for (ConfirmOrderItemBean confirmOrderItemBean : confirmOrderItemBeanList) {
            total = total + confirmOrderItemBean.getPurchaseNum() * confirmOrderItemBean.getPrice();
        }

        mBinding.tvTotalPrice.setText(MathUtils.bigDecimalString(total, 2));
        if (purchaseNumChangeEvent.getNotify()) {
            confirmOrderAdapter.notifyDataSetChanged();
        }
    }

    // 提交订单
    private void submit() {
        AddOrderReqBean addOrderReqBean = new AddOrderReqBean();

        if (selectedAddress == null) {
            ToastUtils.showShort(R.string.text_select_address);
            return;
        }

        addOrderReqBean.setArea(selectedAddress.getArea());
        addOrderReqBean.setAreaId(selectedAddress.getAreaid());
        addOrderReqBean.setProvince(selectedAddress.getProvince());
        addOrderReqBean.setProvinceId(selectedAddress.getProvinceid());
        addOrderReqBean.setCity(selectedAddress.getCity());
        addOrderReqBean.setCityId(selectedAddress.getCityid());
        addOrderReqBean.setUserAddress(selectedAddress.getUseraddress());
        addOrderReqBean.setUserAddressName(selectedAddress.getUseraddressname());
        addOrderReqBean.setUserAddressPhone(selectedAddress.getUseraddressphone());
        addOrderReqBean.setUserId(AccountHelper.getUserId());

        List<ConfirmOrderItemBean> confirmOrderItemBeans = confirmOrderAdapter.getData();
        List<AddOrderReqBean.Commodity> commodities = new ArrayList<>();

        for (ConfirmOrderItemBean confirmOrderItemBean : confirmOrderItemBeans) {
            commodities.add(confirmOrderItemBean.generateCommodity());
        }

        addOrderReqBean.setCommodityList(commodities);

        mViewModel.addOrder(addOrderReqBean);
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
     * 订阅选择地址
     *
     * @param event 事件
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeAddress(RetrieveAddressEvent event) {
        selectedAddress = event.getAddressBean();
        drawAddress();
    }

    private void drawAddress() {
        if (selectedAddress != null) {
            mBinding.tvName.setText(selectedAddress.getUseraddressname());
            mBinding.tvPhone.setText(selectedAddress.getUseraddressphone());
            mBinding.tvArea.setText(selectedAddress.getUseraddress());
            mBinding.tvProCityCou.setText(new StringBuilder().append(selectedAddress.getProvince())
                    .append(selectedAddress.getCity()).append(selectedAddress.getArea()).toString());
        }
    }

    @Override
    public void initListener() {
        mBinding.tab.setBackListener(v -> finish());

        mBinding.rlAddress.setOnClickListener(v -> {
            if (selectAddressDialog != null) {
                selectAddressDialog.show(getSupportFragmentManager(), "selectAddressDialog");
            }
        });

        mBinding.tvSubmit.setOnClickListener(v -> submit());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
