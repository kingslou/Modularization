package com.hengwei.modul_home_tab_warehouse_out.debug;
import com.geen.commonlibary.BaseApplication;

public class DebugApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean isDebug() {
        return false;
    }

    @Override
    public void initApiClient() {

    }
}
