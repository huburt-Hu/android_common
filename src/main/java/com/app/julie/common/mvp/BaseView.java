package com.app.julie.common.mvp;

import android.content.Context;

import com.trello.rxlifecycle2.LifecycleTransformer;


public interface BaseView<T extends BasePresenter> {

    void setPresenter(T presenter);

    boolean isActive();

    void showToast(String msg);

    Context getContext();

    void showLoading();

    void closeLoading();

    <X> LifecycleTransformer<X> bindToRxLifecycle();
}
