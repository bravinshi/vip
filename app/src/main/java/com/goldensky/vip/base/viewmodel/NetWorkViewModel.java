package com.goldensky.vip.base.viewmodel;

import android.view.View;

import androidx.annotation.NonNull;

import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.constant.NetCodeConstant;
import com.goldensky.framework.constant.NetCodeExceptionConstant;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.framework.util.ToastUtils;
import com.goldensky.framework.viewmodel.BaseViewModel;
import com.goldensky.vip.BuildConfig;
import com.goldensky.vip.CustomNetErrorHandler;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/3/10 11:10
 * 包名： com.goldensky.basekit.viewmodel
 * 类说明：
 */
public class NetWorkViewModel extends BaseViewModel {

    public abstract class BaseNetObserver<T> implements Observer<NetResponse<T>> {

        private WeakReference<View> viewWeakReference = new WeakReference<>(null);

        public BaseNetObserver<T> watchViewClickable(View view) {
            if (view != null) {
                viewWeakReference = new WeakReference<>(view);
            }
            return this;
        }

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            compositeDisposable.add(d);

            View temp = viewWeakReference.get();
            if (temp != null) {
                temp.setClickable(false);
            }
        }

        @Override
        public void onNext(@NonNull NetResponse<T> tNetResponse) {
            View temp = viewWeakReference.get();
            if (temp != null) {
                temp.setClickable(true);
            }

            if (tNetResponse.isLogicSuccess()) {
                onSuccess(tNetResponse.getData());
            } else {
                // 在特殊处理之前先走NetErrorHandler处理逻辑
                if (!CustomNetErrorHandler.getInstance().onFail(tNetResponse)) {
                    onFail(tNetResponse);
                }
            }
        }

        @Override
        public void onError(@NonNull Throwable e) {
            View temp = viewWeakReference.get();
            if (temp != null) {
                temp.setClickable(true);
            }

            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
            NetResponse<T> netResponse = new NetResponse<>();
            netResponse.setCode(NetCodeConstant.CODE_EXCEPTION_DEFAULT);
            netResponse.setData(null);
            netResponse.setMessage(StringUtils.isTrimEmpty(e.getMessage())
                    ? e.getLocalizedMessage() : e.getMessage());
            if (StringUtils.isTrimEmpty(netResponse.getMessage())) {
                netResponse.setMessage("unknown error");
            }

            if (e instanceof CompositeException) {
                CompositeException compositeE = (CompositeException) e;
                for (Throwable throwable : compositeE.getExceptions()) {
                    if (throwable instanceof SocketTimeoutException) {
                        netResponse.setMessage(NetCodeExceptionConstant.EXCEPTION_SOCKET_TIMEOUT);
                    } else if (throwable instanceof ConnectException) {
                        netResponse.setMessage(NetCodeExceptionConstant.EXCEPTION_CONNECT);
                    } else if (throwable instanceof UnknownHostException) {
                        netResponse.setMessage(NetCodeExceptionConstant.EXCEPTION_UNKNOWN_HOST);
                    } else if (throwable instanceof MalformedJsonException) {
                        netResponse.setMessage(NetCodeExceptionConstant.EXCEPTION_MALFORMED_JSON);
                    } else if (throwable instanceof JsonSyntaxException) {
                        netResponse.setMessage(NetCodeExceptionConstant.EXCEPTION_JSON_SYNTAX);
                    }
                }
            } else if (e instanceof SocketTimeoutException) {
                netResponse.setMessage(NetCodeExceptionConstant.EXCEPTION_SOCKET_TIMEOUT);
            } else if (e instanceof ConnectException) {
                netResponse.setMessage(NetCodeExceptionConstant.EXCEPTION_CONNECT);
            } else if (e instanceof UnknownHostException) {
                netResponse.setMessage(NetCodeExceptionConstant.EXCEPTION_UNKNOWN_HOST);
            } else if (e instanceof MalformedJsonException) {
                netResponse.setMessage(NetCodeExceptionConstant.EXCEPTION_MALFORMED_JSON);
            } else if (e instanceof JsonSyntaxException) {
                netResponse.setMessage(NetCodeExceptionConstant.EXCEPTION_JSON_SYNTAX);
            }

            // 在特殊处理之前先走NetErrorHandler处理逻辑
            if (!CustomNetErrorHandler.getInstance().onFail(netResponse)) {
                onFail(netResponse);
            }
        }

        @Override
        public void onComplete() {

        }

        public abstract void onSuccess(T data);

        /**
         * 网络访问错误回调
         *
         * @param data 错误的参数
         *
         * @return
         */
        public abstract boolean onFail(NetResponse<T> data);
    }

    /**
     * BaseNetObserver的一般性实现，当出现错误的时候弹出toast
     *
     * @param <T> 预期返回的数据类型
     */
    public abstract class ToastNetObserver<T> extends BaseNetObserver<T> {
        @Override
        public boolean onFail(NetResponse<T> data) {
            ToastUtils.showShort(data.getMessage());

            return false;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
