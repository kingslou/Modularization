package com.geen.module_login;

import android.app.Application;

import com.geen.commonlibary.IComponentApplication;
import com.geen.componentmanger.ServiceFactory;
import com.socks.library.KLog;

public class LoginAppInit implements IComponentApplication {

    @Override
    public void onCreate(Application application) {
        KLog.e("初始化"+"Login");
        ServiceFactory.getInstance().setUserInfoService(new LoginUserService());
    }

}
