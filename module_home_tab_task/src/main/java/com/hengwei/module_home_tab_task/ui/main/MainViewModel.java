package com.hengwei.module_home_tab_task.ui.main;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geen.commonlibary.mvp.BasePresenter;
import com.geen.commonlibary.utils.HandleUtils;
import com.hengwei.module_home_tab_task.bean.ExpandItem;
import com.hengwei.module_home_tab_task.bean.MainInfo;
import java.util.ArrayList;
import java.util.List;

/***
 * @author 86153
 * viewmodel
 */
public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<List<MainInfo>> mutableLiveData = new MutableLiveData<>();

    public MainViewModel(BasePresenter presenter) {

    }

    public LiveData<List<MainInfo>> getMainList() {
        //模拟一个延时操作
        HandleUtils.sUiHandler.postDelayed(() -> {
            List<MainInfo> mainInfoList = new ArrayList<>();
            List<ExpandItem> expandItemList = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                expandItemList.add(new ExpandItem("测试" + i));
            }
            mainInfoList.add(new MainInfo("菜单一", expandItemList));
            mainInfoList.add(new MainInfo("菜单二", expandItemList));
            mainInfoList.add(new MainInfo("菜单三", expandItemList));
            mainInfoList.add(new MainInfo("菜单四", expandItemList));
            mutableLiveData.setValue(mainInfoList);
        },3000);
        return mutableLiveData;
    }

}