package com.goldensky.vip.base.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.goldensky.framework.util.ToastUtils;
import com.goldensky.framework.viewmodel.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/10 16:16
 * 包名： com.goldensky.together.base.activity
 * 类说明：
 */
public abstract class BaseActivity<T extends ViewDataBinding, VM extends BaseViewModel>
        extends AppCompatActivity implements IBaseActivity {

    public T mBinding;
    public VM mViewModel;
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

    public void toast(String message) {
        ToastUtils.showShort(message);
    }

    public void toast(int resId) {
        ToastUtils.showShort(resId);
    }

    private void initViewModel() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            Type argument = actualTypeArguments[1];
            mViewModel = viewModelProvider.get((Class<VM>) argument);
        } else {
            // BaseActivity必须被继承并实现子类
            throw new IllegalStateException("BaseActivity must be extended by subclass");
        }
    }
}
