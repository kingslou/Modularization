package com.hengwei.module_home_tab_2_test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.geen.commonlibary.base.BaseActivity;
import com.hengwei.module_home_tab_2_test.ui.main.MainFragment;

public class Tab2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab2_main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}