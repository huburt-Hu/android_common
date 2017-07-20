package com.app.julie.common.mvp;


import com.app.julie.common.base.BaseBean;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 网络结果处理类, 此类会判断网络错误与业务错误.
 */
public class RespHandler<T> {

    private BaseView view;
    private CustomHandler<T> handler;

    public RespHandler(CustomHandler<T> handler) {
        this.handler = handler;
    }

    public RespHandler(CustomHandler<T> handler, BaseView view) {
        this.handler = handler;
        this.view = view;
    }

    public void onCompleted() {
        release();
    }

    public void onError(Throwable e) {
        e.printStackTrace();
        if (!handler.error(e)) {
            handleException(e);
        }
        release();
    }

    public void onNext(T t) {
        if (t instanceof BaseBean) {
            BaseBean data = (BaseBean) t;
            if (BaseBean.CODE_OK == data.getCode()) {
                handler.success(t);
            } else {
                if (!handler.operationError(t, data.getCode(), data.getMessage())) {
                    handleOperationError(data.getMessage());
                }
            }
        } else {
            handler.success(t);
        }
    }

    public void release() {
        view = null;
        handler = null;
    }

    public void handleException(Throwable e) {
        if (view != null) {
            if (e instanceof ConnectException) {
                view.showToast("网络异常, 请检查您的网络后重试");
            } else if (e instanceof HttpException) {
                view.showToast("服务器异常");
            } else if (e instanceof SocketTimeoutException) {
                view.showToast("连接超时");
            } else if (e instanceof UnknownHostException) {
                view.showToast("网络异常, 请检查您的网络后重试");
            } else {
                view.showToast("数据异常");
            }
        }
    }

    public void handleOperationError(String message) {
        if (view != null)
            view.showToast(message);
    }

    public interface CustomHandler<T> {
        /**
         * 请求成功同时业务成功的情况下会调用此函数
         */
        void success(T t);

        /**
         * 请求成功但业务失败的情况下会调用此函数.
         *
         * @return 是否需要自行处理业务错误.
         * <p>
         * true - 需要, 父类不会处理错误
         * </P>
         * false - 不需要, 交由父类处理
         */
        boolean operationError(T t, int code, String message);

        /**
         * 请求失败的情况下会调用此函数
         *
         * @return 是否需要自行处理系统错误.
         * <p>
         * true - 需要, 父类不会处理错误
         * </P>
         * false - 不需要, 交由父类处理
         */
        boolean error(Throwable e);
    }
}
