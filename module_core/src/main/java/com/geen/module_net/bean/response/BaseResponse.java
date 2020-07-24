package com.geen.module_net.bean.response;

import java.io.Serializable;

public class BaseResponse implements Serializable {

    private String code;
    private int result;
    private String msg;


    public boolean isSuccess(){
        if(result==1){
            return true;
        }
        return false;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
