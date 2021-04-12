package com.goldensky.vip.base.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;

import com.goldensky.framework.viewmodel.BaseViewModel;

/**
 * 懒加载fragment
 *
 * @ClassName:LazyloadFragment
 * @PackageName:com.xianshangjingbiao.app.base
 * @Create On 2018/4/25 0025   17:46
 * @Site:http://www.handongkeji.com
 * @author:zhouhao
 * @Copyrights 2018/4/25 0025 handongkeji All rights reserved.
 */
public abstract class LazyLoadFragment<T extends ViewDataBinding, VM extends BaseViewModel>
        extends BaseFragment<T, VM> {

    public static final int FLAG_USERVISIBLE = 1;
    public static final int FLAG_VIEWCREATED = 1 << 1;
    public static final int FLAG_FIRSTIN = 1 << 2;  //  第一次初始化标志

    private int flag;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            flag |= FLAG_USERVISIBLE;
        } else {
            flag &= ~FLAG_USERVISIBLE;
        }
        lazyLoad();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }

    private void lazyLoad() {
        if ((flag & FLAG_USERVISIBLE) == FLAG_USERVISIBLE
                && (flag & FLAG_VIEWCREATED) == FLAG_VIEWCREATED
                && (flag & FLAG_FIRSTIN) != FLAG_FIRSTIN) {
            flag |= FLAG_FIRSTIN;
            onLazyLoad();
        }
    }

    public abstract void onLazyLoad();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flag |= FLAG_VIEWCREATED;
        lazyLoad();
    }

}
