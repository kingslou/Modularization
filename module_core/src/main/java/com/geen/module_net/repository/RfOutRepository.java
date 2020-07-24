package com.geen.module_net.repository;

import android.app.Activity;

import com.geen.module_net.api.OnResponseListener;
import com.geen.module_net.bean.response.BaseResponse;
import com.geen.module_net.bean.response.ScanOutTrayResponse;


/***
 * @author 86153
 *
 */
public interface RfOutRepository {
    void getScanInfoByTrayNo(Activity activity, String tranNo, OnResponseListener<ScanOutTrayResponse> responseListener);

    void confirmShelf(Activity activity, String detailJson, String userName, String userId, OnResponseListener<BaseResponse> responseListener);
}
