package com.geen.componentmanger;

import com.geen.componentmanger.service.IUserInfoService;
import com.geen.module_net.bean.LoginInfo;

public
class EmptyUserInfoService implements IUserInfoService {

    @Override
    public boolean isLogin() {
        return false;
    }

    @Override
    public LoginInfo getLoginInfo() {
        return null;
    }

    @Override
    public void cacheLoginInfo(String loginInfo) {

    }
}
