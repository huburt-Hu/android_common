package com.app.julie.common;

import android.app.Application;

import com.app.julie.common.util.CrashUtils;

/**
 * Created by julie on 2017/5/2.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashUtils.getInstance().init();
    }
}
