package com.geen.module_net.api;

import com.geen.module_net.bean.response.BaseResponse;
import com.geen.module_net.bean.response.ScanOutTrayResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/***
 * @author 86153
 * 出库api
 */
public interface RfoutApi {
    @FormUrlEncoded
    @POST("stockOut-api/lorealRfApiOut/checkBatchNo")
    Observable<ScanOutTrayResponse> getScanInfoByTrayNo(@Field("batchNo") String trayNo);


    @FormUrlEncoded
    @POST("stockOut-api/lorealRfApiOut/confirmOut")
    Observable<BaseResponse> confirmShelf(@Field("detailJson") String detailJson, @Field("userName") String useName, @Field("userId") String userId);

}
