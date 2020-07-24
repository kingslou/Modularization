package com.geen.module_net.repository;

import android.app.Activity;

import com.geen.commonlibary.config.UrlManger;
import com.geen.module_net.api.ApiClient;
import com.geen.module_net.api.OnResponseListener;
import com.geen.module_net.api.RfoutApi;
import com.geen.module_net.bean.response.BaseResponse;
import com.geen.module_net.bean.response.ScanOutTrayResponse;


/***
 * @author 86153
 */
public class RfOutRepositoryImp implements RfOutRepository {

    private ApiClient apiClient;
    private RfoutApi rfoutApi;

    public RfOutRepositoryImp() {
        apiClient = new ApiClient(UrlManger.getApiUrl());
        rfoutApi = apiClient.create(RfoutApi.class);
    }


    @Override
    public void getScanInfoByTrayNo(Activity activity, String tranNo, OnResponseListener<ScanOutTrayResponse> responseListener) {
        apiClient.setSubscribe(activity, rfoutApi.getScanInfoByTrayNo(tranNo), responseListener);
    }

    @Override
    public void confirmShelf(Activity activity, String detailJson, String userName,String userId, OnResponseListener<BaseResponse> responseListener) {
        apiClient.setSubscribe(activity, rfoutApi.confirmShelf(detailJson, userName,userId), responseListener);
    }
}
