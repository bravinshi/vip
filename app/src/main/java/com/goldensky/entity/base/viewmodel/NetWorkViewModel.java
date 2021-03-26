package com.goldensky.entity.base.viewmodel;

import android.util.Log;

import androidx.annotation.NonNull;

import com.goldensky.entity.BuildConfig;
import com.goldensky.entity.CustomNetErrorHandler;
import com.goldensky.entity.constant.NetCodeConstant;
import com.goldensky.entity.constant.NetCodeExceptionConstant;
import com.goldensky.entity.util.NetResponseUtil;
import com.goldensky.framework.bean.NetResponse;
import com.goldensky.framework.util.StringUtils;
import com.goldensky.framework.util.ToastUtils;
import com.goldensky.framework.viewmodel.BaseViewModel;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;

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

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            compositeDisposable.add(d);
        }

        @Override
        public void onNext(@NonNull NetResponse<T> tNetResponse) {
            if (NetResponseUtil.isSuccess(tNetResponse)) {
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
            if (BuildConfig.DEBUG || data.getCode() != NetCodeConstant.CODE_EXCEPTION_DEFAULT) {
                ToastUtils.showShort(data.getMessage());
            }

            return false;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
