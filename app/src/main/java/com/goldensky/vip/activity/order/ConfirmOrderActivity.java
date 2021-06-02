package com.goldensky.vip.activity.order;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.goldensky.vip.R;
import com.goldensky.vip.base.activity.BaseActivity;
import com.goldensky.vip.databinding.ActivityConfirmOrderBinding;
import com.goldensky.vip.viewmodel.PublicViewModel;

import static com.goldensky.vip.activity.goods.GoodsDetailActivity.KEY_FAKE_DATA;

public class ConfirmOrderActivity extends BaseActivity<ActivityConfirmOrderBinding, PublicViewModel> implements View.OnClickListener {
    @Override
    public void onFinishInit(Bundle savedInstanceState) {
        mBinding.backV.setOnClickListener(this);
        mBinding.confirmV.setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Integer fakeKey = bundle.getInt(KEY_FAKE_DATA, 0);
            switch (fakeKey) {
                case 1:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_pingguoxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_pingguojiage);
                    break;
                case 2:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_mangguojxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_mangguojiage);
                    break;
                case 3:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_yadanxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_yadanjiage);
                    break;
                case 4:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_hongchangxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_hongchangjiage);
                    break;
                case 5:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_jiuxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_jiujiage);
                    break;
                case 6:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_chengzijiage);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_chengzixinxi);
                    break;
                case 7:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_daoxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_daojiage);
                    break;
                case 8:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_zhazhijixinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_zhazhijijiage);
                    break;
                case 9:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_youyuxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_youyujiage);
                    break;
                case 10:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_bingganxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_bingganjiage);
                    break;
                case 11:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_shujuxianxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_shujuxianjiage);
                    break;
                case 12:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_bolibeixinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_bolibeijiage);
                    break;
                case 13:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_wanxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_wanjiage);
                    break;
                case 14:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_mixinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_mijiage);
                    break;
                case 15:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_dangaoxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_dangaojiage);
                    break;
                case 16:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_yundouxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_yundoujiage);
                    break;
                case 17:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_niunanxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_niunanjiage);
                    break;
                case 18:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_fangshaifuxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_fangshaifujiage);
                    break;
                case 19:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_yusanxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_yusanjiage);
                    break;
                case 20:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_jiuyangjiage);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_jiuyangxinxi);
                    break;
                case 21:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_xiangxunxinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_xiangxunjiage);
                    break;
                case 22:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_buxiexinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_buxiejiage);
                    break;
                case 23:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_shoujixinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_shoujijiage);
                    break;
                case 24:
                    mBinding.spinfoIv.setImageResource(R.mipmap.my_pic_xiyiyexinxi);
                    mBinding.priceIv.setImageResource(R.mipmap.my_pic_xiyiyejiage);
                    break;
                case 25:
                    mBinding.spinfoIv.setImageResource(R.mipmap.qrdd25);
                    mBinding.priceIv.setImageResource(R.mipmap.qrjg25);
                    break;
                case 26:
                    mBinding.spinfoIv.setImageResource(R.mipmap.qrdd25);
                    mBinding.priceIv.setImageResource(R.mipmap.qrjg25);
                    break;
            }
        }
    }

    @Override
    public void observe() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_v:
                finish();
                break;
            case R.id.confirm_v:
                Toast ts = Toast.makeText(this,"订单提交成功",Toast.LENGTH_SHORT);
                ts.setGravity(Gravity.CENTER, 0, 0);
                ts.show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
                break;
        }
    }
}