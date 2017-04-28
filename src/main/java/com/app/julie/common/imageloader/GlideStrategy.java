package com.app.julie.common.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by julie on 2017/3/1.
 */

public class GlideStrategy implements Loader {

    @Override
    public void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    @Override
    public void loadImage(Context context, String url, int placeHolder, ImageView imageView) {
        Glide.with(context).load(url).placeholder(placeHolder).into(imageView);
    }

    @Override
    public void loadImage(Context context, String url, int placeHolder, int error, ImageView imageView) {
        Glide.with(context).load(url).placeholder(placeHolder).error(error).into(imageView);
    }

    @Override
    public void loadImage(Context context, int resId, ImageView imageView) {
        Glide.with(context).load(resId).into(imageView);
    }

    @Override
    public void loadImage(Context context, int resId, int placeHolder, ImageView imageView) {
        Glide.with(context).load(resId).placeholder(placeHolder).into(imageView);
    }

    @Override
    public void loadImage(Context context, int resId, int placeHolder, int error, ImageView imageView) {
        Glide.with(context).load(resId).placeholder(placeHolder).error(error).into(imageView);
    }
}
