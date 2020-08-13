package com.hengwei.module_home_tab_task.utils

import android.util.Log
import com.geen.commonlibary.utils.ToastUtil

object AppUtils {
    @JvmStatic
    fun showLog(log: String?) {
        Log.e("xxx", log)
        ToastUtil.showTips(log)
    }
}

fun test(){

}