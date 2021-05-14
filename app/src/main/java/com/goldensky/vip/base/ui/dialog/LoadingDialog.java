package com.goldensky.vip.base.ui.dialog;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.goldensky.framework.ui.dialog.BaseDialog;
import com.goldensky.vip.R;
import com.goldensky.vip.databinding.DialogLoadingBinding;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/5/14 9:49
 * 包名： com.goldensky.vip.base.ui.dialog
 * 类说明：
 */
public class LoadingDialog extends BaseDialog {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogLoadingBinding dialogLoadingBinding = DataBindingUtil
                .inflate(inflater, R.layout.dialog_loading, container, false);
        dialogLoadingBinding.lvLoading.show();
        setCancelable(false);
        return dialogLoadingBinding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0f;
        attributes.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);
        window.setBackgroundDrawableResource(R.color.colorTransparent);
    }
}
