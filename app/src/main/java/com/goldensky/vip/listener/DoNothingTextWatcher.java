package com.goldensky.vip.listener;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/26 15:15
 * 包名： com.jintian.jintianhezong.listener
 * 类说明：
 */
public abstract class DoNothingTextWatcher implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
