package com.app.julie.common.imageloader;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

public class GlideImageLoaderStrategy implements ImageLoader {

    @Override
    public void load(View v, String url) {
        load(v, url, null);
    }

    @Override
    public void load(View v, String url, ImageLoaderOptions options) {
        if (v instanceof ImageView) {
            //将类型转换为ImageView
            ImageView imageView = (ImageView) v;
            //装配基本的参数
            DrawableTypeRequest dtr = Glide.with(imageView.getContext()).load(url);
            //装配附加参数
            loadOptions(dtr, options).into(imageView);
        } else {
            throw new IllegalArgumentException("请传入ImageView或其子类！");
        }
    }

    @Override
    public void load(View v, int resId) {
        load(v, resId, null);
    }

    @Override
    public void load(View v, int drawable, ImageLoaderOptions options) {
        if (v instanceof ImageView) {
            ImageView imageView = (ImageView) v;
            DrawableTypeRequest dtr = Glide.with(imageView.getContext()).load(drawable);
            loadOptions(dtr, options).into(imageView);
        } else {
            throw new IllegalArgumentException("请传入ImageView或其子类！");
        }
    }

    //这个方法用来装载由外部设置的参数
    private DrawableTypeRequest loadOptions(DrawableTypeRequest dtr, ImageLoaderOptions options) {
        if (options == null)
            return dtr;
        if (options.getPlaceHolder() != -1)
            dtr.placeholder(options.getPlaceHolder());
        if (options.getErrorDrawable() != -1)
            dtr.error(options.getErrorDrawable());
        if (options.isCrossFade())
            dtr.crossFade();
        if (options.isSkipMemoryCache())
            dtr.skipMemoryCache(options.isSkipMemoryCache());
        if (options.getAnimator() != null)
            dtr.animate(options.getAnimator());
        if (options.getSize() != null)
            dtr.override(options.getSize().reWidth, options.getSize().reHeight);
        if (options.getTransformations() != null)
            dtr.bitmapTransform(options.getTransformations());
        return dtr;
    }
}