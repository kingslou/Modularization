package com.geen.module_net.bean.response;

import com.geen.module_net.bean.ScanOutTrayNoInfo;
import com.google.gson.annotations.SerializedName;

/***
 * @author 86153
 * 出库
 */
public class ScanOutTrayResponse extends BaseResponse {

    @SerializedName("data")
    private ScanOutTrayNoInfo scanTrayNoInfo;

    public void setScanTrayNoInfo(ScanOutTrayNoInfo scanTrayNoInfo) {
        this.scanTrayNoInfo = scanTrayNoInfo;
    }

    public ScanOutTrayNoInfo getScanTrayNoInfo() {
        return scanTrayNoInfo;
    }
}
