package com.app.julie.common.mvp;


import com.app.julie.common.util.LogUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by hubert
 * <p>
 * Created on 2017/6/14.
 */

public class DoOnXTransformer {

    public static <T> ObservableTransformer<T, T> doOnSubscribe(final Runnable runnable) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        LogUtils.i("doOnSubscribe:" + Thread.currentThread().getName());
                        runnable.run();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    public static <T> ObservableTransformer<T, T> doOnFinish(final Runnable runnable) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        LogUtils.i("doOnCompleted:" + Thread.currentThread().getName());
                        runnable.run();
                    }
                }).subscribeOn(AndroidSchedulers.mainThread())
                        .doOnError(new Consumer<Throwable>() {
                            @Override
                            public void accept(@NonNull Throwable throwable) throws Exception {
                                LogUtils.i("doOnCompleted:" + Thread.currentThread().getName());
                                runnable.run();
                            }
                        });
            }
        };
    }
}
