package com.goldensky.framework.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.goldensky.framework.R;
import com.goldensky.framework.util.Utils;

public class MeasureFixedLayout extends FrameLayout {

    private boolean halfHeight = false;

    public MeasureFixedLayout(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public MeasureFixedLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MeasureFixedLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MeasureFixedLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private int screenHeight;

    private void init(@NonNull Context context, @Nullable AttributeSet attrs) {
        WindowManager wm = (WindowManager) Utils.getApp().getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            screenHeight = getContext().getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        } else {
            Point point = new Point();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                wm.getDefaultDisplay().getRealSize(point);
            } else {
                wm.getDefaultDisplay().getSize(point);
            }
            screenHeight = point.y;
        }

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MeasureFixedLayout);
        halfHeight = typedArray.getBoolean(R.styleable.MeasureFixedLayout_measureFixed_half_height, false);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measuredHeight = getMeasuredHeight();

        if (halfHeight || measuredHeight > screenHeight / 2) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(screenHeight / 2, MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
