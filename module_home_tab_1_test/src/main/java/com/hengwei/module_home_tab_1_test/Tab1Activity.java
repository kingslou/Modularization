package com.hengwei.module_home_tab_1_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.geen.commonlibary.base.BaseActivity;
import com.hengwei.module_home_tab_1_test.ui.main.Tab1Fragment;

public class Tab1Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab1_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Tab1Fragment.newInstance())
                    .commitNow();
        }
    }
}