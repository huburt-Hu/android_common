package com.app.julie.common.download;

public interface DownloadProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}