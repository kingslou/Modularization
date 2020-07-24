package com.geen.module_net.bean.response;
import com.geen.module_net.bean.ScanStockNoInfo;
import com.google.gson.annotations.SerializedName;

/***
 * @author 86153
 * 扫描库位返回信息
 */
public class ScanStockResponse extends BaseResponse {

    @SerializedName("data")
    private ScanStockNoInfo scanStockNoInfo;

    public void setScanStockNoInfo(ScanStockNoInfo scanStockNoInfo) {
        this.scanStockNoInfo = scanStockNoInfo;
    }

    public ScanStockNoInfo getScanStockNoInfo() {
        return scanStockNoInfo;
    }
}
