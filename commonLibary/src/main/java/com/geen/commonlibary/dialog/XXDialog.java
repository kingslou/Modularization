package com.geen.commonlibary.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.os.Build;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.annotation.StyleRes;
import com.geen.commonlibary.R;


public abstract class XXDialog {
    private CustomDialog mDialog;
    private Window mDialogWindow;
    private DialogViewHolder dilaogVh;
    public View mRootView;
    protected Context context;

    public XXDialog(Context context, int layoutId) {
        this.context = context;
        dilaogVh = DialogViewHolder.get(context, layoutId);
        mRootView = dilaogVh.getConvertView();
        mDialog = new CustomDialog(context, R.style.common_dialog);
        mDialog.setContentView(mRootView);
        mDialogWindow = mDialog.getWindow();

        convert(dilaogVh);
    }

    /*---------------------------------------文庆新增  除拦截事件外 其他不要用----------------------------------*/


    /**
     * 重写 dialog里面触摸事件
     *
     * @param onTouchEventDialog
     * @return
     */
    public XXDialog setOnTouchEventDialog(CustomDialog.OnTouchEventCustom onTouchEventDialog) {
        mDialog.setOnTouchEventCustom(onTouchEventDialog);
        return this;
    }
    /*---------------------------------------文庆新增  除拦截事件外 其他不要用----------------------------------*/

    /**
     * 把弹出框view holder传出去
     *
     * @param holder
     */
    public abstract void convert(DialogViewHolder holder);

    public static AlertDialog.Builder creatNormalDialogBuilder(Context context, String title, String message) {
        return new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message);
    }

    /**
     * 显示dialog
     */
    public XXDialog showDialog() {
        if (context != null && context instanceof Activity && ((Activity) context).isFinishing()) {
            return this;
        }
        if (context != null && mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
        return this;
    }

    private void fullScreenImmersive(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;
            view.setSystemUiVisibility(uiOptions);
        }
    }

    /**
     * 显示dialog
     */
    public XXDialog showDialogNotFocus() {
        if (mDialog != null && !mDialog.isShowing()) {
            mDialogWindow.setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            mDialog.show();
            fullScreenImmersive(mDialogWindow.getDecorView());
            mDialogWindow.clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        }
        return this;
    }

    /**
     * @param light 弹出时背景亮度 值为0.0~1.0    1.0表示全黑  0.0表示全白
     * @return
     */
    public XXDialog backgroundLight(double light) {
        if (light < 0.0 || light > 1.0) {
            return this;
        }
        WindowManager.LayoutParams lp = mDialogWindow.getAttributes();
        lp.dimAmount = (float) light;
        mDialogWindow.setAttributes(lp);
        return this;
    }

    /**
     * 从底部一直弹到中间
     */
    public XXDialog fromBottomToMiddle() {
        mDialogWindow.setWindowAnimations(R.style.common_window_bottom_in_bottom_out_p);
        mDialogWindow.setGravity(Gravity.CENTER);
        return this;
    }

    /**
     * 从底部弹出
     */
    public XXDialog fromBottom() {
        mDialogWindow.setWindowAnimations(R.style.common_window_bottom_in_bottom_out);
        mDialogWindow.setGravity(Gravity.BOTTOM);
        return this;
    }


    /**
     * @param style 显示一个Dialog自定义一个弹出方式  具体怎么写 可以模仿上面的
     * @return
     */
    public XXDialog showDialog(@StyleRes int style) {
        mDialogWindow.setWindowAnimations(style);
        mDialog.show();
        return this;
    }

    /**
     * 全屏显示
     */
    public XXDialog fullScreen() {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }


    public XXDialog setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        mDialog.setOnKeyListener(onKeyListener);
        return this;
    }

    /**
     * 全屏宽度
     */
    public XXDialog fullWidth() {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * 全屏高度
     */
    public XXDialog fullHeight() {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;
        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * @param width  自定义的宽度
     * @param height 自定义的高度
     * @return
     */
    public XXDialog setWidthAndHeight(int width, int height) {
        WindowManager.LayoutParams wl = mDialogWindow.getAttributes();
        wl.width = width == 0 ? ViewGroup.LayoutParams.WRAP_CONTENT : width;
        wl.height = height == 0 ? ViewGroup.LayoutParams.WRAP_CONTENT : height;

        mDialog.onWindowAttributesChanged(wl);
        return this;
    }

    /**
     * cancel dialog
     */
    public void cancelDialog() {
        if (mDialog != null && mDialog.isShowing()) {
            dismiss();
        }
    }

    public boolean isShowing() {
        return mDialog.isShowing();
    }

    /**
     * cancel dialog
     */
    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    /**
     * 设置监听
     */
    public XXDialog setDialogDismissListener(OnDismissListener listener) {
        mDialog.setOnDismissListener(listener);
        return this;
    }

    /**
     * 设置监听
     */
    public XXDialog setOnCancelListener(OnCancelListener listener) {
        mDialog.setOnCancelListener(listener);
        return this;
    }

    /**
     * 设置是否能取消
     */
    public XXDialog setCancelAble(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }


    /**
     * 设置触摸其他地方是否能取消
     */
    public XXDialog setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    /**
     * 设置触摸其他地方是否能取消
     */
    public XXDialog setOnShowListener(DialogInterface.OnShowListener listener) {
        mDialog.setOnShowListener(listener);
        return this;
    }

    //盖在状态栏上
    public XXDialog hideSystemUI(View view) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//            );
//        }

        mDialog.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        return this;
    }


    public int getHeight() {
        return mDialog.getWindow().getAttributes().height;
    }

    /**
     * 包一层
     */
    public static class CustomDialog extends Dialog {

        public interface OnTouchEventCustom {
            void onTouchEvent(MotionEvent event);
        }

        OnTouchEventCustom onTouchEventCustom;

        public void setOnTouchEventCustom(OnTouchEventCustom onTouchEventCustom) {
            this.onTouchEventCustom = onTouchEventCustom;
        }


        public CustomDialog(Context context) {
            super(context);
        }

        public CustomDialog(Context context, int themeResId) {
            super(context, themeResId);
        }

        protected CustomDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
            super(context, cancelable, cancelListener);
        }


        @Override
        public boolean onTouchEvent(MotionEvent event) {
            if (onTouchEventCustom != null) {
                onTouchEventCustom.onTouchEvent(event);
                return true;
            }
            return super.onTouchEvent(event);
        }
    }
}

//使用方法
//
//
//public void click(View view) {
//        XXDialog xxDialog = new XXDialog(this, R.layout.dialog) {
//@Override
//public void convert(DialogViewHolder holder) {  //holder 很重要避免多次创建
//        holder.setOnClick(R.id.tv, new View.OnClickListener() {
//@Override
//public void onClick(View v) {
//        Toast.makeText(MainActivity.this, "点了", Toast.LENGTH_SHORT).show();
//        }
//        });
//        }
//        }.fromBottom().fullWidth().showDialog().setCanceledOnTouchOutside(true);
//
//        }




