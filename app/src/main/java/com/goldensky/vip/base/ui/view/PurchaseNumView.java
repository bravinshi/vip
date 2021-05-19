package com.goldensky.vip.base.ui.view;

import android.content.Context;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.DataBindingUtil;

import com.goldensky.framework.util.StringUtils;
import com.goldensky.vip.R;
import com.goldensky.vip.databinding.ViewPurchaseNumBinding;
import com.goldensky.vip.listener.DoNothingTextWatcher;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/19 11:36
 * 包名： com.goldensky.vip.base.ui.view
 * 类说明：
 */
public class PurchaseNumView extends LinearLayout {
    private final PurchaseQuantityModel purchaseQuantityModel = new PurchaseQuantityModel("1");
    private static final int min = 1;
    private static final int max = 999;

    public PurchaseNumView(Context context) {
        super(context);
        init(context);
    }

    public PurchaseNumView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PurchaseNumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public int getPurchaseNum() {
        return purchaseQuantityModel.getPurchaseQuantityInt();
    }

    public void setPurchaseNum(int num) {
        purchaseQuantityModel.setPurchaseQuantity(String.valueOf(num));
    }

    public PurchaseNumView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        ViewPurchaseNumBinding viewPurchaseNumBinding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.view_purchase_num, this, false);

        viewPurchaseNumBinding.setModel(purchaseQuantityModel);
        viewPurchaseNumBinding.btnDecrease.setOnClickListener(v -> purchaseQuantityModel.decrease());
        viewPurchaseNumBinding.btnIncrease.setOnClickListener(v -> purchaseQuantityModel.increase());

        viewPurchaseNumBinding.etCount.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (purchaseQuantityModel.getPurchaseQuantityInt() == 0) {
                    purchaseQuantityModel.setPurchaseQuantity("1");
                }
            }
        });

        viewPurchaseNumBinding.etCount.addTextChangedListener(new DoNothingTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                // 保证光标在文字最后
                viewPurchaseNumBinding.etCount.setSelection(s.toString().length());
            }
        });

        addView(viewPurchaseNumBinding.getRoot());
    }

    public interface OnNumChangedListener {
        void onChange(boolean formBtn, int oldNum, int newNum);
    }

    public void setOnNumChangedListener(OnNumChangedListener onNumChangedListener) {
        purchaseQuantityModel.setOnNumChangedListener(onNumChangedListener);
    }

    public static class PurchaseQuantityModel extends BaseObservable {
        private String purchaseQuantity;
        private OnNumChangedListener onNumChangedListener;

        public PurchaseQuantityModel(String purchaseQuantity) {
            this.purchaseQuantity = purchaseQuantity;
        }

        public void setOnNumChangedListener(OnNumChangedListener onNumChangedListener) {
            this.onNumChangedListener = onNumChangedListener;
        }

        private boolean fromBtn = false;

        public void increase() {
            int temp = getPurchaseQuantityInt();
            int old = temp;
            temp++;
            if (temp > PurchaseNumView.max) {
                temp = PurchaseNumView.max;
            }

            if (old != temp) {
                fromBtn = true;
            }

            setPurchaseQuantity(Integer.toString(temp));
        }

        public void decrease() {
            int temp = getPurchaseQuantityInt();
            int old = temp;
            temp--;
            if (temp < PurchaseNumView.min) {
                temp = PurchaseNumView.min;
            }

            if (old != temp) {
                fromBtn = true;
            }

            setPurchaseQuantity(Integer.toString(temp));
        }

        public String getPurchaseQuantity() {
            return purchaseQuantity;
        }

        public Integer getPurchaseQuantityInt() {
            if (StringUtils.isTrimEmpty(purchaseQuantity)) {
                return 0;
            }
            return Integer.valueOf(purchaseQuantity);
        }

        public void setPurchaseQuantity(String purchaseQuantity) {
            int old = getPurchaseQuantityInt();

            if (!StringUtils.isTrimEmpty(purchaseQuantity)) {
                int temp = Integer.parseInt(purchaseQuantity);
                if (temp == 0) {
                    purchaseQuantity = String.valueOf(min);// 不允许手动输入0
                }
                while (purchaseQuantity.startsWith("0")) {
                    purchaseQuantity = purchaseQuantity.substring(1);
                }
            }

            this.purchaseQuantity = purchaseQuantity;
            if (getPurchaseQuantityInt() > PurchaseNumView.max) {
                this.purchaseQuantity = String.valueOf(PurchaseNumView.max);
            } else if (getPurchaseQuantityInt() < 1){
                // 不处理 这是用户删除文本框里的内容
//            this.purchaseQuantity = "1";
            }

            int newNum = getPurchaseQuantityInt();

            if (old != newNum && onNumChangedListener != null) {
                onNumChangedListener.onChange(fromBtn, old, newNum);
            }

            fromBtn = false;// 无论如何 消费掉fromBtn

            notifyChange();
        }
    }
}
