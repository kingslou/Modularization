package com.geen.module_net.repository;

import android.app.Activity;

import com.geen.commonlibary.config.UrlManger;
import com.geen.module_net.api.ApiClient;
import com.geen.module_net.api.OnResponseListener;
import com.geen.module_net.api.RfInApi;
import com.geen.module_net.bean.response.BaseResponse;
import com.geen.module_net.bean.response.ScanInTrayResponse;
import com.geen.module_net.bean.response.ScanStockResponse;


/***
 * @author 86153
 */
public class RfInRepositoryImp implements RfInRepository {

    private RfInApi scanApi;
    private ApiClient apiClient;

    public RfInRepositoryImp() {
        apiClient = new ApiClient(UrlManger.getApiUrl());
        scanApi = apiClient.create(RfInApi.class);
    }


    @Override
    public void getScanInfoByTrayNo(Activity activity, String trayNo, OnResponseListener<ScanInTrayResponse> responseListener) {
        apiClient.setSubscribe(activity, scanApi.getScanInfoByTrayNo(trayNo), responseListener);
    }

    @Override
    public void getScanInfoByStockNo(Activity activity, String scanNo, String wareHouseId, OnResponseListener<ScanStockResponse> responseListener) {
        apiClient.setSubscribe(activity, scanApi.getScanInfoByStockNo(scanNo, wareHouseId), responseListener);
    }

    @Override
    public void confirmShelf(Activity activity, String detailJson, String userName,String userId, OnResponseListener<BaseResponse> responseListener) {
        apiClient.setSubscribe(activity,scanApi.confirmShelf(detailJson,userName,userId),responseListener);
    }
}
