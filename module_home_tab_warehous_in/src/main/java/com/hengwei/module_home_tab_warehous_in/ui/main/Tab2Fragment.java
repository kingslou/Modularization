package com.hengwei.module_home_tab_warehous_in.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.geen.commonlibary.RouteConfig;
import com.geen.commonlibary.base.BaseFragment;
import com.hengwei.module_home_tab_warehous_in.R;

@Route(path = RouteConfig.ROUTE_FRAGMENT_TAB2)
public class Tab2Fragment extends BaseFragment {

    private MainViewModel mViewModel;

    public static Tab2Fragment newInstance() {
        return new Tab2Fragment();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab2_main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(mActivity).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}