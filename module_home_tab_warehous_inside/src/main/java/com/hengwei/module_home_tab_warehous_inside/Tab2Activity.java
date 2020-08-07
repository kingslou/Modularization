package com.hengwei.module_home_tab_warehous_inside;
import android.os.Bundle;
import com.geen.commonlibary.base.BaseActivity;
import com.hengwei.module_home_tab_warehous_inside.ui.main.Tab2Fragment;

public class Tab2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_inside_main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Tab2Fragment.newInstance())
                    .commitNow();
        }
    }
}