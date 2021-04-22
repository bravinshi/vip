package com.goldensky.vip.base.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/22 15:19
 * 包名： com.goldensky.vip.base.ui.view
 * 类说明：
 */
public class CustomRecyclerView extends RecyclerView {
    public CustomRecyclerView(@NonNull Context context) {
        super(context);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        heightSpec = MeasureSpec.makeMeasureSpec(9999, MeasureSpec.EXACTLY);
        super.onMeasure(widthSpec, heightSpec);
    }
}
