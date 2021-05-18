package com.goldensky.vip.activity.order;


import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.util.ToastUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.OrderDetailListAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.base.error.FailCallback;
import com.goldensky.vip.base.ui.dialog.LoadingDialog;
import com.goldensky.vip.bean.ExpressBean;
import com.goldensky.vip.bean.GetPaymentOrderResBean;
import com.goldensky.vip.bean.LogisticsBean;
import com.goldensky.vip.bean.LogisticsReqBean;
import com.goldensky.vip.bean.OrderDetailBean;
import com.goldensky.vip.bean.PaymentOrderReqBean;
import com.goldensky.vip.constant.ConfigConstant;
import com.goldensky.vip.databinding.ActivityOrderDetailBinding;
import com.goldensky.vip.event.ChangeOrderStatusEvent;
import com.goldensky.vip.utils.NoScrollStaggeredGridLayoutManager;
import com.goldensky.vip.viewmodel.order.OrderDetailViewModel;
import com.google.gson.JsonObject;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailActivity extends BaseActivity<ActivityOrderDetailBinding, OrderDetailViewModel> implements View.OnClickListener {
    private String orderNumber;
    private ExpressBean express;
    private String[] orderStatus = {"待付款","待发货","待收货","已完成","已关闭","已取消"};
    private int orderType;
    private OrderDetailBean orderDetail=new OrderDetailBean(1);
    private OrderDetailListAdapter adapter;
    private List<OrderDetailBean.OrderDetailListDTO> list=new ArrayList<>();
    private LoadingDialog loadingDialog=new LoadingDialog();
    public static final String KEY_ADDRESS = "KEY_ADDRESS";
    public static final String KEY_GOODS = "KEY_GOODS";
    private LogisticsBean logistics;
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        Intent intent = getIntent();
        orderNumber = intent.getStringExtra("orderNumber");
        orderType = intent.getIntExtra("orderType", 0);
        mBinding.tvStatuOrderdetail.setText(orderStatus[orderType]);
        mBinding.setDetailBean(orderDetail);
        mBinding.rvOrderdetail.setLayoutManager(new NoScrollStaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        adapter=new OrderDetailListAdapter(list,orderType);
        mBinding.topBarOrderdetail.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter.addChildClickViewIds(new int[]{R.id.go_evaluated_item_order_detail});
        adapter.setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public void onItemChildClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                if(view.getId()==R.id.go_evaluated_item_order_detail){
                    Bundle bundle = new Bundle();
                    bundle.putString("PRODUCT_URL_KEY",orderDetail.getOrderDetailList().get(position).getInventorypic());
                    bundle.putString("PRODUCT_SECOND_ORDERID_KEY",orderDetail.getOrderDetailList().get(position).getSecondorderid());
                    Starter.startOrderCommentActivity(OrderDetailActivity.this,bundle);
                }
            }
        });;
        mBinding.rvOrderdetail.setAdapter(adapter);
        mBinding.setListener(this);


    }


    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.getOrderDetail(orderNumber);
       switch (orderType){
           case 2:
               mViewModel.getExpress(orderNumber);
               break;
       }
    }

    @Override
    public void observe() {
        mViewModel.orderDetailLive.observe(this, new Observer<OrderDetailBean>() {
            @Override
            public void onChanged(OrderDetailBean orderDetailBean) {
                list.clear();
                list.addAll(orderDetailBean.getOrderDetailList());
                adapter.notifyDataSetChanged();
                orderDetail.setCity(orderDetailBean.getCity());
                orderDetail.setCreatetime(orderDetailBean.getCreatetime());
                orderDetail.setOrdernumber(orderDetailBean.getOrdernumber());
                orderDetail.setOrderprice(orderDetailBean.getOrderpriceD());
                orderDetail.setPickuptype(orderDetailBean.getPickuptypeI());
                orderDetail.setPostage(orderDetailBean.getPostageD());
                orderDetail.setProvince(orderDetailBean.getProvince());
                orderDetail.setUseraddress(orderDetailBean.getUseraddress());
                orderDetail.setUseraddressname(orderDetailBean.getUseraddressname());
                orderDetail.setUseraddressphone(orderDetailBean.getUseraddressphone());
                orderDetail.setOrderDetailList(orderDetailBean.getOrderDetailList());
                orderDetail.setArea( orderDetailBean.getArea());
                orderDetail.notifyChange();
                mBinding.orderTimeOrderdetail.setText(orderDetailBean.getCreatetime());
                switch (orderType){
                    case 0:
                        mBinding.tvPayOrderdetail.setText(getResources().getText(R.string.text_due));
                        mBinding.cancelOrPaymentOrderdetail.setVisibility(View.VISIBLE);
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.clLogisticsOrderdetail.setVisibility(View.GONE);
                        mBinding.btnMethedRight.setText(getResources().getText(R.string.text_go_pay));
                        mBinding.btnMethedLeft.setText(getResources().getText(R.string.text_cancel_order));
                        mBinding.ivStatuOrderdetail.setImageResource(R.mipmap.img_wddd_dfk);
                        break;
                    case 1:
                        mBinding.tvPayOrderdetail.setText(getResources().getText(R.string.text_actual_payment));
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.ivStatuOrderdetail.setImageResource(R.mipmap.img_wddd_ywc);
                        mBinding.clLogisticsOrderdetail.setVisibility(View.GONE);
                        mBinding.cancelOrPaymentOrderdetail.setVisibility(View.GONE);
                        break;
                    case 2:
                        mBinding.cancelOrPaymentOrderdetail.setVisibility(View.VISIBLE);
                        mBinding.clLogisticsOrderdetail.setVisibility(View.VISIBLE);
                        mBinding.tvPayOrderdetail.setText(getResources().getText(R.string.text_actual_payment));
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.btnMethedLeft.setVisibility(View.GONE);
                        mBinding.btnMethedRight.setText(getResources().getText(R.string.text_confirm_receipt));
                        mBinding.ivStatuOrderdetail.setImageResource(R.mipmap.img_wddd_dsh);
                        break;
                    case 3:
                        mBinding.tvPayOrderdetail.setText(getResources().getText(R.string.text_actual_payment));
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.ivStatuOrderdetail.setImageResource(R.mipmap.img_wddd_ywc);
                        mBinding.clLogisticsOrderdetail.setVisibility(View.GONE);
                        mBinding.cancelOrPaymentOrderdetail.setVisibility(View.GONE);
                        break;
                    case 4:
                        mBinding.tvPayOrderdetail.setText(getResources().getText(R.string.text_actual_payment));
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.ivStatuOrderdetail.setImageResource(R.mipmap.img_wddd_ywc);
                        mBinding.clLogisticsOrderdetail.setVisibility(View.GONE);
                        mBinding.cancelOrPaymentOrderdetail.setVisibility(View.GONE);
                        break;
                    case 5:
                        mBinding.tvPayOrderdetail.setText(getResources().getText(R.string.text_due));
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.ivStatuOrderdetail.setImageResource(R.mipmap.img_wddd_ywc);
                        mBinding.clLogisticsOrderdetail.setVisibility(View.GONE);
                        mBinding.cancelOrPaymentOrderdetail.setVisibility(View.GONE);
                        break;
                }
            }
        });
        mViewModel.updateOrderLive.observe(this, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                switch (orderType){
                    case 0:
                        orderType=5;
                        mBinding.tvStatuOrderdetail.setText(orderStatus[orderType]);
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.ivStatuOrderdetail.setImageResource(R.mipmap.img_wddd_ywc);
                        mBinding.clLogisticsOrderdetail.setVisibility(View.GONE);
                        mBinding.cancelOrPaymentOrderdetail.setVisibility(View.GONE);
                        break;
                    case 2:
                        orderType=3;
                        mBinding.clLogisticsOrderdetail.setVisibility(View.GONE);
                        mBinding.tvStatuOrderdetail.setText(orderStatus[orderType]);
                        mBinding.ivStatuOrderdetail.setImageResource(R.mipmap.img_wddd_ywc);
                        mBinding.countDownOrderdetail.setVisibility(View.GONE);
                        mBinding.cancelOrPaymentOrderdetail.setVisibility(View.GONE);
                        break;
                }
                EventBus.getDefault().post(new ChangeOrderStatusEvent(true));
            }
        });
        mViewModel.paymentOrderLive.observe(this, new Observer<GetPaymentOrderResBean>() {
            @Override
            public void onChanged(GetPaymentOrderResBean jsonObject) {
                IWXAPI api = WXAPIFactory.createWXAPI(OrderDetailActivity.this, ConfigConstant.WX_APP_ID, false);
                api.registerApp(ConfigConstant.WX_APP_ID);

                PayReq payReq = new PayReq();
                payReq.appId = ConfigConstant.WX_APP_ID;
                payReq.partnerId = ConfigConstant.WX_MCH_ID;
                payReq.prepayId = jsonObject.getPrepayId();
                payReq.packageValue = "Sign=WXPay";
                payReq.nonceStr = jsonObject.getNoncestr();
                payReq.timeStamp = jsonObject.getTimestamp();
                payReq.sign = jsonObject.getPaySign();
                payReq.signType = "MD5";

                api.sendReq(payReq);
                loadingDialog.dismissAllowingStateLoss();
                ToastUtils.showShort("正在发起支付请求");
            }
        });
        mViewModel.expressLive.observe(this, new Observer<ExpressBean>() {
            @Override
            public void onChanged(ExpressBean expressBean) {
                express=expressBean;
                LogisticsReqBean bean = new LogisticsReqBean();
                bean.setTo(expressBean.getProvince()+expressBean.getCity()+expressBean.getArea());
                bean.setCom(expressBean.getExpresscode());
                bean.setNum(expressBean.getExpressnumber());
                bean.setPhone(expressBean.getUseraddressphone());
                bean.setOrder("desc");
                bean.setResultv2("1");
                bean.setShow("0");
                bean.setFrom("");
                mViewModel.getLogistics(bean);
            }
        });
        mViewModel.getLogisticsLive.observe(this, new Observer<LogisticsBean>() {
            @Override
            public void onChanged(LogisticsBean logisticsBean) {
                logistics=logisticsBean;
//                0在途，1揽收，2疑难，3签收，4退签，5派件，6退回，7转单，10待清关，11清关中，12已清关，13清关异常，14收件人拒签
                String[] logisticStatus={"在途","揽收","疑难","签收","退签","派件","退回","转单","待揽收","","待清关","清关中","已清关","清关异常","收件人拒签"};
                int status=0;
                if(logisticsBean.getState()!=null){
                    status=Integer.parseInt(logisticsBean.getState());
                }else {
                    status=8;
                }
                mBinding.logisticsStatuOrderdetail.setText(logisticStatus[status]);
                if(logisticsBean.getData()!=null){
                    mBinding.logisticsAddressOrderdetail.setText(logisticsBean.getData().get(0).getContext());
                }else {
                    mBinding.logisticsAddressOrderdetail.setText("暂无物流信息");
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_methed_right:
                switch (orderType){
                    case 0://去支付
                        loadingDialog.show(getSupportFragmentManager(),"loadingDialog");
                        PaymentOrderReqBean reqBean = new PaymentOrderReqBean();
                        List<String> orderNumberList = new ArrayList<>();
                        orderNumberList.add(orderDetail.getOrdernumber());
                        reqBean.setOrderNumberList(orderNumberList);
                        reqBean.setPayType(0);
                        reqBean.setRechargeMoney(orderDetail.getSum());
                        mViewModel.getPaymentOrder(reqBean, new FailCallback() {
                            @Override
                            public void onFail(NetResponse netResponse) {
                                loadingDialog.dismissAllowingStateLoss();
                            }
                        });
                        break;
                    case 2://确认收货
                        mViewModel.updateOrder(orderDetail.getOrdernumber(),3);
                        break;
                }
                break;
            case R.id.btn_methed_left://取消订单
                mViewModel.updateOrder(orderDetail.getOrdernumber(),5);
                break;
            case R.id.check_logistics_orderdetail://查看物流信息
                Bundle bundle = new Bundle();
                bundle.putSerializable("logistics",logistics);
                bundle.putString("pic",orderDetail.getOrderDetailList().get(0).getInventorypic());
                Starter.startLogisticsActivity(this,bundle);
                break;
            case R.id.tv_copy_orderdetail:
                ClipboardManager cmb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                cmb.setText(orderNumber);
                toast("复制成功");
                break;
        }
    }
}