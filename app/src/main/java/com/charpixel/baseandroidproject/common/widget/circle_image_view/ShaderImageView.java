
package com.charpixel.baseandroidproject.common.widget.circle_image_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public abstract class ShaderImageView extends ImageView {
    private static final boolean DEBUG = false;
    private ShaderHelper pathHelper;

    public ShaderImageView(Context context) {
        super(context);
        this.setup(context, null, 0);
    }

    public ShaderImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setup(context, attrs, 0);
    }

    public ShaderImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setup(context, attrs, defStyle);
    }

    private void setup(Context context, AttributeSet attrs, int defStyle) {
        this.getPathHelper().init(context, attrs, defStyle);
    }

    protected ShaderHelper getPathHelper() {
        if (this.pathHelper == null) {
            this.pathHelper = this.createImageViewHelper();
        }

        return this.pathHelper;
    }

    protected abstract ShaderHelper createImageViewHelper();

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.getPathHelper().isSquare()) {
            //noinspection SuspiciousNameCombination
            super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }

    }

    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        this.getPathHelper().onImageDrawableReset(this.getDrawable());
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        this.getPathHelper().onImageDrawableReset(this.getDrawable());
    }

    public void setImageResource(int resId) {
        super.setImageResource(resId);
        this.getPathHelper().onImageDrawableReset(this.getDrawable());
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.getPathHelper().onSizeChanged(w, h);
    }

    public void onDraw(Canvas canvas) {
        if (!this.getPathHelper().onDraw(canvas)) {
            super.onDraw(canvas);
        }

    }

    public void setBorderColor(int borderColor) {
        this.getPathHelper().setBorderColor(borderColor);
        this.invalidate();
    }

    public int getBorderWidth() {
        return this.getPathHelper().getBorderWidth();
    }

    public void setBorderWidth(int borderWidth) {
        this.getPathHelper().setBorderWidth(borderWidth);
        this.invalidate();
    }

    public float getBorderAlpha() {
        return this.getPathHelper().getBorderAlpha();
    }

    public void setBorderAlpha(float borderAlpha) {
        this.getPathHelper().setBorderAlpha(borderAlpha);
        this.invalidate();
    }

    public void setSquare(boolean square) {
        this.getPathHelper().setSquare(square);
        this.invalidate();
    }
}