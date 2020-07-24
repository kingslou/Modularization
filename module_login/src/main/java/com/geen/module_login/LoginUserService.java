package com.geen.module_login;

import android.text.TextUtils;

import com.geen.componentmanger.service.IUserInfoService;
import com.geen.module_net.bean.LoginInfo;

public class LoginUserService implements IUserInfoService {

    @Override
    public boolean isLogin() {
        if(getLoginInfo()!=null){
            return true;
        }
        return false;
    }

    @Override
    public LoginInfo getLoginInfo() {
        return UserManger.getInstance().getLoginInfo();
    }

    @Override
    public void cacheLoginInfo(String loginInfo) {
        UserManger.getInstance().cacheLoginInfo(loginInfo);
    }
}
