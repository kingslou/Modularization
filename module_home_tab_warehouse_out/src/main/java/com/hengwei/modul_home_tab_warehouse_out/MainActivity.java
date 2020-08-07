package com.hengwei.modul_home_tab_warehouse_out;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hengwei.modul_home_tab_warehouse_out.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab3_main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }
}