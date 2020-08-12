package com.hengwei.module_home_tab_task.ui.main;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.geen.commonlibary.RouteConfig;
import com.geen.commonlibary.base.BaseFragment;
import com.hengwei.module_home_tab_task.bean.MainInfo;
import com.hengwei.module_home_tab_task.databinding.TabTaskMainFragmentBinding;
import com.hengwei.module_home_tab_task.ui.main.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouteConfig.ROUTE_FRAGMENT_TAB1)
public class Tab1Fragment extends BaseFragment {

    private MainViewModel mViewModel;

    private TabTaskMainFragmentBinding mBinding;
    private MainAdapter mainAdapter;
    private List<MainInfo> mainInfoList = new ArrayList<>();

    public static Tab1Fragment newInstance() {
        return new Tab1Fragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mBinding = TabTaskMainFragmentBinding.inflate(inflater, container, false);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {

        mainAdapter = new MainAdapter(mainInfoList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        mBinding.recycleView.setLayoutManager(layoutManager);
        mBinding.recycleView.setAdapter(mainAdapter);
        mBinding.recycleView.addItemDecoration(new DividerItemDecoration(getActivity(), RecyclerView.VERTICAL));

        mainAdapter.setOnItemClickListener((adapter, view, position) -> {

            MainInfo info = mainInfoList.get(position);
            if (info.isShowExpandView()) {
                info.setShowExpandView(false);
            } else {
                resetExpandView();
                info.setShowExpandView(true);
            }
            mainAdapter.setList(mainInfoList);
        });

    }

    private void resetExpandView() {
        if (mainAdapter != null && mainAdapter.getData() != null) {
            for (MainInfo info : mainAdapter.getData()) {
                info.setShowExpandView(false);
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(mActivity).get(MainViewModel.class);
        // TODO: Use the ViewModel



        mViewModel.getMainList().observe(getViewLifecycleOwner(), new Observer<List<MainInfo>>() {
            @Override
            public void onChanged(List<MainInfo> mainInfos) {
                Log.e("执行","xxxx11111");
                mainAdapter.setList(mainInfos);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}