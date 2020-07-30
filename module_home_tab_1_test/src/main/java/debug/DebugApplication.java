package debug;
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
}
