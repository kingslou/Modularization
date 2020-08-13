package debug

import com.geen.commonlibary.BaseApplication

class DebugApplication : BaseApplication() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun isDebug(): Boolean {
        return false
    }

    override fun initApiClient() {}
}