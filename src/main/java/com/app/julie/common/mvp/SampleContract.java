package com.app.julie.common.mvp;

/**
 * Created by julie on 2017/2/28.
 */

public interface SampleContract {
    abstract class View extends BaseViewImpl<Presenter> {
        //methods
        public abstract void showToast(String msg);

    }

    abstract class Presenter extends BasePresenterImpl<View> {

        public Presenter(View view) {
            super(view);
        }
    }

}
