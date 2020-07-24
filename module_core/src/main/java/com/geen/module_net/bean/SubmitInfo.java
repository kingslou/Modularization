package com.geen.module_net.bean;

import java.io.Serializable;

/***
 * @author 86153
 * 提交 deatilJson
 */
public class SubmitInfo implements Serializable {

    private String id;
    private String versionNo;
    private String stockStorageCode;
    private String areaCode;
    private String warehouseId;
    private String row;
    private String goodsCode;
    private String goodsId;

    public SubmitInfo(String id, String versionNo, String stockStorageCode,String areaCode,String warehouseId,String row,String goodsCode,String goodsId) {
        this.id = id;
        this.versionNo = versionNo;
        this.stockStorageCode = stockStorageCode;
        this.areaCode = areaCode;
        this.warehouseId = warehouseId;
        this.row = row;
        this.goodsCode = goodsCode;
        this.goodsId = goodsId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getStockStorageCode() {
        return stockStorageCode;
    }

    public void setStockStorageCode(String stockStorageCode) {
        this.stockStorageCode = stockStorageCode;
    }
}
