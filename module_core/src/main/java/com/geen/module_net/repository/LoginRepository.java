package com.geen.module_net.repository;

import android.app.Activity;

import com.geen.module_net.api.OnResponseListener;
import com.geen.module_net.bean.response.LoginResponse;


public interface LoginRepository {

    void loginApp(Activity activity, String userName, String userPwd, OnResponseListener<LoginResponse> responseListener);

    void loginByToken(Activity activity, OnResponseListener<LoginResponse> responseOnResponseListener);
}
