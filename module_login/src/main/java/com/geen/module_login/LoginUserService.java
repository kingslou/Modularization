package com.geen.module_login;

import android.app.Activity;
import android.text.TextUtils;

import com.geen.commonlibary.callback.CommonCallBack;
import com.geen.componentmanger.service.IUserInfoService;
import com.geen.module_net.bean.LoginInfo;
import com.google.gson.Gson;

public class LoginUserService implements IUserInfoService {

    @Override
    public boolean isLogin() {
        return getLoginInfo()!=null;
    }

    @Override
    public void cacheLoginInfo(LoginInfo loginInfo) {
        UserManger.getInstance().cacheLoginInfo(new Gson().toJson(loginInfo));
    }

    @Override
    public LoginInfo getLoginInfo() {
        return UserManger.getInstance().getLoginInfo();
    }

    @Override
    public void clearLoginInfo() {
        UserManger.getInstance().cacheLoginInfo("");
    }

    @Override
    public String getToken() {
        return UserManger.getInstance().getToken();
    }

    @Override
    public void refreshToken(Activity activity, CommonCallBack callBack) {

    }
}
