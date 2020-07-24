package debug;
import com.geen.commonlibary.BaseApplication;
import com.geen.module_login.LoginAppInit;

public class DebugApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        LoginAppInit loginAppInit = new LoginAppInit();
        loginAppInit.onCreate(this);
    }
}
