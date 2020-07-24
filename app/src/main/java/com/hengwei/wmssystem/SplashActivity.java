package com.hengwei.wmssystem;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.geen.commonlibary.RouteConfig;
import com.geen.commonlibary.base.BaseActivity;
import com.geen.commonlibary.callback.CommonCallBack;
import com.geen.commonlibary.mvp.BasePresenter;
import com.geen.componentmanger.ServiceFactory;
import com.hengwei.wmssystem.databinding.ActivitySplashBinding;

/***
 * @author 86153
 * 启动页
 */
public class SplashActivity extends BaseActivity<BasePresenter> {

    private ActivitySplashBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());
        mBinding.textAppVersion.setText("Version " + BuildConfig.VERSION_NAME);
        shortLogin();
    }

    private void shortLogin() {
        if (ServiceFactory.getInstance().getUserInfoService().isLogin()) {
            ServiceFactory.getInstance().getUserInfoService().refreshToken(SplashActivity.this, new CommonCallBack() {
                @Override
                public void onSuccess(Object object) {
                    ARouter.getInstance().build(RouteConfig.ROUTE_MAIN).navigation(SplashActivity.this, new NavCallback() {
                        @Override
                        public void onArrival(Postcard postcard) {
                            finish();
                        }
                    });
                }
                @Override
                public void onFail(String msg) {
                    ARouter.getInstance().build(RouteConfig.ROUTE_LOGIN)
                            .navigation(SplashActivity.this, new NavCallback() {
                                @Override
                                public void onArrival(Postcard postcard) {
                                    finish();
                                }
                            });
                }
            });
        } else {
            ARouter.getInstance().build(RouteConfig.ROUTE_LOGIN)
                    .navigation(this, new NavCallback() {
                        @Override
                        public void onArrival(Postcard postcard) {
                            finish();
                        }
                    });
        }
    }
}
