package com.goldensky.framework.ui.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.goldensky.framework.R;

public class BaseDialog extends DialogFragment {

    private boolean showFromBottom;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = ViewGroup.LayoutParams.MATCH_PARENT;
        attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        window.setAttributes(attributes);
        window.setBackgroundDrawable(new ColorDrawable(0x00ffffff));

        if (showFromBottom) {
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.hand_popupwindow_anim_style);
        }
    }

    public void setShowFromBottom(boolean showFromBottom) {
        this.showFromBottom = showFromBottom;
    }
}
