package com.geen.module_login;

import android.text.TextUtils;

import com.geen.commonlibary.BaseApplication;
import com.geen.commonlibary.config.AppConstans;
import com.geen.commonlibary.utils.ACache;
import com.geen.module_net.bean.LoginInfo;
import com.google.gson.Gson;

public class UserManger {

    private static class Holder {
        private static UserManger instance = new UserManger();
    }

    public static UserManger getInstance() {
        return Holder.instance;
    }

    public void cacheLoginInfo(String loginInfo) {
        ACache.get(BaseApplication.getInstance()).put(AppConstans.LOGININFO, loginInfo);
    }

    public String getToken() {
        if (getLoginInfo() != null) {
            return getLoginInfo().getToken();
        }
        return "";
    }

    public LoginInfo getLoginInfo() {
        String loginInfo = ACache.get(BaseApplication.getInstance()).getAsString(AppConstans.LOGININFO);
        if (!TextUtils.isEmpty(loginInfo)) {
            return new Gson().fromJson(loginInfo, LoginInfo.class);
        }
        return null;
    }

}
