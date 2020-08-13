package com.hengwei.module_home_tab_task.ui.main
import android.os.Bundle
import com.geen.commonlibary.base.BaseActivity
import com.geen.commonlibary.mvp.BasePresenter
import com.hengwei.module_home_tab_task.R

class DetailListActivity : BaseActivity<BasePresenter>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tab_task_activity_detali_list)
    }
}