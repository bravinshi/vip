package com.goldensky.vip.base.ui.view;

import android.content.Context;
import android.util.AttributeSet;

import com.goldensky.vip.R;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/10 8:54
 * 包名： com.goldensky.vip.base.ui.view
 * 类说明：
 */
public class SpecificationItemView extends androidx.appcompat.widget.AppCompatTextView {
    public static final Integer SELECT_STATE_SELECTED = 1;// 选中
    public static final Integer SELECT_STATE_UNSELECTED = 2;// 未选中
    public static final Integer SELECT_STATE_NOT_AVAILABLE = 3;// 不可用

    public SpecificationItemView(Context context) {
        super(context);
    }

    public SpecificationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpecificationItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSelectState(Integer state) {
        if (state.equals(SELECT_STATE_SELECTED)) {
            setTextColor(getContext().getResources().getColor(R.color.color_EA483F));
            setBackground(getContext().getDrawable(R.drawable.sepecification_item_selected));
        } else if (state.equals(SELECT_STATE_UNSELECTED)) {
            setTextColor(getContext().getResources().getColor(R.color.color_3));
            setBackground(getContext().getDrawable(R.drawable.sepecification_item_unselected));
        } else if (state.equals(SELECT_STATE_NOT_AVAILABLE)) {
            setTextColor(getContext().getResources().getColor(R.color.color_9));
            setBackground(getContext().getDrawable(R.drawable.sepecification_item_unselected));
        } else {
            throw new IllegalArgumentException("the argument of state is illegal");
        }
    }
}
