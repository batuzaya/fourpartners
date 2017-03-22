
package com.charpixel.baseandroidproject.common.widget.circle_image_view;

import android.content.Context;
import android.util.AttributeSet;

public class CircularImageView extends ShaderImageView {
    private CircleShader shader;

    public CircularImageView(Context context) {
        super(context);
    }

    public CircularImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircularImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ShaderHelper createImageViewHelper() {
        this.shader = new CircleShader();
        return this.shader;
    }

    public float getBorderRadius() {
        return this.shader != null ? this.shader.getBorderRadius() : 0.0F;
    }

    public void setBorderRadius(float borderRadius) {
        if (this.shader != null) {
            this.shader.setBorderRadius(borderRadius);
            this.invalidate();
        }

    }
}
