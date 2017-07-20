package com.app.julie.common.mvp;


import android.content.Context;

import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


public abstract class BasePresenterImpl<V extends BaseView<? extends BasePresenter>>
        implements BasePresenter {

    protected WeakReference<V> mView;

    public BasePresenterImpl(V view) {
        this.mView = new WeakReference<V>(view);
    }

    /**
     * @return true if the view is visible to user, else not.
     */
    protected boolean isViewActive() {
        return mView != null && mView.get() != null && mView.get().isActive();
    }

    /**
     * get the view which associated with this presenter,
     * calling {@link BasePresenterImpl#isViewActive()} before to make sure view is active,
     * or the return could be null.
     *
     * @return view or NULL.
     */
    protected V getView() {
        return mView != null ? mView.get() : null;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    /**
     * 获取context，实际为activity
     *
     * @return context
     */
    public Context getContext() {
        return getView().getContext();
    }

    /**
     * 请求网络数据进行一层简单封装，在Io线程请求，在主线程使用。
     * <p>使用{@link BasePresenterImpl#getRequestTransformer}代替</p>
     *
     * @param observable 需要请求的接口Observable
     * @param <T>        请求参数
     * @return 返回该Observable
     */
    @Deprecated
    public <T> Observable<T> requestData(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    /**
     * 获取网络请求的通用transformer，包括断网重试，切换线程，绑定生命周期
     * <p>使用方式observable.compose(getRequestTransformer()).subscribe(...)</p>
     *
     * @param <T>
     * @return transformer
     */
    protected <T> ObservableTransformer<T, T> getRequestTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .retryWhen(new RetryWhenNetException())
                        .compose(SchedulersTransformer.<T>io_ui())
                        .compose(getView().<T>bindToRxLifecycle());
            }
        };
    }

    /**
     * 为网络请求绑定默认的加载前显示loading，加载完成后消失
     *
     * @param <T>
     * @return transformer
     */
    protected <T> ObservableTransformer<T, T> getLoadingTransformer() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream
                        .compose(DoOnXTransformer.<T>doOnSubscribe(new Runnable() {
                            @Override
                            public void run() {
                                getView().showLoading();
                            }
                        }))
                        .compose(DoOnXTransformer.<T>doOnFinish(new Runnable() {
                            @Override
                            public void run() {
                                if (isViewActive()) {
                                    getView().closeLoading();
                                }
                            }
                        }));
            }
        };
    }
}
