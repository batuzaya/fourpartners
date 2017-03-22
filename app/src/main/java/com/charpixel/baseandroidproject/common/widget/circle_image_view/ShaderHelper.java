package com.charpixel.baseandroidproject.common.widget.circle_image_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.util.DisplayMetrics;

import com.charpixel.baseandroidproject.R;


public abstract class ShaderHelper {
    private static final int ALPHA_MAX = 255;
    protected int viewWidth;
    protected int viewHeight;
    @ColorInt
    protected int borderColor = -16777216;
    protected int borderWidth = 0;
    protected float borderAlpha = 1.0F;
    protected boolean square = false;
    protected final Paint borderPaint = new Paint();
    protected final Paint imagePaint;
    protected BitmapShader shader;
    protected Drawable drawable;
    protected final Matrix matrix = new Matrix();

    public ShaderHelper() {
        this.borderPaint.setStyle(Style.STROKE);
        this.borderPaint.setAntiAlias(true);
        this.imagePaint = new Paint();
        this.imagePaint.setAntiAlias(true);
    }

    public abstract void draw(Canvas var1, Paint var2, Paint var3);

    public abstract void reset();

    public abstract void calculate(int var1, int var2, float var3, float var4, float var5, float var6, float var7);

    protected final int dpToPx(DisplayMetrics displayMetrics, int dp) {
        return Math.round((float) dp * (displayMetrics.xdpi / 160.0F));
    }

    public void init(Context context, AttributeSet attrs, int defStyle) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ShaderImageView, defStyle, 0);
            this.borderColor = typedArray.getColor(R.styleable.ShaderImageView_borderColor, this.borderColor);
            this.borderWidth = typedArray.getDimensionPixelSize(R.styleable.ShaderImageView_siBorderWidth, this.borderWidth);
            this.borderAlpha = typedArray.getFloat(R.styleable.ShaderImageView_borderAlpha, this.borderAlpha);
            this.square = typedArray.getBoolean(R.styleable.ShaderImageView_square, this.square);
            typedArray.recycle();
        }

        this.borderPaint.setColor(this.borderColor);
        this.borderPaint.setAlpha(Float.valueOf(this.borderAlpha * 255.0F).intValue());
        this.borderPaint.setStrokeWidth((float) this.borderWidth);
    }

    public boolean onDraw(Canvas canvas) {
        if (this.shader == null) {
            this.createShader();
        }

        if (this.shader != null && this.viewWidth > 0 && this.viewHeight > 0) {
            this.draw(canvas, this.imagePaint, this.borderPaint);
            return true;
        } else {
            return false;
        }
    }

    public void onSizeChanged(int width, int height) {
        if (this.viewWidth != width || this.viewHeight != height) {
            this.viewWidth = width;
            this.viewHeight = height;
            if (this.isSquare()) {
                this.viewWidth = this.viewHeight = Math.min(width, height);
            }

            if (this.shader != null) {
                this.calculateDrawableSizes();
            }

        }
    }

    public Bitmap calculateDrawableSizes() {
        Bitmap bitmap = this.getBitmap();
        if (bitmap != null) {
            int bitmapWidth = bitmap.getWidth();
            int bitmapHeight = bitmap.getHeight();
            if (bitmapWidth > 0 && bitmapHeight > 0) {
                float width = (float) Math.round((float) this.viewWidth - 2.0F * (float) this.borderWidth);
                float height = (float) Math.round((float) this.viewHeight - 2.0F * (float) this.borderWidth);
                float translateX = 0.0F;
                float translateY = 0.0F;
                float scale;
                if ((float) bitmapWidth * height > width * (float) bitmapHeight) {
                    scale = height / (float) bitmapHeight;
                    translateX = (float) Math.round((width / scale - (float) bitmapWidth) / 2.0F);
                } else {
                    scale = width / (float) bitmapWidth;
                    translateY = (float) Math.round((height / scale - (float) bitmapHeight) / 2.0F);
                }

                this.matrix.setScale(scale, scale);
                this.matrix.preTranslate(translateX, translateY);
                this.matrix.postTranslate((float) this.borderWidth, (float) this.borderWidth);
                this.calculate(bitmapWidth, bitmapHeight, width, height, scale, translateX, translateY);
                return bitmap;
            }
        }

        this.reset();
        return null;
    }

    public final void onImageDrawableReset(Drawable drawable) {
        this.drawable = drawable;
        this.shader = null;
        this.imagePaint.setShader(null);
    }

    protected void createShader() {
        Bitmap bitmap = this.calculateDrawableSizes();
        if (bitmap != null && bitmap.getWidth() > 0 && bitmap.getHeight() > 0) {
            this.shader = new BitmapShader(bitmap, TileMode.CLAMP, TileMode.CLAMP);
            this.imagePaint.setShader(this.shader);
        }

    }

    protected Bitmap getBitmap() {
        Bitmap bitmap = null;
        if (this.drawable != null && this.drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) this.drawable).getBitmap();
        }

        return bitmap;
    }

    public final int getBorderColor() {
        return this.borderColor;
    }

    public final void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
        if (this.borderPaint != null) {
            this.borderPaint.setColor(borderColor);
        }

    }

    public final int getBorderWidth() {
        return this.borderWidth;
    }

    public final void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        if (this.borderPaint != null) {
            this.borderPaint.setStrokeWidth((float) borderWidth);
        }

    }

    public final float getBorderAlpha() {
        return this.borderAlpha;
    }

    public final void setBorderAlpha(float borderAlpha) {
        this.borderAlpha = borderAlpha;
        if (this.borderPaint != null) {
            this.borderPaint.setAlpha(Float.valueOf(borderAlpha * 255.0F).intValue());
        }

    }

    public final void setSquare(boolean square) {
        this.square = square;
    }

    public final boolean isSquare() {
        return this.square;
    }
}
