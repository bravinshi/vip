package com.goldensky.framework.net;

import androidx.annotation.Nullable;

import com.goldensky.framework.R;

import java.lang.reflect.Type;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;

/**
 * @author bravin
 * @version 1.0
 * 创建日期：2021/4/29 15:43
 * 包名： com.goldensky.framework.net
 * 类说明：
 */
public class MainThreadObservableCallAdapter implements CallAdapter<R, Object> {

    private final Type responseType;
    private final Scheduler scheduler;
    private final boolean isAsync;
    private final boolean isResult;
    private final boolean isBody;
    private final boolean isFlowable;
    private final boolean isSingle;
    private final boolean isMaybe;
    private final boolean isCompletable;

    MainThreadObservableCallAdapter(Type responseType, @Nullable Scheduler scheduler, boolean isAsync,
                                    boolean isResult, boolean isBody, boolean isFlowable, boolean isSingle, boolean isMaybe,
                                    boolean isCompletable) {
        this.responseType = responseType;
        this.scheduler = scheduler;
        this.isAsync = isAsync;
        this.isResult = isResult;
        this.isBody = isBody;
        this.isFlowable = isFlowable;
        this.isSingle = isSingle;
        this.isMaybe = isMaybe;
        this.isCompletable = isCompletable;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public Object adapt(Call<R> call) {
        Observable<Response<R>> responseObservable = isAsync
                ? new CallEnqueueObservable<>(call)
                : new CallExecuteObservable<>(call);

        Observable<?> observable;
        if (isResult) {
            observable = new ResultObservable<>(responseObservable);
        } else if (isBody) {
            observable = new BodyObservable<>(responseObservable);
        } else {
            observable = responseObservable;
        }

        if (scheduler != null) {
            observable = observable.subscribeOn(scheduler);
        }

        if (isFlowable) {
            observable.subscribeOn(AndroidSchedulers.mainThread());
            return observable.toFlowable(BackpressureStrategy.LATEST);
        }
        if (isSingle) {
            return observable.singleOrError().observeOn(AndroidSchedulers.mainThread());
        }
        if (isMaybe) {
            return observable.singleElement().observeOn(AndroidSchedulers.mainThread());
        }
        if (isCompletable) {
            return observable.ignoreElements().observeOn(AndroidSchedulers.mainThread());
        }
        return observable.observeOn(AndroidSchedulers.mainThread());
    }
}
