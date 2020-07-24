package com.geen.componentmanger;

import android.app.Activity;

import com.geen.commonlibary.callback.CommonCallBack;
import com.geen.componentmanger.service.IUserInfoService;
import com.geen.module_net.bean.LoginInfo;

public class EmptyUserInfoService implements IUserInfoService {

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public void cacheLoginInfo(LoginInfo loginInfo) {

    }

    @Override
    public LoginInfo getLoginInfo() {
        return null;
    }

    @Override
    public void clearLoginInfo() {

    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public void refreshToken(Activity activity, CommonCallBack callBack) {

    }
}
