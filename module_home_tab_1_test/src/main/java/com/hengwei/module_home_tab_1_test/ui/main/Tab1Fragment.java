package com.hengwei.module_home_tab_1_test.ui.main;

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
import com.hengwei.module_home_tab_1_test.R;
import com.hengwei.module_home_tab_1_test.Tab1Activity;

@Route(path = RouteConfig.ROUTE_FRAGMENT_TAB1)
public class Tab1Fragment extends BaseFragment {

    private MainViewModel mViewModel;

    public static Tab1Fragment newInstance() {
        return new Tab1Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab1_main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(mActivity).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

}