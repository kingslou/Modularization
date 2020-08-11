package com.geen.commonlibary.widget;

import android.content.Context;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by sky on 18/4/14.
 */

public class DividerUtils {

    /**
     * Recyclerview orientation vertical  使用这个
     *
     * @param context
     * @param resId
     * @param colorId
     * @return
     */
    public static RecyclerView.ItemDecoration getHorDivider(Context context, @DimenRes int resId, @ColorRes int colorId) {
        return new HorizontalDividerItemDecoration.Builder(context)
                .sizeResId(resId)
                .colorResId(colorId)
                .build();
    }

    /**
     * Recyclerview orientation horizontal  使用这个
     *
     * @param context
     * @param resId
     * @param colorId
     * @return
     */
    public static RecyclerView.ItemDecoration getVerDivider(Context context, @DimenRes int resId, @ColorRes int colorId) {
        return new VerticalDividerItemDecoration.Builder(context)
                .sizeResId(resId)
                .colorResId(colorId)
                .build();
    }

    public static RecyclerView.ItemDecoration getHorDividerWithLMargin(Context context,
                                                                       @DimenRes int resId,
                                                                       int marginStart,
                                                                       int marginEnd,
                                                                       @DrawableRes int drawResId) {
        return new HorizontalDividerItemDecoration.Builder(context)
                .drawable(drawResId)
                .sizeResId(resId)
                .margin(marginStart, marginEnd)
                .build();
    }

}
