package com.geen.commonlibary.utils;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

public class HandleUtils {
    public static Handler sUiHandler = new Handler(Looper.getMainLooper());

    private static HandlerThread sHandlerThread = new HandlerThread("Background Thread");

    static {
        sHandlerThread.start();
    }

    public static Handler sBgHandler = new Handler(sHandlerThread.getLooper());
}
