package com.geen.componentmanger.service;

import android.app.Activity;

import com.geen.commonlibary.callback.CommonCallBack;
import com.geen.module_net.bean.LoginInfo;

public interface IUserInfoService {

    boolean isLogin();

    void cacheLoginInfo(LoginInfo loginInfo);

    LoginInfo getLoginInfo();

    void clearLoginInfo();

    void refreshToken(Activity activity, CommonCallBack callBack);

    String getToken();

}
