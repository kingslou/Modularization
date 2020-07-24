package com.geen.commonlibary.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;

import com.geen.commonlibary.R;

/**
 *
 * @author Shey
 * @date 16/10/21
 */

public class ProgressDialog extends Dialog {

    public ProgressDialog(Context context) {
        this(context, true, null);
    }

    public ProgressDialog(Context context, int theme) {
        this(context, true, null);
    }

    private ProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_progress);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }
}
