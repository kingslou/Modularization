package com.geen.commonlibary.circularprogressbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;


/**
 * @author youtui
 */
public class ProgressBarCircularIndeterminate extends ProgressbarView {


    final static String ANDROIDXML = "http://schemas.android.com/apk/res/android";

    int backgroundColor =  0XFFEB252E;

    public ProgressBarCircularIndeterminate(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs);
    }

    protected void setAttributes(AttributeSet attrs) {

        setMinimumHeight(dp2px(20));
        setMinimumWidth(dp2px(20));

        if (attrs == null) {
            setBackgroundColor( 0xffEB252E);
            return;
        }

        int backgroundColor = attrs.getAttributeResourceValue(ANDROIDXML, "background", -1);
        if (backgroundColor != -1) {
            setBackgroundColor(getResources().getColor(backgroundColor));
        } else {
            // Color by hexadecimal
            int background = attrs.getAttributeIntValue(ANDROIDXML, "background", -1);
            if (background != -1) {
                setBackgroundColor(background);
            } else {
                setBackgroundColor( 0xffEB252E);
            }
        }
    }

    /**
     * Make a dark color to ripple effect
     *
     * @return
     */
    protected int makePressColor() {
        int r = (this.backgroundColor >> 16) & 0xFF;
        int g = (this.backgroundColor >> 8) & 0xFF;
        int b = (this.backgroundColor >> 0) & 0xFF;
        return Color.argb(128, r, g, b);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawSecondAnimation(canvas);
        invalidate();

    }

    float radius1 = 0;
    float radius2 = 0;
    int cont = 0;
    boolean firstAnimationOver = false;

    /**
     * Draw first animation of view
     *
     * @param canvas
     */
    private void drawFirstAnimation(Canvas canvas) {
        if (radius1 < getWidth() / 2) {
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(makePressColor());
            radius1 = (radius1 >= getWidth() / 2) ? (float) getWidth() / 2 : radius1 + 1;
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius1, paint);
        } else {
            Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas temp = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(makePressColor());
            temp.drawCircle(getWidth() / 2, getHeight() / 2, getHeight() / 2, paint);
            Paint transparentPaint = new Paint();
            transparentPaint.setAntiAlias(true);
            transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
            transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            if (cont >= 50) {
                radius2 = (radius2 >= getWidth() / 2) ? (float) getWidth() / 2 : radius2 + 1;
            } else {
                radius2 = (radius2 >= getWidth() / 2 - dp2px(4)) ? (float) getWidth() / 2 - dp2px(4) : radius2 + 1;
            }
            temp.drawCircle(getWidth() / 2, getHeight() / 2, radius2, transparentPaint);
            canvas.drawBitmap(bitmap, 0, 0, new Paint());
            if (radius2 >= getWidth() / 2 - dp2px(4)) {
                cont++;
            }
            if (radius2 >= getWidth() / 2) {
                firstAnimationOver = true;
            }
        }
    }

    int arcD = 1;
    int arcO = 0;
    float rotateAngle = 0;
    int limite = 0;

    /**
     * Draw second animation of view
     *
     * @param canvas
     */
    private void drawSecondAnimation(Canvas canvas) {
        if (arcO == limite) {
            arcD += 6;
        }
        if (arcD >= 290 || arcO > limite) {
            arcO += 6;
            arcD -= 6;
        }
        if (arcO > limite + 290) {
            limite = arcO;
            arcO = limite;
            arcD = 1;
        }
        rotateAngle += 4;
        canvas.rotate(rotateAngle, getWidth() / 2, getHeight() / 2);

        Bitmap bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas temp = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(backgroundColor);
        temp.drawArc(new RectF(0, 0, getWidth(), getHeight()), arcO, arcD, true, paint);
        Paint transparentPaint = new Paint();
        transparentPaint.setAntiAlias(true);
        transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
        transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        temp.drawCircle(getWidth() / 2, getHeight() / 2, (getWidth() / 2) - dp2px(3), transparentPaint);

        canvas.drawBitmap(bitmap, 0, 0, new Paint());
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        if (isEnabled()) {
            beforeBackground = backgroundColor;
        }
        this.backgroundColor = color;
    }

    private int dp2px(float dpValue) {
          float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}