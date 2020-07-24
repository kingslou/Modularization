package com.geen.module_login;

import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.geen.commonlibary.RouteConfig;
import com.geen.commonlibary.base.BaseActivity;
import com.geen.commonlibary.config.AppConstans;
import com.geen.commonlibary.utils.ConfigUtil;
import com.geen.commonlibary.utils.ProgressDialog;
import com.geen.commonlibary.utils.ScreenUtil;
import com.geen.commonlibary.utils.ToastUtil;
import com.geen.componentmanger.ServiceFactory;
import com.geen.module_login.databinding.LoginActivityLoginBinding;
import com.geen.module_net.api.OnResponseListener;
import com.geen.module_net.bean.response.LoginResponse;
import com.geen.module_net.repository.LoginRepository;
import com.geen.module_net.repository.LoginRepositoryImp;
import com.gyf.immersionbar.ImmersionBar;

/***
 * @author 86153
 * 闪屏页
 */
@Route(path = RouteConfig.ROUTE_LOGIN)
public class LoginActivity extends BaseActivity {

    @Autowired(name = RouteConfig.Params.ROUTE_PARAMS_LOGIN_TO)
    String routeLoginTo;

    private LoginActivityLoginBinding loginBinding;
    private LoginRepository loginRepository;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = LoginActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        ARouter.getInstance().inject(this);
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f)
                .init();
        intiView();
        loginBinding.statusView.getLayoutParams().height = ScreenUtil.getStatusBarHeight();
        if(TextUtils.isEmpty(routeLoginTo)){
            routeLoginTo = RouteConfig.ROUTE_MAIN;
        }
    }


    private void intiView() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        loginBinding.btnLogin.setOnClickListener(v -> {
            if (TextUtils.isEmpty(loginBinding.edAccount.getText())) {
                ToastUtil.showTips("用户名不能为空");
                return;
            }
            if (TextUtils.isEmpty(loginBinding.editPwd.getText())) {
                ToastUtil.showTips("密码不能为空");
                return;
            }
            loginBinding.btnLogin.setEnabled(false);
            doLogin();
        });

        if (ConfigUtil.getBoolean(AppConstans.CHECK_PWD)) {
            loginBinding.checkPwd.setChecked(true);
            String account = ConfigUtil.getString(AppConstans.ACCOUNT);
            String pwd = ConfigUtil.getString(AppConstans.PWD);
            if (!TextUtils.isEmpty(account)) {
                loginBinding.edAccount.setText(account);
            }
            if (!TextUtils.isEmpty(pwd)) {
                loginBinding.editPwd.setText(pwd);
            }
        }
        //如果本地token 不为空，并且是记录用户名密码,就直接跳转到主界面
        if(!TextUtils.isEmpty(ConfigUtil.getString(AppConstans.TOKEN)) &&ConfigUtil.getBoolean(AppConstans.CHECK_PWD)){
            ARouter.getInstance().build(routeLoginTo).navigation();
            finish();
        }
    }

    private void doLogin() {
        loginRepository = new LoginRepositoryImp();
        progressDialog.show();
        if (loginBinding.checkPwd.isChecked()) {
            ConfigUtil.saveString(AppConstans.ACCOUNT, loginBinding.edAccount.getText().toString());
            ConfigUtil.saveString(AppConstans.PWD, loginBinding.editPwd.getText().toString());
        }else{
            ConfigUtil.saveString(AppConstans.ACCOUNT, "");
            ConfigUtil.saveString(AppConstans.PWD, "");
        }
        loginRepository.loginApp(this,loginBinding.edAccount.getText().toString(), loginBinding.editPwd.getText().toString(), new OnResponseListener<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                progressDialog.dismiss();
                loginBinding.btnLogin.setEnabled(true);
                if(loginResponse.getLoginInfo()==null){
                    ToastUtil.showTips("返回用户信息为空，请重新登录");
                    return;
                }
                ConfigUtil.saveBoolean(AppConstans.CHECK_PWD, loginBinding.checkPwd.isChecked());
                ServiceFactory.getInstance().getUserInfoService().cacheLoginInfo(loginResponse.getLoginInfo());
                ARouter.getInstance().build(routeLoginTo).navigation(LoginActivity.this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        finish();
                    }
                });
            }

            @Override
            public void onFailed(String e) {
                loginBinding.btnLogin.setEnabled(true);
                progressDialog.dismiss();
                ToastUtil.showTips(e);

                ARouter.getInstance().build(routeLoginTo).navigation(LoginActivity.this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {
                        finish();
                    }
                });
            }
        });
    }
}
