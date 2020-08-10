package com.hengwei.module_home_tab_task;
import android.os.Bundle;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.geen.commonlibary.RouteConfig;
import com.geen.commonlibary.base.BaseActivity;
import com.hengwei.module_home_tab_task.ui.main.Tab1Fragment;

@Route(path = RouteConfig.ROUTE_TASK)
public class TabTaskMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_task_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Tab1Fragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}