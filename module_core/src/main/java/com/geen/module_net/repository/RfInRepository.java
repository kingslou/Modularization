package com.geen.module_net.repository;

import android.app.Activity;

import com.geen.module_net.api.OnResponseListener;
import com.geen.module_net.bean.response.BaseResponse;
import com.geen.module_net.bean.response.ScanInTrayResponse;
import com.geen.module_net.bean.response.ScanStockResponse;

/***
 * @author 86153
 * 扫描之类的api请求
 */
public interface RfInRepository {

    void getScanInfoByTrayNo(Activity activity, String tranNo, OnResponseListener<ScanInTrayResponse> responseListener);

    void getScanInfoByStockNo(Activity activity, String scanNo, String wareHouseId, OnResponseListener<ScanStockResponse> responseListener);

    void confirmShelf(Activity activity, String detailJson, String userName, String userId, OnResponseListener<BaseResponse> responseListener);
}
