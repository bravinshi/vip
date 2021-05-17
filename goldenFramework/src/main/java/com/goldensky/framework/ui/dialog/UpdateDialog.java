package com.goldensky.framework.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.goldensky.framework.R;


public class UpdateDialog extends Dialog {

    private TextView title;// 标题
    private TextView version;// 标题
    private TextView desc;// 标题
    private TextView left;// 左边按钮
    private TextView right;// 右边按钮

    public UpdateDialog(Context context, boolean showLeft, String version, String desc, View.OnClickListener rightListener, View.OnClickListener leftListener) {
        super(context);
        initView(context, showLeft, version, desc, rightListener, leftListener);
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title.setText(title);
    }

    /**
     * 初始化
     *
     * @param context
     * @param rightListener
     */
    private void initView(Context context, boolean showLeft, String versionStr, String descStr, View.OnClickListener rightListener,View.OnClickListener leftListener) {
        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_update, null);
        title = contentView.findViewById(R.id.tv_title);
        version = contentView.findViewById(R.id.tv_version);
        desc = contentView.findViewById(R.id.tv_desc);
        left = contentView.findViewById(R.id.tv_left);
        right = contentView.findViewById(R.id.tv_right);

        left.setVisibility(showLeft ? View.VISIBLE : View.GONE);

        if (rightListener != null) {
            right.setOnClickListener(rightListener);
        }

        if (leftListener != null) {
            left.setOnClickListener(leftListener);
        } else {
            left.setOnClickListener(view -> dismiss());
        }

        version.setText(versionStr);
        desc.setText(descStr);


        setContentView(contentView);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

}
