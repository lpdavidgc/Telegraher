package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.evildayz.code.telegraher.ThePenisMightierThanTheSword;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.SharedConfig;

public class BlurredFrameLayout extends FrameLayout {

    private final SizeNotifierFrameLayout sizeNotifierFrameLayout;
    protected Paint backgroundPaint;
    public int backgroundColor = Color.TRANSPARENT;
    public int backgroundPaddingBottom;
    public int backgroundPaddingTop;
    public boolean isTopView = true;
    public boolean drawBlur = true;

    public BlurredFrameLayout(@NonNull Context context, SizeNotifierFrameLayout sizeNotifierFrameLayout) {
        super(context);
        this.sizeNotifierFrameLayout = sizeNotifierFrameLayout;
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if (SharedConfig.chatBlurEnabled() && sizeNotifierFrameLayout != null && drawBlur && backgroundColor != Color.TRANSPARENT) {
            if (backgroundPaint == null) {
                backgroundPaint = new Paint();
            }
            backgroundPaint.setColor(backgroundColor);
            AndroidUtilities.rectTmp2.set(0, backgroundPaddingTop, getMeasuredWidth(), getMeasuredHeight() - backgroundPaddingBottom);
            float y = 0;
            View view = this;
            while (view != sizeNotifierFrameLayout) {
                y += view.getY();
                view = (View) view.getParent();
            }
            sizeNotifierFrameLayout.drawBlurRect(canvas, y, AndroidUtilities.rectTmp2, backgroundPaint, isTopView);
        }
        super.dispatchDraw(canvas);
    }

    @Override
    public void setBackgroundColor(int color) {
        if (SharedConfig.chatBlurEnabled() && sizeNotifierFrameLayout != null) {
            backgroundColor = color;
        } else {
            super.setBackgroundColor(color);
        }
    }

    @Override
    protected void onAttachedToWindow() {
        if (SharedConfig.chatBlurEnabled() && sizeNotifierFrameLayout != null) {
            sizeNotifierFrameLayout.blurBehindViews.add(this);
        }
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        if (sizeNotifierFrameLayout != null) {
            sizeNotifierFrameLayout.blurBehindViews.remove(this);
        }
        super.onDetachedFromWindow();
    }
}
