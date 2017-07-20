package com.app.julie.common.base;

import com.app.julie.common.mvp.BasePresenter;
import com.app.julie.common.mvp.BaseView;

import java.util.List;

/**
 * Created by hubert
 * <p>
 * Created on 2017/7/6.
 */

public interface BaseRvContract {
    interface View<B, P extends Presenter> extends BaseView<P> {
        /**
         * 获取数据后调用此方法刷新界面
         *
         * @param beans 数据
         */
        void onDataReceived(List<B> beans);

        void onFailed();
    }

    interface Presenter extends BasePresenter {
        void getData(int page);

    }

}
