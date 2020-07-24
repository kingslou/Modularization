package com.geen.module_net.bean;

import java.io.Serializable;

public class LoginInfo implements Serializable {

    private String id;
    private String staffNo;
    private String userName;
    private String adLoginName;
    private String loginName;
    private String userMemberId;
    private String token;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getUserName() {
        return userName+"";
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAdLoginName() {
        return adLoginName;
    }

    public void setAdLoginName(String adLoginName) {
        this.adLoginName = adLoginName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserMemberId() {
        return userMemberId;
    }

    public void setUserMemberId(String userMemberId) {
        this.userMemberId = userMemberId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
