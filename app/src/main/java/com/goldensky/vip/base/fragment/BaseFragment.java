package com.goldensky.vip.base.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.goldensky.framework.util.ToastUtils;
import com.goldensky.framework.util.Utils;
import com.goldensky.framework.viewmodel.BaseViewModel;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseFragment<T extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {

    protected T mBinding;
    protected VM mViewModel;
    private ViewModelProvider viewModelProvider;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelProvider = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(Utils.getApp()));
        mViewModel = initViewModel();
        initView(savedInstanceState);
    }

    abstract int getLayoutRes();

    abstract void initView(@Nullable Bundle savedInstanceState);

    protected VM initViewModel() {
        Type type = getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            Type argument = actualTypeArguments[1];
            mViewModel = viewModelProvider.get((Class<VM>) argument);
        } else {
            // BaseFragment必须被继承并实现子类
            throw new IllegalStateException("BaseFragment must be extended by subclass");
        }

        return mViewModel;
    }

    public void toast(String message) {
        ToastUtils.showShort(message);
    }

    public void toast(int messageId) {
        ToastUtils.showShort(messageId);
    }
}