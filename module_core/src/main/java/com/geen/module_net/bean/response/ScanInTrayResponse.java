package com.geen.module_net.bean.response;

import com.geen.module_net.bean.ScanInTrayNoInfo;
import com.google.gson.annotations.SerializedName;

/***
 * @author 86153
 * 上架
 */
public class ScanInTrayResponse extends BaseResponse {

    @SerializedName("data")
    private ScanInTrayNoInfo scanTrayNoInfo;

    public void setScanTrayNoInfo(ScanInTrayNoInfo scanTrayNoInfo) {
        this.scanTrayNoInfo = scanTrayNoInfo;
    }

    public ScanInTrayNoInfo getScanTrayNoInfo() {
        return scanTrayNoInfo;
    }
}
