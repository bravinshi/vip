package com.goldensky.vip.activity.goods;

import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.goldensky.vip.R;
import com.goldensky.vip.adapter.GoodsDetailAdapter;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.base.ui.view.FullyLinearLayoutManager;
import com.goldensky.vip.databinding.ActivityGoodsDetailBinding;
import com.goldensky.vip.helper.ImageLoaderHelper;
import com.goldensky.vip.viewmodel.goods.GoodsDetailViewModel;

import java.util.ArrayList;
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
                    .price("￥49.80")
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
                    .price("￥34.90")
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
                    .price("￥29.90")
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
                    .price("￥19.90")
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
                    .price("￥299.00")
                    .title("金天国际金天喝睿酒52度浓香型整箱500ml6瓶礼盒精装送礼纯粮食白酒")
                    .specification("3.75kg/份 1件")
                    .commentNum(56232)
                    .detailIds(new ArrayList<Integer>(){{
                        add(R.mipmap.jiu1);
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
