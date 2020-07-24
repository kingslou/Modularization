package com.geen.commonlibary.base;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.geen.commonlibary.R;
import com.geen.commonlibary.eventbus.Event;
import com.geen.commonlibary.eventbus.EventBusUtil;
import com.geen.commonlibary.mvp.BasePresenter;
import com.geen.commonlibary.mvp.IView;
import com.geen.commonlibary.utils.AppManager;
import com.geen.commonlibary.utils.ProgressDialog;
import com.geen.commonlibary.utils.ToastUtil;
import com.gyf.immersionbar.ImmersionBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IView {

    public ProgressDialog progressDialog;
    public T mPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this)
                .fitsSystemWindows(true)
                .statusBarColor(R.color.white)
                .statusBarDarkFont(true, 0.2f)
                .init();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBusUtil.register(this);
        }
        AppManager.getAppManager().addActivity(this);
        mPresenter = initPresenter();
    }

    public void initToolbar(Toolbar toolbar, boolean withBack) {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("");
            if (withBack) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    @Override
    public void toast(String toast) {
        ToastUtil.showTips(toast);
    }

    @Override
    public void showProgress() {
        if(progressDialog!=null){
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(BaseActivity.this);
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    public void hideProgress() {
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }

    protected T initPresenter(){
        return null;
    }

    public void replaceFragment(@IdRes int containerViewId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, fragment)
                .commitAllowingStateLoss();
    }

    public void addFragment(@IdRes int containerViewId, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .add(containerViewId, fragment)
                .commitAllowingStateLoss();
    }


    public void showFragmentAndHideOldFragment(Fragment fragment, Fragment fragmentOld) {
        if (fragment == fragmentOld) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (null != fragmentOld) {
            transaction.hide(fragmentOld);
        }
        transaction.show(fragment);
        transaction.commitAllowingStateLoss();
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBusCome(Event event) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBusUtil.unregister(this);
        }
        if(mPresenter!=null){
            mPresenter.onDestroy();
        }
        AppManager.getAppManager().removeActivity(this);
    }
}
