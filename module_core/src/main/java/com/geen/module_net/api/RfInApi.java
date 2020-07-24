package com.geen.module_net.api;
import com.geen.module_net.bean.response.BaseResponse;
import com.geen.module_net.bean.response.ScanInTrayResponse;
import com.geen.module_net.bean.response.ScanStockResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/***
 * @author 86153
 */
public interface RfInApi {

    @FormUrlEncoded
    @POST("stockIn-api/lorealRfApiIn/checkBatchNo")
    Observable<ScanInTrayResponse> getScanInfoByTrayNo(@Field("batchNo") String trayNo);

    @FormUrlEncoded
    @POST("stockIn-api/lorealRfApiIn/checkStorageCode")
    Observable<ScanStockResponse> getScanInfoByStockNo(@Field("storageCode") String code, @Field("warehouseId") String wareHouseId);


    @FormUrlEncoded
    @POST("stockIn-api/lorealRfApiIn/confirmShelf")
    Observable<BaseResponse> confirmShelf(@Field("detailJson") String detailJson, @Field("userName") String useName, @Field("userId") String userId);

}
