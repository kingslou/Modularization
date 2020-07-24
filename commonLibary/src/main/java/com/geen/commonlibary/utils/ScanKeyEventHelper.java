package com.geen.commonlibary.utils;

import android.os.Handler;
import android.view.KeyEvent;

/**
 * @author Administrator
 * @date 创建时间 2018/7/29
 * @Description 扫码监听工具类
 */

public class ScanKeyEventHelper {

    private StringBuffer mStringBufferResult = new StringBuffer();
    private boolean mCaps;
    private OnScanSuccessListener mOnScanSuccessListener;
    private Handler mHandler = new Handler();

    private static ScanKeyEventHelper scanKeyEventHelper = new ScanKeyEventHelper();

    public static ScanKeyEventHelper getInstance() {
        return scanKeyEventHelper;
    }

    private final Runnable mScanningFishedRunnable = () -> performScanSuccess();

    //返回扫描结果
    private void performScanSuccess() {
        String barcode = mStringBufferResult.toString();
        if (mOnScanSuccessListener != null ) {
            mOnScanSuccessListener.onScanSuccess(barcode);
        }
        mStringBufferResult = new StringBuffer();
    }

    public void analysisKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        checkLetterStatus(event);
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            char aChar = getInputCode(event);
            ;
            if (aChar != 0) {
                mStringBufferResult.append(aChar);
            }
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                mHandler.removeCallbacks(mScanningFishedRunnable);
                mHandler.post(mScanningFishedRunnable);
            } else {

            }

        }
    }

    private void checkLetterStatus(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_SHIFT_RIGHT || keyCode == KeyEvent.KEYCODE_SHIFT_LEFT) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                mCaps = true;
            } else {
                mCaps = false;
            }
        }
    }

    private char getInputCode(KeyEvent event) {
        int keyCode = event.getKeyCode();
        char aChar;
        if (keyCode >= KeyEvent.KEYCODE_A && keyCode <= KeyEvent.KEYCODE_Z) {
            aChar = (char) ((mCaps ? 'A' : 'a') + keyCode - KeyEvent.KEYCODE_A);
        } else if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9 && keyCode != KeyEvent.KEYCODE_3) {
            aChar = (char) ('0' + keyCode - KeyEvent.KEYCODE_0);
        } else {
            switch (keyCode) {
                case KeyEvent.KEYCODE_PERIOD:
                    aChar = '.';
                    break;
                case KeyEvent.KEYCODE_MINUS:
                    aChar = mCaps ? '_' : '-';
                    break;
                case KeyEvent.KEYCODE_SLASH:
                    aChar = '/';
                    break;
                case KeyEvent.KEYCODE_BACKSLASH:
                    aChar = mCaps ? '|' : '\\';
                    break;
                case KeyEvent.KEYCODE_3:
                    aChar = mCaps ? '#' : '3';
                    break;
                default:
                    aChar = 0;
                    break;
            }
        }

        return aChar;
    }


    public interface OnScanSuccessListener {
        void onScanSuccess(String barcode);
    }

    public void setOnBarCodeCatchListener(OnScanSuccessListener onScanSuccessListener) {
        mOnScanSuccessListener = onScanSuccessListener;
    }

    public void onDestroy() {
        mHandler.removeCallbacks(mScanningFishedRunnable);
        mOnScanSuccessListener = null;
    }

}
