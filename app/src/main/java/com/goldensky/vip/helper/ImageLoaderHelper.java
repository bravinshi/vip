package com.goldensky.vip.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.goldensky.framework.util.StringUtils;

/**
 * Created by Administrator on 2017/8/24 0024.
 */

public class ImageLoaderHelper {

    public static void loadImage(ImageView imageView, String imagePth) {
        loadImage(imageView, imagePth, 0);
    }

    public static void loadImage(ImageView imageView, String imagePath, @DrawableRes int placeHolder) {
        loadImage(imageView, imagePath, placeHolder, placeHolder);
    }

    public static void loadImage(ImageView imageView, String imagePath, @DrawableRes int placeHolder, @DrawableRes int errRes) {
        if (StringUtils.isTrimEmpty(imagePath)) {
            imageView.setImageResource(errRes);
        } else {
            RequestOptions options = new RequestOptions();
            options.diskCacheStrategy(DiskCacheStrategy.ALL);

            imagePath = fixUrl(imagePath);
            Glide.with(imageView.getContext())
                    .asBitmap()
                    .apply(options)
                    .load(imagePath)
                    .into(imageView);
        }
    }

    public static String fixUrl(String url) {

        if (!TextUtils.isEmpty(url) && url.startsWith("http://") && url.contains("\\")) {
            url = url.substring(7);
            url = url.replaceAll("\\\\", "/");
            url = "http://".concat(url);
            return url;
        }
        return url;
    }

    public static void loadImage(ImageView imageView, int resid) {
        Glide.with(imageView.getContext()).load(resid).into(imageView);
    }

    /**
     * 加再圆形图
     *
     * @param imageView
     * @param imagePath
     * @param placeholderid 占位图资源id
     */
    public static void loadRoundImage(final ImageView imageView, String imagePath, int placeholderid) {
        //imageView.setImageResource(placeholderid);

        RequestOptions options = new RequestOptions()
                .placeholder(placeholderid)
                .error(placeholderid)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        imagePath = fixUrl(imagePath);
        Glide.with(imageView.getContext())
                .asBitmap()
                .apply(options)
                .load(imagePath)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(imageView.getResources(), resource);
                        roundedBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(roundedBitmapDrawable);
                    }
                });


    }

    /**
     * 圆角
     *
     * @param imageView
     * @param imagePath
     * @param placeholderid 占位图资源id
     */
    public static void loadRoundImage1(final ImageView imageView, String imagePath, int placeholderid) {

        RequestOptions options = new RequestOptions()
                .placeholder(placeholderid)
                .error(placeholderid)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        imagePath = fixUrl(imagePath);
        Glide.with(imageView.getContext())
                .asBitmap()
                .apply(options)
                .load(imagePath)
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(imageView.getContext().getResources(), resource);
                        circularBitmapDrawable.setCornerRadius(10); //设置圆角弧度
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });
    }

    public static void loadImage(Context context, ImageView imageView, String imagePath, int width, int height) {
        RequestOptions options = new RequestOptions()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .override(width, height);

        Glide.with(context)
                .load(imagePath)
                .apply(options)
                .into(imageView);
    }

}
