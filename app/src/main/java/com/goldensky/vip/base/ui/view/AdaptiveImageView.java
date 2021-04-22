package com.goldensky.vip.base.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.widget.NestedScrollView;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/22 13:35
 * 包名： com.goldensky.vip.base.ui.view
 * 类说明：
 */
public class AdaptiveImageView extends AppCompatImageView {

    public AdaptiveImageView(Context context) {
        super(context);
    }

    public AdaptiveImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdaptiveImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        Drawable d = getDrawable();

        if(d!=null){

            int mode = MeasureSpec.getMode(widthMeasureSpec);
            if (mode == MeasureSpec.EXACTLY) {
                // ceil not round - avoid thin vertical gaps along the left/right edges
                int width = MeasureSpec.getSize(widthMeasureSpec);
                //高度根据使得图片的宽度充满屏幕计算而得
                int height = (int) Math.ceil((float) width * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());
                setMeasuredDimension(width, height);
                return;
            } else if (mode == MeasureSpec.AT_MOST
            || mode == MeasureSpec.UNSPECIFIED) {
                ViewGroup parent = (ViewGroup) getParent();
                int parentWidth = parent.getRight() - parent.getLeft() - parent.getPaddingStart() - parent.getPaddingEnd();
                int height = (int) Math.ceil((float) parentWidth * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());
                setMeasuredDimension(parentWidth, height);
                return;
            }

            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
