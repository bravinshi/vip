package com.goldensky.framework.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.goldensky.framework.R;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/11 11:33
 * 包名： com.goldensky.basekit.ui.view
 * 类说明：
 */
public class TopActionBar extends LinearLayout {
    private ImageView tvBack;
    private TextView tvCenter;
    private TextView tvRight;

    public TopActionBar(Context context) {
        this(context, null, 0);
    }

    public TopActionBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopActionBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopActionBar);
        String centerText = typedArray.getString(R.styleable.TopActionBar_centerText);
        String rightText = typedArray.getString(R.styleable.TopActionBar_rightText);
        Boolean showLeftIcon = typedArray.getBoolean(R.styleable.TopActionBar_rightText,true);
        int centerColor = typedArray.getColor(R.styleable.TopActionBar_centerTextColor,
                getResources().getColor(R.color.colorText));
        tvCenter.setText(centerText);
        tvCenter.setTextColor(centerColor);
        tvRight.setText(rightText);
        if(showLeftIcon){
            tvRight.setVisibility(VISIBLE);
        }else {
            tvRight.setVisibility(GONE);
        }

        typedArray.recycle();
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.top_bar_layout, this);
        tvBack = findViewById(R.id.iv_back);
        tvCenter = findViewById(R.id.tv_center);
        tvRight = findViewById(R.id.tv_right);
    }

    public void setBackListener(OnClickListener listener){
        tvBack.setOnClickListener(listener);
    }

    public void setRightListener(OnClickListener listener){
        tvRight.setVisibility(VISIBLE);
        tvRight.setOnClickListener(listener);
    }

    public void setCenterHint(String hint) {
        tvCenter.setText(hint);
    }
}
