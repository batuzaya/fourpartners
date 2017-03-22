package com.charpixel.baseandroidproject.common.widget.circle_image_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

public class CircleShader extends ShaderHelper {
    private float center;
    private float bitmapCenterX;
    private float bitmapCenterY;
    private float borderRadius;
    private int bitmapRadius;

    public CircleShader() {
    }

    public void init(Context context, AttributeSet attrs, int defStyle) {
        super.init(context, attrs, defStyle);
        this.square = true;
    }

    public void draw(Canvas canvas, Paint imagePaint, Paint borderPaint) {
//        canvas.drawCircle(this.center, this.center, this.borderRadius, borderPaint);
        canvas.save();
        canvas.concat(this.matrix);
        canvas.drawCircle(this.bitmapCenterX, this.bitmapCenterY, (float) this.bitmapRadius, imagePaint);
        canvas.restore();
    }

    public void onSizeChanged(int width, int height) {
        super.onSizeChanged(width, height);
        this.center = (float) Math.round((float) this.viewWidth / 2.0F);
        this.borderRadius = (float) Math.round((float) (this.viewWidth - this.borderWidth) / 2.0F);
    }

    public void calculate(int bitmapWidth, int bitmapHeight, float width, float height, float scale, float translateX, float translateY) {
        this.bitmapCenterX = (float) Math.round((float) bitmapWidth / 2.0F);
        this.bitmapCenterY = (float) Math.round((float) bitmapHeight / 2.0F);
        this.bitmapRadius = Math.round(width / scale / 2.0F + 0.5F);
    }

    public void reset() {
        this.bitmapRadius = 0;
        this.bitmapCenterX = 0.0F;
        this.bitmapCenterY = 0.0F;
    }

    public final float getBorderRadius() {
        return this.borderRadius;
    }

    public final void setBorderRadius(float borderRadius) {
        this.borderRadius = borderRadius;
    }
}
