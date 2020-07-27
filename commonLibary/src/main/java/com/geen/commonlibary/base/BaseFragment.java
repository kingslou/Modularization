package com.geen.commonlibary.base;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.geen.commonlibary.eventbus.Event;
import com.geen.commonlibary.eventbus.EventBusUtil;
import com.geen.commonlibary.mvp.BasePresenter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public abstract class BaseFragment<T extends BasePresenter> extends Fragment {

    public T mPresenter;

    public FragmentActivity mActivity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBusUtil.register(this);
        }
        mPresenter = initPresenter();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {

    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
    }


    protected T initPresenter(){
        return null;
    }


    public boolean isActivityFinish() {
        if (getActivity() == null || getActivity().isFinishing()) {
            return true;
        }
        return false;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (FragmentActivity) context;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        if(mActivity==null){
            mActivity = (FragmentActivity) activity;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBusUtil.unregister(this);
        }
    }
}
