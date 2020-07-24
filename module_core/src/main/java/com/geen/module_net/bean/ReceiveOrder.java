package com.geen.module_net.bean;
import android.text.TextUtils;

import java.io.Serializable;

/***
 * @author 86153
 * 上架订单描述
 */
public class ReceiveOrder implements Serializable {

    private String totalBatchNoNum;

    private String putawayNum;


    public String getTotalBatchNoNum() {
        if(TextUtils.isEmpty(totalBatchNoNum)){
            return "0";
        }
        return totalBatchNoNum;
    }

    public void setTotalBatchNoNum(String totalBatchNoNum) {
        this.totalBatchNoNum = totalBatchNoNum;
    }

    public String getPutawayNum() {
        if(TextUtils.isEmpty(putawayNum)){
            return "0";
        }
        return putawayNum;
    }

    public void setPutawayNum(String putawayNum) {
        this.putawayNum = putawayNum;
    }
}
