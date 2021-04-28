package com.goldensky.vip.activity.goods;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.goldensky.vip.R;
import com.goldensky.vip.Starter;
import com.goldensky.vip.adapter.GoodsDetailAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.base.ui.view.FullyLinearLayoutManager;
import com.goldensky.vip.databinding.ActivityGoodsDetail1Binding;
import com.goldensky.vip.databinding.ActivityGoodsDetailBinding;
import com.goldensky.vip.helper.ImageLoaderHelper;
import com.goldensky.vip.viewmodel.goods.GoodsDetailViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/14 14:21
 * 包名： com.goldensky.vip.activity.goods
 * 类说明：
 */
public class GoodsDetailActivity extends BaseActivity<ActivityGoodsDetailBinding,
        GoodsDetailViewModel> {

    public static final String KEY_FAKE_DATA = "KEY_FAKE_DATA";

    public static final Integer KEY_FAKE_DATA_APPLE = 1;// 苹果
    public static final Integer KEY_FAKE_DATA_MANGO = 2;// 芒果
    public static final Integer KEY_FAKE_DATA_EGG = 3;// 鸭蛋
    public static final Integer KEY_FAKE_DATA_SAUSAGE = 4;// 红肠
    public static final Integer KEY_FAKE_DATA_WINE = 5;// 酒
    public static final Integer KEY_FAKE_DATA_ORANGE = 6;// 橘子
    public static final Integer KEY_FAKE_DATA_MANGUODAO = 7;// 芒果刀
    public static final Integer KEY_FAKE_DATA_ZHAZHIBEI = 8;// 榨汁机
    public static final Integer KEY_FAKE_DATA_YOUYUTIAO = 9;// 鱿鱼条
    public static final Integer KEY_FAKE_DATA_XIAOBINGGAN = 10;// 小饼干
    public static final Integer KEY_FAKE_DATA_SHUJUXIAN = 11;// 数据线
    public static final Integer KEY_FAKE_DATA_XIAOSHUIBEI = 12;// 小水杯
    public static final Integer KEY_FAKE_DATA_CANJU = 13;// 餐具
    public static final Integer KEY_FAKE_DATA_FENGMI = 14;// 蜂蜜
    public static final Integer KEY_FAKE_DATA_DANGAO = 15;// 蛋糕
    public static final Integer KEY_FAKE_DATA_YUNDOU = 16;// 熨斗



    @Override
    public void onFinishInit(Bundle savedInstanceState) {



        LinearLayoutManager mLinearLayoutManager = new FullyLinearLayoutManager(GoodsDetailActivity.this);
//        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        mBinding.rvDetail.setLayoutManager(mLinearLayoutManager);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            generateFakeData(1);
            return;
        }

        Integer fakeKey = bundle.getInt(KEY_FAKE_DATA, 0);
        generateFakeData(fakeKey);
    }

    public void generateFakeData(Integer fakeKey) {

        if (fakeKey == null) {
            return;
        }

        FakeData fakeData = FakeData.create();

        if (fakeKey.equals(KEY_FAKE_DATA_APPLE)) {
            fakeData.mainImage(R.mipmap.pingguozhutu)
                    .price("49.80")
                    .title("山东红富士高端礼盒顺丰包邮整箱3.7KG水果苹果脆甜")
                    .specification("3.75kg/份 1件")
                    .commentNum(2121)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.pingguo1);
                        add(R.mipmap.pingguo2);
                        add(R.mipmap.pingguo3);
                    }});
        } else if (fakeKey.equals(KEY_FAKE_DATA_MANGO)) {
            fakeData.mainImage(R.mipmap.mangguozhutu)
                    .price("34.90")
                    .title("三亚芒果新鲜采摘顺丰包邮整箱5KG热带水果香甜多汁")
                    .specification("3.75kg/份 1件")
                    .commentNum(9852)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.mangguo1);
                        add(R.mipmap.mangguo2);
                        add(R.mipmap.mangguo3);
                    }});
        } else if (fakeKey.equals(KEY_FAKE_DATA_EGG)) {
            fakeData.mainImage(R.mipmap.yadanzhutu)
                    .price("29.90")
                    .title("正宗骆马湖咸鸭蛋纯天然多有鸭蛋20枚/40枚/60枚")
                    .specification("3.75kg/份 1件")
                    .commentNum(121)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.yadan1);
                        add(R.mipmap.yadan2);
                        add(R.mipmap.yadan3);
                    }});
        } else if (fakeKey.equals(KEY_FAKE_DATA_SAUSAGE)) {
            fakeData.mainImage(R.mipmap.hongchangzhutu)
                    .price("19.90")
                    .title("正宗哈尔滨红肠熏火腿肠香肠腊肠方便速食老字号东北特产")
                    .specification("3.75kg/份 1件")
                    .commentNum(6554)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.hongchang1);
                        add(R.mipmap.hongchang2);
                        add(R.mipmap.hongchang3);
                    }});
        } else if (fakeKey.equals(KEY_FAKE_DATA_WINE)) {
            fakeData.mainImage(R.mipmap.jiuzhutu)
                    .price("299.00")
                    .title("金天国际金天喝睿酒52度浓香型整箱500ml6瓶礼盒精装送礼纯粮食白酒")
                    .specification("3.75kg/份 1件")
                    .commentNum(56232)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.jiu1);
                    }});
        }else if (fakeKey.equals(KEY_FAKE_DATA_ORANGE)) {
            fakeData.mainImage(R.mipmap.chengzi1)
                    .price("30.80")
                    .title("正宗赣南脐橙赣州显现采摘当季水果2.5KG手剥冰糖橙")
                    .specification("2.5kg/份 1件")
                    .commentNum(8829)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.chengzi2);
                        add(R.mipmap.chengzi3);
                        add(R.mipmap.chengzi4);
                        add(R.mipmap.chengzi5);
                    }});
        }else if (fakeKey.equals(KEY_FAKE_DATA_MANGUODAO)) {
            fakeData.mainImage(R.mipmap.mangguodao1)
                    .price("19.90")
                    .title("切芒果神器多功能削芒果剥皮分离器专用刀切芒果丁挖勺取肉")
                    .specification("芒果取肉器")
                    .commentNum(6398)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.mangguodao2);
                        add(R.mipmap.mangguodao3);
                        add(R.mipmap.mangguodao4);
                        add(R.mipmap.mangguodao5);
                    }});
        }else if (fakeKey.equals(KEY_FAKE_DATA_ZHAZHIBEI)) {
            fakeData.mainImage(R.mipmap.zhazhiji1)
                    .price("159.80")
                    .title("榨汁机家用迷你多功能小型便携式迷你果汁水果榨汁杯")
                    .specification("粉色")
                    .commentNum(8829)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.zhazhiji2);
                        add(R.mipmap.zhazhiji2);
                        add(R.mipmap.zhazhiji3);
                    }});
        }else if (fakeKey.equals(KEY_FAKE_DATA_YOUYUTIAO)) {
            fakeData.mainImage(R.mipmap.youyutiao1)
                    .price("14.80")
                    .title("碳烤鱿鱼条原味香辣味即食零食年货新鲜现烤现发")
                    .specification("60g原丝")
                    .commentNum(3829)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.youyutiao2);
                        add(R.mipmap.youyutiao3);
                        add(R.mipmap.youyutiao4);
                    }});
        }else if (fakeKey.equals(KEY_FAKE_DATA_XIAOBINGGAN)) {
            fakeData.mainImage(R.mipmap.binggan1)
                    .price("46.00")
                    .title("日式小圆饼干1kg多口味海盐小圆饼干零食休闲食品整箱")
                    .specification("1.5kg/一箱")
                    .commentNum(9846)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.binggan2);
                        add(R.mipmap.binggan3);
                        add(R.mipmap.binggan4);
                    }});
        }else if (fakeKey.equals(KEY_FAKE_DATA_SHUJUXIAN)) {
            fakeData.mainImage(R.mipmap.shujuxian1)
                    .price("9.90")
                    .title("品胜USB-C数据线1m/1.5m/2m黑白双色标准数据线")
                    .specification("1.5m白色 1件")
                    .commentNum(131321)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.shujuxian2);
                        add(R.mipmap.shujuxian3);
                        add(R.mipmap.shujuxian4);
                    }});
        }else if (fakeKey.equals(KEY_FAKE_DATA_XIAOSHUIBEI)) {
            fakeData.mainImage(R.mipmap.my_pic_bolibeizhutu)
                    .price("15.90")
                    .title("高硼硅耐热玻 带把喝水杯子 牛奶杯 泡茶杯 六支装260ML")
                    .specification("不带杯盖 1件")
                    .commentNum(211119)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.my_pic_bolibei1);
                        add(R.mipmap.my_pic_bolibe2);
                        add(R.mipmap.my_pic_bolibei3);
                    }});
        }else if (fakeKey.equals(KEY_FAKE_DATA_CANJU)) {
            fakeData.mainImage(R.mipmap.my_pic_taociwanzhutu)
                    .price("586.00")
                    .title("景德镇陶瓷碗餐具套装陶瓷碗碟套装太阳岛20头碗盘碟微波炉适用")
                    .specification("3.7kg/份 1件")
                    .commentNum(2138)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.my_pic_taociwan1);
                        add(R.mipmap.my_pic_taociwa2);
                        add(R.mipmap.my_pic_taociwan3);
                    }});
        }else if (fakeKey.equals(KEY_FAKE_DATA_FENGMI)) {
            fakeData.mainImage(R.mipmap.my_pic_fengmizhutu)
                    .price("29.90")
                    .title("蜂蜜正品农家纯天然琵琶蜂蜜京蜂蜂蜜无添加120ml")
                    .specification("枇杷蜂蜜 1件")
                    .commentNum(213)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.my_pic_fengmi1);
                        add(R.mipmap.my_pic_fengmi2);
                        add(R.mipmap.my_pic_fengmi3);
                        add(R.mipmap.my_pic_fengmi4);
                    }});
        }else if (fakeKey.equals(KEY_FAKE_DATA_DANGAO)) {
            fakeData.mainImage(R.mipmap.my_pic_dangaozhutu)
                    .price("14.90")
                    .title("鸡蛋松饼松可蛋糕焗式蛋糕糕点休闲小吃零食面包鸡蛋糕")
                    .specification("500g 1件")
                    .commentNum(219)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.my_pic_dangao1);
                        add(R.mipmap.my_pic_dangao2);
                    }});
        }else if (fakeKey.equals(KEY_FAKE_DATA_YUNDOU)) {
            fakeData.mainImage(R.mipmap.my_pic_yundouzhutu)
                    .price("244.90")
                    .title("熨斗界的颜值担当手持挂烫机智能蒸汽加热家用迷你型烫衣机小型电熨斗便携式")
                    .specification("标准版 1件")
                    .commentNum(8229)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.my_pic_yundou1);
                        add(R.mipmap.my_pic_yundou2);
                    }});
        }

        showFakeData(fakeData);
        mBinding.topBarGoodsDetail.setBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showFakeData(FakeData data) {
        ImageLoaderHelper.loadImage(mBinding.ivMain, data.mainImage);
        mBinding.tvPrice.setText(data.price);
        mBinding.tvTitle.setText(data.title);
        mBinding.tvSpecification.setText(data.specification);
        mBinding.tvCommentNum.setText("(" + data.commentNum + ")");
        mBinding.tvPrice.setText(data.price);

        GoodsDetailAdapter goodsDetailAdapter = new GoodsDetailAdapter();
        mBinding.rvDetail.setAdapter(goodsDetailAdapter);

        List<Integer> detailData = new ArrayList<Integer>(){{
            addAll(data.detailIds);
        }};
        mBinding.tvJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("加入购物车成功");
            }
        });
        mBinding.ivShareGoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View inflate = LayoutInflater.from(GoodsDetailActivity.this).inflate(R.layout.pop_share,null);
                ImageView ivShare = inflate.findViewById(R.id.iv_share);
                Glide.with(GoodsDetailActivity.this).load(R.mipmap.my_pic_fenxiang).into(ivShare);
                PopupWindow popupWindow = new PopupWindow(inflate, LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
                ivShare.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });
                Window window = getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.alpha=0.1f;
                window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                window.setAttributes(attributes);
                window.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setTouchable(true);
                popupWindow.setOutsideTouchable(true);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        attributes.alpha=1.0f;
                        window.setAttributes(attributes);
                    }
                });
                popupWindow.showAtLocation(mBinding.bottomView, Gravity.TOP, 0,0);
            }
        });
        goodsDetailAdapter.setPics(detailData);
    }

    public static class FakeData {
        Integer mainImage;
        String price;
        String title;
        String specification;
        Integer commentNum;
        List<Integer> detailIds;

        public static FakeData create() {
            return new FakeData();
        }

        public FakeData mainImage(Integer mainImage) {
            this.mainImage = mainImage;
            return this;
        }

        public FakeData commentNum(Integer commentNum) {
            this.commentNum = commentNum;
            return this;
        }

        public FakeData price(String price) {
            this.price = price;
            return this;
        }

        public FakeData title(String title) {
            this.title = title;
            return this;
        }

        public FakeData specification(String specification) {
            this.specification = specification;
            return this;
        }

        public FakeData detailIds(List<Integer> detailIds) {
            this.detailIds = detailIds;
            return this;
        }
    }

    @Override
    public void observe() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods_detail;
    }
}
