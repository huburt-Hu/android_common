package com.app.julie.common.mvp;


import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by hubert
 * <p>
 * Created on 2017/6/14.
 */

public abstract class RespObserver<T> implements Observer<T>, RespHandler.CustomHandler<T> {

    private RespHandler<T> respHandler;

    public RespObserver() {
        respHandler = new RespHandler<T>(this);
    }

    public RespObserver(BaseView baseView) {
        respHandler = new RespHandler<T>(this, baseView);
    }

    @Override
    public void onError(Throwable e) {
        respHandler.onError(e);
        respHandler = null;
    }

    @Override
    public void onComplete() {
        respHandler.onCompleted();
        respHandler = null;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(T t) {
        respHandler.onNext(t);
    }


    @Override
    public boolean operationError(T t, int code, String message) {
        return false;
    }

    @Override
    public boolean error(Throwable e) {
        return false;
    }


}
