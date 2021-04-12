package com.goldensky.vip.base.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.goldensky.framework.util.ToastUtils;


/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/10 16:16
 * 包名： com.goldensky.together.base.activity
 * 类说明：
 */
public abstract class BaseActivity<T extends ViewDataBinding>
        extends AppCompatActivity implements IBaseActivity {

    public T mBinding;
    private ViewModelProvider viewModelProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        viewModelProvider = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mBinding = DataBindingUtil.setContentView(this, getLayoutId());
        initViewModel();
        observe();
        onFinishInit(savedInstanceState);
    }

    public abstract void onFinishInit(Bundle savedInstanceState);

    public abstract void observe();

    public void beforeSetContentView() {
    }

    public abstract void initViewModel();

    public void toast(String message) {
        ToastUtils.showShort(message);
    }

    public void toast(int resId) {
        ToastUtils.showShort(resId);
    }

    public ViewModelProvider getViewModelProvider() {
        return viewModelProvider;
    }
}
