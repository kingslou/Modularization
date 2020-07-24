package com.geen.componentmanger.helper;

import android.app.Activity;

import com.blankj.utilcode.util.LogUtils;
import com.geen.commonlibary.BaseApplication;
import com.geen.commonlibary.config.AppConstans;
import com.geen.commonlibary.utils.ACache;
import com.geen.commonlibary.utils.ConfigUtil;
import com.geen.commonlibary.utils.TimeUtils;
import com.geen.commonlibary.utils.ToastUtil;
import com.geen.componentmanger.ServiceFactory;
import com.geen.module_net.api.OnResponseListener;
import com.geen.module_net.bean.LoginInfo;
import com.geen.module_net.bean.response.LoginResponse;
import com.geen.module_net.repository.LoginRepository;
import com.geen.module_net.repository.LoginRepositoryImp;
import com.google.gson.Gson;

import java.io.File;
import java.util.Date;

/***
 * @author 86153
 */
public class AppHelper {

    private LoginRepository loginRepository;

    private File logDir;

    private static class Holder {
        private static AppHelper instance = new AppHelper();
    }

    public interface RefreshTokenCallBack {
        void needLogin(boolean need);
    }

    public static AppHelper getAppHelper() {
        return Holder.instance;
    }

    public void resetAppToken() {
        clearLoginInfo();
        ConfigUtil.saveString(AppConstans.TOKEN, "");
    }

    public void cacheLoginInfo(LoginInfo loginInfo) {
        ServiceFactory.getInstance().getUserInfoService().cacheLoginInfo(new Gson().toJson(loginInfo));
    }

    public LoginInfo getLoginInfo() {

        return ServiceFactory.getInstance().getUserInfoService().getLoginInfo();
    }

    public void clearLoginInfo() {
        ACache.get(BaseApplication.getInstance()).put(AppConstans.LOGININFO, "");
    }

    public void refreshToken(Activity activity, final RefreshTokenCallBack callBack) {
        if (loginRepository == null) {
            loginRepository = new LoginRepositoryImp();
        }
        final String saveTime = TimeUtils.format(new Date(), TimeUtils.PATTERN_DATETIME_DATE);
        loginRepository.loginByToken(activity, new OnResponseListener<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse loginResponse) {
                try {
                    ConfigUtil.saveString(AppConstans.CHECK_TOKEN, saveTime);
                    LogUtils.d("请求---" + "成功  " + saveTime);
                    LoginInfo loginInfo = loginResponse.getLoginInfo();
                    if (loginInfo != null) {
                        cacheLoginInfo(loginInfo);
                        ConfigUtil.saveString(AppConstans.TOKEN, loginInfo.getToken());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    callBack.needLogin(false);
                }
            }

            @Override
            public void onFailed(String e) {
                //token 失效或者已经过期
                ToastUtil.showTips(e);
                ConfigUtil.saveString(AppConstans.TOKEN, "");
                clearLoginInfo();
                callBack.needLogin(true);
            }
        });
    }

}