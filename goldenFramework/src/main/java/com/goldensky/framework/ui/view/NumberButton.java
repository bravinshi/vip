package com.goldensky.framework.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.goldensky.framework.R;
import com.goldensky.framework.util.ToastUtils;

public class NumberButton extends LinearLayout {
    private TextView minusNumberButton;
    private EditText countNumberButton;
    private TextView plusNumberButton;
    private OnCountChangeListener listener;
    public int count = 1;
    private int minCount = 1;
    private int maxCount = Integer.MAX_VALUE;
    private boolean isUser = false;
    private int inputCount=1;
    private TextView.OnEditorActionListener focusListener;
    public NumberButton(Context context) {
        super(context);
    }

    public void setFocusListener(TextView.OnEditorActionListener focusListener) {
        this.focusListener = focusListener;
    }

    public NumberButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public NumberButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.number_button_layout, this);
        minusNumberButton = (TextView) findViewById(R.id.minus_number_button);
        countNumberButton = (EditText) findViewById(R.id.count_number_button);
        plusNumberButton = (TextView) findViewById(R.id.plus_number_button);
        if(focusListener!=null){
            countNumberButton.setOnEditorActionListener(focusListener);
        }
        minusNumberButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countNumberButton.clearFocus();
                if (count - 1 < minCount) {
                    ToastUtils.showShort("????????????????????????:" + minCount);
                    count = minCount;
                } else {
                    count--;
                }
                isUser = true;
                changeCount(count);
                if (listener != null) {
                    listener.onChange(NumberButton.this);
                }
            }

        });
        plusNumberButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                countNumberButton.clearFocus();
                if (count + 1 > maxCount) {
                    ToastUtils.showShort("?????????????????????:" + maxCount);
                    count = maxCount;
                } else {
                    if(inputCount!=0){
                        count++;
                    }

                }
                isUser = true;
                changeCount(count);
                if (listener != null) {
                    listener.onChange(NumberButton.this);
                }
            }
        });
        countNumberButton.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() > 0) {
                    inputCount=Integer.parseInt(s.toString().trim());
                    String countString = s.toString().trim();
                    if (countString.startsWith("0") && countString.length() >= 2) {
                        countString = countString.substring(1);
                        countNumberButton.setText(countString);
                        countNumberButton.setSelection(countString.length());//??????????????????
                        return;
                    }
                    if(countString.startsWith("0")){
                        ToastUtils.showShort("????????????????????????:" + minCount);
                        countNumberButton.setText("1");
                        countNumberButton.setSelection(countString.length());//??????????????????
                        return;
                    }
                    count = Integer.parseInt(countString);
                    if (count > maxCount) {
                        ToastUtils.showShort("?????????????????????:" + maxCount);
                        count = maxCount;
                        changeCount(count);
                        listener.onChange(NumberButton.this);
                    }

                }


            }
        });
        countNumberButton.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                EditText editText = (EditText) v;
                String s = editText.getEditableText().toString().trim();
                if (!hasFocus) {
                    if (editText.getEditableText().length() == 0) {
                        ToastUtils.showShort("????????????????????????:" + minCount);
                        countNumberButton.setText("1");
                        countNumberButton.setSelection(1);//??????????????????
                    }else {
                        if(s.startsWith("0")){
                            ToastUtils.showShort("????????????????????????:" + minCount);
                            count = minCount;
                            countNumberButton.setText(count+"");
                            countNumberButton.setSelection(1);//??????????????????
                        }
                    }

                    if(isUser){
                        if (listener != null) {
                            listener.onChange(NumberButton.this);
                        }
                    }
                }
            }
        });
    }

    public void changeCount(Integer number) {
        countNumberButton.setText(number + "");
        countNumberButton.setSelection(countNumberButton.getEditableText().length());//??????????????????
    }

    public void setCount(int count) {
        isUser = false;
        this.count = count;
        changeCount(this.count);
        isUser=true;
    }

    public int getCount() {
        return count;
    }

    public void setMinCount(int minCount) {
        this.minCount = minCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public void setCountChageListener(OnCountChangeListener listener) {
        this.listener = listener;
    }

    public Integer getEditCount() {
        return Integer.parseInt(countNumberButton.getEditableText().toString().trim());
    }

    public interface OnCountChangeListener {
        void onChange(NumberButton button);
    }
}
