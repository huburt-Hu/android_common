package com.app.julie.common.download;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;

import com.app.julie.common.BuildConfig;
import com.app.julie.common.util.ConvertUtils;
import com.app.julie.common.util.LogUtils;

import java.io.File;
import java.io.InputStream;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class UpdateApkService extends IntentService {

    private static final String ACTION_DOWNLOAD = "com.app.julie.common.download.action.DOWNLOAD";

    private static final String EXTRA_BASE_URL = "com.app.julie.common.download.extra.baseUrl";
    private static final String EXTRA_URL = "com.app.julie.common.download.extra.url";
    private static final String EXTRA_FILE = "com.app.julie.common.download.extra.file";
    private static final String EXTRA_ICON = "com.app.julie.common.download.extra.icon";

    public static final int NOTIFICATION_ID = 0x123;

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;

    public UpdateApkService() {
        super("UpdateApkService");
    }

    public static void startDownload(Context context, int icon, String baseUrl, String url, File file) {
        Intent intent = new Intent(context, UpdateApkService.class);
        intent.setAction(ACTION_DOWNLOAD);
        intent.putExtra(EXTRA_BASE_URL, baseUrl);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_FILE, file);
        intent.putExtra(EXTRA_ICON, icon);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_DOWNLOAD.equals(action)) {
                final String baseUrl = intent.getStringExtra(EXTRA_BASE_URL);
                final String url = intent.getStringExtra(EXTRA_URL);
                File file = (File) intent.getSerializableExtra(EXTRA_FILE);
                int icon = intent.getIntExtra(EXTRA_ICON, 0);
                handleActionDown(icon, baseUrl, url, file);
            }
        }
    }

    private void handleActionDown(final int icon, String baseUrl, String url, final File file) {
        LogUtils.i("handleActionDown");
        if (file.exists()) {
            installApk(file);
            return;
        }

        new DownloadManager(baseUrl, new DownloadProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength, boolean done) {
                sendNotification(bytesRead, contentLength);
            }
        }).download(url, file, new Observer<InputStream>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationBuilder = new NotificationCompat.Builder(UpdateApkService.this)
                        .setSmallIcon(icon)
                        .setContentTitle("Download")
                        .setContentText("Downloading File")
                        .setAutoCancel(true);
                notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
            }

            @Override
            public void onNext(@NonNull InputStream inputStream) {

            }

            @Override
            public void onError(@NonNull Throwable e) {
                notificationBuilder.setProgress(0, 0, false);
                notificationBuilder.setContentText("下载失败");
                notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
            }

            @Override
            public void onComplete() {
                notificationManager.cancel(NOTIFICATION_ID);
                installApk(file);
            }
        });
    }

    private void installApk(File file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileprovider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }

    //回调频率太高，控制通知栏更新的次数
    private int lastProgress;

    private void sendNotification(long bytesRead, long contentLength) {
        int progress = (int) (bytesRead * 1d / contentLength * 100);
        if (progress > lastProgress) {
            notificationBuilder.setProgress(100, progress, false);
            notificationBuilder.setContentText(
                    ConvertUtils.byte2FitMemorySize(bytesRead) + "/" +
                            ConvertUtils.byte2FitMemorySize(contentLength));
            notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build());
            lastProgress = progress;
        }
    }

}
