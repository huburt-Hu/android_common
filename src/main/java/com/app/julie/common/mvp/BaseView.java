package com.app.julie.common.mvp;

/**
 * Created by julie on 2017/2/28.
 */

public interface BaseView<P extends BasePresenter> {
    void setPresenter(P presenter);

    boolean isActive();

    void showToast(String msg);

}
