package com.app.julie.common.mvp;


import com.app.julie.common.async.AsyncTaskExecutor;

import java.lang.ref.WeakReference;

import static com.app.julie.common.async.AsyncTaskManager.getExecutor;


/**
 * Created by julie on 2017/2/28.
 */

public abstract class BasePresenterImpl<V extends BaseView>
        implements BasePresenter, AsyncTaskExecutor {

    private WeakReference<V> mView;

    public BasePresenterImpl(V view) {
        this.mView = new WeakReference<V>(view);
        view.setPresenter(this);
    }

    /**
     * @return true if the view is visible to user, else not.
     */
    protected boolean isViewActive() {
        return mView != null && mView.get().isActive();
    }

    /**
     * get the view which associated with this presenter,
     * calling {@link BasePresenterImpl#isViewActive()} before to make sure view is active,
     * or the return could be null.
     *
     * @return
     */
    protected V getView() {
        return mView != null ? mView.get() : null;
    }

    public void detachView() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    @Override
    public void runOnBackground(Runnable runnable) {
        getExecutor().runOnBackground(runnable);
    }

    @Override
    public void runOnUiThread(Runnable runnable) {
        getExecutor().runOnUiThread(runnable);
    }

    @Override
    public void runOnUiThreadDelay(Runnable runnable, long delayMillis) {
        getExecutor().runOnUiThreadDelay(runnable, delayMillis);
    }
}
