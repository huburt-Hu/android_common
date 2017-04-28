package com.app.julie.common.mvp;

import android.support.v4.app.Fragment;

import com.app.julie.common.util.ToastUtil;

/**
 * Created by julie on 2017/2/28.
 */

public abstract class BaseViewImpl<P extends BasePresenterImpl> extends Fragment implements BaseView<P> {

    protected P mPresenter;

    @Override
    public void setPresenter(P presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showToast(getActivity(), msg);
    }
}
