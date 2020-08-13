package com.hengwei.module_home_tab_task

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.geen.commonlibary.RouteConfig
import com.geen.commonlibary.base.BaseActivity
import com.geen.commonlibary.mvp.BasePresenter
import com.hengwei.module_home_tab_task.ui.main.Tab1Fragment

@Route(path = RouteConfig.ROUTE_TASK)
class TabTaskMainActivity : BaseActivity<BasePresenter?>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_task_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, Tab1Fragment.newInstance())
                    .commitNow()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}