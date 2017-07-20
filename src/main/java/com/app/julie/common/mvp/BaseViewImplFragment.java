package com.app.julie.common.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.app.julie.common.base.BaseFragment;
import com.app.julie.common.util.ToastUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;


public abstract class BaseViewImplFragment<P extends BasePresenter> extends BaseFragment
        implements BaseView<P> {

    protected P mPresenter;
    protected Activity mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.subscribe();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.unSubscribe();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShortToast(msg);
    }

    @Override
    public Context getContext() {
        return mContext;
    }

    @Override
    public void showLoading() {
        showLoadingDialog();
    }

    @Override
    public void closeLoading() {
        closeLoadingDialog();
    }

    @Override
    public <T> LifecycleTransformer<T> bindToRxLifecycle() {
        return this.<T>bindToLifecycle();
    }
}
