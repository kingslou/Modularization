package com.geen.componentmanger.service;

import com.geen.module_net.bean.LoginInfo;

public interface IUserInfoService {

    boolean isLogin();

    void cacheLoginInfo(String loginInfo);

    LoginInfo getLoginInfo();


}
