package com.hengwei.module_main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;


public class HomePagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragmentArrayList;


    public HomePagerAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fragmentArrayList = fragments;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList!=null?fragmentArrayList.size():0;
    }



}
