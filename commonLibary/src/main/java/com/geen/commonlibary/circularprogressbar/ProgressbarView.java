package com.geen.commonlibary.circularprogressbar;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * @author youtui
 */
public class ProgressbarView extends RelativeLayout {

    int disabledBackgroundColor = 0xffE2E2E2;
    int beforeBackground;

    /** Indicate if user touched this view the last time
     *
     */
    public boolean isLastTouch = false;

    public ProgressbarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        if (enabled) {
            setBackgroundColor(beforeBackground);
        } else {
            setBackgroundColor(disabledBackgroundColor);
        }
        invalidate();
    }

    boolean animation = false;

    @Override
    protected void onAnimationStart() {
        super.onAnimationStart();
        animation = true;
    }

    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
        animation = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (animation) {
            invalidate();
        }
    }
}