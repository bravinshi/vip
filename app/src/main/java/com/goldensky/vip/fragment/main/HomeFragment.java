package com.goldensky.vip.fragment.main;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pools;

import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goldensky.vip.R;

import com.goldensky.vip.Starter;
import com.goldensky.vip.activity.accountManager.JsAccountActivity;
import com.goldensky.vip.activity.accountManager.JyAccountActivity;
import com.goldensky.vip.activity.brandcompany.BrandCompanyActivity;
import com.goldensky.vip.activity.order.XsOrderActivity;
import com.goldensky.vip.base.fragment.BaseFragment;
import com.goldensky.vip.databinding.FragmentHomeBinding;
import com.goldensky.vip.viewmodel.home.HomeViewModel;


public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements View.OnClickListener {

    public static boolean isTYST = true; //是否体验实体
//    private LinearLayout llContainer;

    private int[] ids = new int[]{R.mipmap.h_1_icon,R.mipmap.h_2_icon,R.mipmap.h_3_icon,R.mipmap.h_4_icon,R.mipmap.h_5_icon,R.mipmap.h_6_icon,R.mipmap.h_7_icon};

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mBinding.orderLayout.setOnClickListener(this);
        mBinding.sjV.setOnClickListener(this);
        mBinding.styqIv.setOnClickListener(this);
        mBinding.scglIv.setOnClickListener(this);
        mBinding.zyspIv.setOnClickListener(this);
        mBinding.ddglIv.setOnClickListener(this);
        mBinding.allIv.setOnClickListener(this);
        mBinding.jyzhV.setOnClickListener(this);
        mBinding.jszhV.setOnClickListener(this);


        LayoutTransition transition = new LayoutTransition();

        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0, 1);

        ObjectAnimator valueAnimator = ObjectAnimator.ofPropertyValuesHolder(null, new PropertyValuesHolder[]{scaleX, scaleY})
                .setDuration(transition.getDuration(LayoutTransition.APPEARING));
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                ObjectAnimator objectAnimator = (ObjectAnimator) animation;
                View view = (View) objectAnimator.getTarget();
                view.setPivotX(0f);
                view.setPivotY(view.getMeasuredHeight());
            }
        });

//        transition.setAnimator(LayoutTransition.APPEARING, valueAnimator);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(null, "alpha", 0, 1).setDuration(LayoutTransition.DISAPPEARING);
        transition.setAnimator(LayoutTransition.DISAPPEARING, objectAnimator);
        mBinding.llContainer.setLayoutTransition(transition);

//
//        for (int i = 0; i < ids.length; i++) {
//            ImageView imageView = obtainImgeView();
//            imageView.setImageResource(ids[i]);
//            mBinding.llContainer.addView(imageView);
//        }

    }


    @Override
    public void onResume() {
        super.onResume();
        if (isTYST) {
            mBinding.iv1.setImageResource(R.mipmap.home_tiyan_top);
            mBinding.iv4.setImageResource(R.mipmap.my_pic_wodegongju_ty);
            mBinding.iv6.setVisibility(View.INVISIBLE);
        } else {
            mBinding.iv1.setImageResource(R.mipmap.my_pic_shouye1);
            mBinding.iv4.setImageResource(R.mipmap.my_pic_wodegongju);
            mBinding.iv6.setVisibility(View.VISIBLE);
            handler.sendEmptyMessage(0);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (!isTYST) {
            handler.removeMessages(0);
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.order_layout:
                if (isTYST) return;
                Starter.startSxOrderActivity(getContext());
                break;
            case R.id.sj_v:
                if (isTYST) {
                    Starter.startSjstActivity(getContext());
                } else {
                    Intent compInten = new Intent(getContext(), BrandCompanyActivity.class);
                    startActivity(compInten);
                }
                break;
            case R.id.jyzh_v:
                Starter.startJyAccountActivity(getContext());
                break;
            case R.id.jszh_v:
                Starter.startJsAccountActivity(getContext());
                break;
            case R.id.styq_iv: //VIP管理

                Starter.startVipManageActivity(getContext(),null);
                break;
            case R.id.scgl_iv: //商城管理
                if (isTYST) {
                    Starter.startMallManageTestActivity(getContext(),null);
                } else {
                    Starter.startMallMangeActivity(getContext(),null);
                }

                break;
            case R.id.zysp_iv://主营商品
                if (isTYST) {
                    Starter.startJhActivity(getContext());
                } else {
                    Starter.startMainGoodsActivity(getContext(),null);
                }
                break;
            case R.id.ddgl_iv: //订单管理
                Starter.startOrderListActivity(getContext(),null);
                break;
            case R.id.all_iv: //全部
                if (isTYST) {
                    Starter.startMyToolsTestActivity(getContext());
                } else {
                    Starter.startMyToolsActivity(getContext(),null);
                }
                break;
        }
    }


//    private int[] ids = new int[]{R.mipmap.h_1_icon,R.mipmap.h_2_icon,R.mipmap.h_3_icon,R.mipmap.h_4_icon};

    Pools.SimplePool<ImageView> textViewPool = new Pools.SimplePool<>(ids.length);

    private ImageView obtainImgeView() {
        ImageView imageView = textViewPool.acquire();
        if (imageView == null) {
            imageView = new ImageView(getContext());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//            imageView.setPadding(dp2px(15), dp2px(10), dp2px(15), dp2px(10));
            imageView.setAdjustViewBounds(true);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        return imageView;
    }

    private int dp2px(float dp) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics);
    }


    int index = 7;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @SuppressLint("ResourceAsColor")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 0 && mBinding.llContainer.getChildCount() == 5) {
                mBinding.llContainer.removeViewAt(0);
            }
            if (index == 7) {
                index = 0;
            }
            ImageView imageView = obtainImgeView();
            imageView.setImageResource(ids[index]);

            mBinding.llContainer.addView(imageView);
            sendEmptyMessageDelayed(0, 2000);
            index++;
        }
        ;
    };

}