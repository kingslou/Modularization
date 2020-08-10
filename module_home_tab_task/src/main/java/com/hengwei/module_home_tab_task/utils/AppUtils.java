package com.hengwei.module_home_tab_task.utils;

import android.util.Log;

import com.geen.commonlibary.utils.ToastUtil;

public class  AppUtils {

    public static void showLog(String log){
        Log.e("xxx",log);
        ToastUtil.showTips(log);
    }
}
