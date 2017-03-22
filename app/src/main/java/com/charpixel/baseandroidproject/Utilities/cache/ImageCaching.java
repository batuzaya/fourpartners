package com.charpixel.baseandroidproject.Utilities.cache;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.io.File;

/**
 * ImageCaching
 */
public class ImageCaching {

    private static Context context;
    private RequestCreator builder;

    public RequestCreator getBuilder() {
        return builder;
    }

    /**
     * @param builder RequestCreator for particular image url
     */
    public void setBuilder(RequestCreator builder) {
        this.builder = builder;
    }

    public static void initialize(Context context) {
        ImageCaching.context = context;
    }

    /**
     * @param imageUrl to load image into ImageView
     * @return instance of image catching
     */
    public static ImageCaching load(String imageUrl) {
        if (context == null) {
            throw new NullPointerException("Context may not be null");
        }
        ImageCaching imageCaching = new ImageCaching();
        if (TextUtils.isEmpty(imageUrl)) {
            return imageCaching;
        }
        imageCaching.setBuilder(Picasso.with(context).load(imageUrl));
        return imageCaching;
    }

    public static ImageCaching load(File file) {
        if (context == null) {
            throw new NullPointerException("Context may not be null");
        }
        ImageCaching imageCaching = new ImageCaching();
        if (file == null) {
            return imageCaching;
        }
        imageCaching.setBuilder(Picasso.with(context).load(file));
        return imageCaching;
    }

    /**
     * @param target is ImageView where load image from server
     * @return instance of image catching
     */
    public ImageCaching into(ImageView target) {
        if (getBuilder() != null) {
            getBuilder().into(target);
        }
        return this;
    }

    /**
     * @param placeholderResId resourceId for place holder
     * @return instance of image catching
     */
    public ImageCaching placeholder(int placeholderResId) {
        if (getBuilder() != null) {
            getBuilder().placeholder(placeholderResId);
        }
        return this;
    }

    public void fetch(final Callback callback) {
        if (getBuilder() != null) {
            getBuilder().fetch(new com.squareup.picasso.Callback() {
                @Override
                public void onSuccess() {
                    callback.onSuccess();
                    callback.onComplete();
                }

                @Override
                public void onError() {
                    callback.onError();
                    callback.onComplete();
                }
            });
        }
    }

    public static class SimpleCallback implements Callback {

        @Override
        public void onSuccess() {

        }

        @Override
        public void onError() {

        }

        @Override
        public void onComplete() {

        }
    }

    public interface Callback {
        void onSuccess();

        void onError();

        void onComplete();
    }
}
