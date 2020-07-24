package com.geen.module_net.bean;
import android.text.TextUtils;

import java.io.Serializable;

/***
 * @author 86153
 * 出库订单描述
 */
public class ShippingOrder implements Serializable {

    private String confirmNum;
    private String putawayNumber;
    private String totalBatchNoNum;

    public void setConfirmNum(String confirmNum) {
        this.confirmNum = confirmNum;
    }

    public String getConfirmNum() {
        if(TextUtils.isEmpty(confirmNum)){
            return "0";
        }
        return confirmNum;
    }


    public String getTotalBatchNoNumber() {
        if(TextUtils.isEmpty(totalBatchNoNum)){
            return "0";
        }
        return totalBatchNoNum;
    }

    public void setTotalBatchNoNumber(String totalBatchNoNumber) {

        this.totalBatchNoNum = totalBatchNoNumber;
    }
}
