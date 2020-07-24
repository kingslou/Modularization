package com.geen.commonlibary.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.ToastUtils;
import com.geen.commonlibary.BaseApplication;
import com.geen.commonlibary.R;

/**
 * @author youtui
 */
public class ToastUtil {

    public static void showToast(Context context, int resId) {
        String content = context.getString(resId);
        showToast(context, content);
    }

    public static void showToast(Context context, String content) {
        if (!TextUtils.isEmpty(content)) {
            showTips(content);
        }
    }

    public static void showToast(String content) {
        if (!TextUtils.isEmpty(content)) {
            showTips(content);
        }
    }

    public static void showLongToast(Context context, String content) {
        if (!TextUtils.isEmpty(content)) {
            showTips(content);
        }
    }

    public static void showTips(Context context, String tipsString) {
        showTips(context, tipsString, 0);
    }

    public static void showTips(Context context, String tipsString, int duration) {
        if(TextUtils.isEmpty(tipsString)){
            return;
        }
        final Toast t = new Toast(context);
        t.setGravity(Gravity.CENTER, 0, 0);
        if (duration == 0) {
            t.setDuration(Toast.LENGTH_SHORT);
        } else {
            t.setDuration(Toast.LENGTH_LONG);
        }
        LayoutInflater mInflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams")
        View view = mInflater.inflate(R.layout.toast_tips_layout, null);
        TextView text = view.findViewById(R.id.text_toast);
        text.setText(tipsString);
        view.setMinimumWidth(9999);
        view.setMinimumHeight(ScreenUtil.dp2px(context, 48));
        t.setView(view);
        t.show();
    }

    public static void showTips(String tipsString) {
        showTips(BaseApplication.getInstance(), tipsString, 0);
    }

    public static void showTips(String tipsString, int duration) {
        showTips(BaseApplication.getInstance(), tipsString, duration);

    }


}
