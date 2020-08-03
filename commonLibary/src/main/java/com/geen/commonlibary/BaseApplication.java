package com.geen.commonlibary;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.geen.commonlibary.log.LuoJiFileLog;
import com.geen.commonlibary.utils.ConfigUtil;
import com.geen.commonlibary.utils.ToastUtil;
import com.socks.library.KLog;

public abstract class BaseApplication extends Application {

    private static BaseApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        if (isDebug()) {
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
        ConfigUtil.mPref = getSharedPreferences("hengwei_sys", MODE_PRIVATE);
        LuoJiFileLog.start(this);
        if (isDebug()) {
            KLog.init(true, "retrofit");
        }
        modulesApplicationInit();

        initApiClient();
    }

    public abstract boolean isDebug();

    public abstract void initApiClient();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    private void modulesApplicationInit() {
        for (String moduleImpl : ModuleConfig.MODULESLIST) {
            try {
                Class<?> clazz = Class.forName(moduleImpl);
                Object obj = clazz.newInstance();
                if (obj instanceof IComponentApplication) {
                    ((IComponentApplication) obj).onCreate(BaseApplication.getInstance());
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }
}
