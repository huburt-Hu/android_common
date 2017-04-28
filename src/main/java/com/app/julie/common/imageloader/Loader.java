package com.app.julie.common.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by julie on 2017/2/17.
 */

public interface Loader {

    void loadImage(Context context, String url, ImageView imageView);

    void loadImage(Context context, String url, int placeHolder, ImageView imageView);

    void loadImage(Context context, String url, int placeHolder, int error, ImageView imageView);

    void loadImage(Context context, int resId, ImageView imageView);

    void loadImage(Context context, int resId, int placeHolder, ImageView imageView);

    void loadImage(Context context, int resId, int placeHolder, int error, ImageView imageView);

}
