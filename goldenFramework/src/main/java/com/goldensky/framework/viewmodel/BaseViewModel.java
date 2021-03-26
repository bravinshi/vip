package com.goldensky.framework.viewmodel;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/9 18:00
 * 包名： com.goldensky.basekit.viewmodel
 * 类说明：基础的VM 覆写{@link #onCleared}的实现，实现在 {@link LifecycleOwner} 被Destroy的时候
 * 能够中断网络请求
 */
public class BaseViewModel extends ViewModel {

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

}
