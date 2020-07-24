package com.geen.module_net.api;

public interface OnResponseListener<T> {
    void onSuccess(T t);
    void onFailed(String errorMsg);
}
