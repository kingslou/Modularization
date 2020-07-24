package com.geen.module_net.repository;

import android.app.Activity;

import com.geen.commonlibary.config.UrlManger;
import com.geen.module_net.api.ApiClient;
import com.geen.module_net.api.LoginApi;
import com.geen.module_net.api.OnResponseListener;
import com.geen.module_net.bean.response.LoginResponse;


public class LoginRepositoryImp implements LoginRepository {

    private LoginApi loginApi;
    public LoginRepositoryImp() {
        loginApi = ApiClient.getInstance().create(LoginApi.class);
    }

    @Override
    public void loginApp(Activity activity, String userName, String userPwd, OnResponseListener<LoginResponse> responseListener) {
        ApiClient.getInstance().setSubscribe(activity, loginApi.loginApp(userName, userPwd), responseListener);
    }

    @Override
    public void loginByToken(Activity activity, OnResponseListener<LoginResponse> responseOnResponseListener) {
        ApiClient.getInstance().setSubscribe(activity, loginApi.loginAppByToken(), responseOnResponseListener);
    }
}
