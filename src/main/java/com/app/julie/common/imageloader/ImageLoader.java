package com.app.julie.common.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by julie on 2017/3/1.
 */

public class ImageLoader implements Loader {

    private static Loader sLoader;

    private ImageLoader() {
    }

    public static ImageLoader getInstance() {
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final ImageLoader sInstance = new ImageLoader();
    }

    public static void init(Loader loader) {
        sLoader = loader;
    }

    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        sLoader.loadImage(context, url, imageView);
    }

    @Override
    public void loadImage(Context context, String url, int placeHolder, ImageView imageView) {
        sLoader.loadImage(context, url, placeHolder, imageView);
    }

    @Override
    public void loadImage(Context context, String url, int placeHolder, int error, ImageView imageView) {
        sLoader.loadImage(context, url, placeHolder, error, imageView);
    }

    @Override
    public void loadImage(Context context, int resId, ImageView imageView) {
        sLoader.loadImage(context, resId, imageView);
    }

    @Override
    public void loadImage(Context context, int resId, int placeHolder, ImageView imageView) {
        sLoader.loadImage(context, resId, placeHolder, imageView);
    }

    @Override
    public void loadImage(Context context, int resId, int placeHolder, int error, ImageView imageView) {
        sLoader.loadImage(context, resId, placeHolder, error, imageView);
    }
}