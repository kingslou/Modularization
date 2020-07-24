package com.geen.commonlibary.utils;

import com.geen.commonlibary.log.LuoJiFileLog;
import com.socks.library.KLog;

/***
 * @author 86153
 * 日志工具类
 */
public class AppLogUtils {

    public static void v(Object msg) {
        KLog.v(msg);
    }

    public static void fileLog(Object msg) {

        LuoJiFileLog.d(AppLogUtils.class,msg.toString());
    }

    public static void v(Object msg, boolean writeFile) {
        KLog.v(msg);
        if (writeFile) {
            fileLog(msg);
        }
    }

    public static void d(Object msg) {
        KLog.d(msg);
    }

    public static void d(Object msg, boolean writeFile) {
        KLog.d(msg);
        if (writeFile) {
            fileLog(msg);
        }
    }

    public static void i(Object msg) {
        KLog.i(msg);
    }

    public static void i(Object msg, boolean writeFile) {
        KLog.i(msg);
        if (writeFile) {
            fileLog(msg);
        }
    }

    public static void w(Object msg) {
        KLog.w(msg);
    }

    public static void w(Object msg, boolean writeFile) {
        KLog.w(msg);
        if (writeFile) {
            fileLog(msg);
        }
    }

    public static void e(Object msg) {
        KLog.e(msg);
    }

    public static void e(Object msg, boolean writeFile) {
        KLog.e(msg);
        if (writeFile) {
            fileLog(msg);
        }
    }

}
