package com.app.julie.common.async;

/**
 * Created by julie on 2017/3/1.
 */

public class AsyncTaskManager {
    private AsyncTaskExecutor mExecutor;

    public AsyncTaskManager() {
        this.mExecutor = new AsyncTaskExecutorImpl();
    }

    public static AsyncTaskExecutor getExecutor() {
        return SingletonHolder.sInstance.mExecutor;
    }

    private static class SingletonHolder {
        private static AsyncTaskManager sInstance = new AsyncTaskManager();
    }


}
