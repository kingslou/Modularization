package debug;

import com.geen.commonlibary.BaseApplication;
import com.hengwei.module_main.BuildConfig;

public class DebugApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override
    public void initApiClient() {

    }
}
