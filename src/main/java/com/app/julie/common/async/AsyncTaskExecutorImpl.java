package com.app.julie.common.async;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by julie on 2017/3/1.
 */

public class AsyncTaskExecutorImpl implements AsyncTaskExecutor {

    private ExecutorService mThreadPool = Executors.newCachedThreadPool();

    private Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void runOnBackground(Runnable runnable) {
        if (runnable != null) {
            mThreadPool.execute(runnable);
        }
    }

    @Override
    public void runOnUiThread(Runnable runnable) {
        if (runnable != null) {
            mHandler.post(runnable);
        }
    }

    @Override
    public void runOnUiThreadDelay(Runnable runnable, long delayMillis) {
        if (runnable != null) {
            mHandler.postDelayed(runnable, delayMillis);
        }
    }
}
