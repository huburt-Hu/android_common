package com.app.julie.common.async;

/**
 * Created by julie on 2017/3/1.
 */

public interface AsyncTaskExecutor {
    void runOnBackground(Runnable runnable);

    void runOnUiThread(Runnable runnable);

    void runOnUiThreadDelay(Runnable runnable, long delayMillis);
}
