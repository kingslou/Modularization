package com.geen.module_net.bean.response;

import com.geen.module_net.bean.LoginInfo;

public class LoginResponse extends BaseResponse {

    private LoginInfo loginInfo;

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }
}
