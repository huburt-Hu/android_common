package com.app.julie.common;

import android.app.Application;

import com.app.julie.common.util.CrashUtils;
import com.app.julie.common.util.Utils;

/**
 * Created by julie on 2017/5/2.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        CrashUtils.getInstance().init();

    }
}
