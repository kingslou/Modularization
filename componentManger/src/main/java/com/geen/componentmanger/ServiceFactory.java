package com.geen.componentmanger;

import com.geen.componentmanger.service.IUserInfoService;

public class ServiceFactory {

    private IUserInfoService userInfoService;

    private ServiceFactory(){
    }

    public static final class Inner{
        private static ServiceFactory serviceFactory = new ServiceFactory();
    }

    public static ServiceFactory getInstance(){
        return Inner.serviceFactory;
    }


    public void setUserInfoService(IUserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    public IUserInfoService getUserInfoService() {
        if(userInfoService==null){
            userInfoService = new EmptyUserInfoService();
        }
        return userInfoService;
    }
}
