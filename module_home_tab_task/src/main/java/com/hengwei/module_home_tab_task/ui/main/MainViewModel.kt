package com.hengwei.module_home_tab_task.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geen.commonlibary.utils.HandleUtils
import com.hengwei.module_home_tab_task.bean.ExpandItem
import com.hengwei.module_home_tab_task.bean.MainInfo
import java.util.*

/***
 * @author 86153
 * viewmodel
 */
class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val mutableLiveData = MutableLiveData<List<MainInfo>>()

    fun getMain() : MutableLiveData<List<MainInfo>>{
        //模拟一个延时操作
        HandleUtils.sUiHandler.postDelayed({
            val mainInfoList: MutableList<MainInfo> = ArrayList()
            val expandItemList: MutableList<ExpandItem> = ArrayList()
            for (i in 0..9) {
                expandItemList.add(ExpandItem("测试$i"))
            }
            mainInfoList.add(MainInfo("菜单一", expandItemList))
            mainInfoList.add(MainInfo("菜单二", expandItemList))
            mainInfoList.add(MainInfo("菜单三", expandItemList))
            mainInfoList.add(MainInfo("菜单四", expandItemList))
            mutableLiveData.setValue(mainInfoList)
        }, 3000)
        return mutableLiveData
    }
}