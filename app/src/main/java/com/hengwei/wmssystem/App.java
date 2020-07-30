package com.hengwei.wmssystem;


import com.geen.commonlibary.BaseApplication;
import com.geen.commonlibary.config.UrlManger;
import com.geen.module_net.api.ApiClient;

public class App extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        ApiClient.getInstance().initApiClient(UrlManger.getApiUrl());

    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
