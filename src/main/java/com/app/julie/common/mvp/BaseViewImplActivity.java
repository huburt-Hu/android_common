package com.app.julie.common.mvp;

import android.content.Context;

import com.app.julie.common.base.BaseActivity;
import com.app.julie.common.util.ToastUtils;
import com.trello.rxlifecycle2.LifecycleTransformer;


/**
 * Created by hubert
 * <p>
 * Created on 2017/6/27.
 */

public class BaseViewImplActivity<P extends BasePresenter> extends BaseActivity implements BaseView<P> {
    protected P mPresenter;

    @Override
    public void setPresenter(P presenter) {
        this.mPresenter = presenter;
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
        return isDestroyed();
    }

    @Override
    public Context getContext() {
        return this;
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
        return bindToLifecycle();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showShortToast(msg);
    }
}
