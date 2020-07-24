package com.geen.module_net.bean;

import java.io.Serializable;

/***
 * @author 86153
 * 扫描上架批次号信息
 */
public class ScanInTrayNoInfo implements Serializable {


    /**
     * id : 26
     * rcOrderDetailId : 20287
     * rcOrderId : 3375
     * rcOrderCode : R20061911100029
     * goodsId : 89873
     * goodsCode : L5273300U
     * shipFrom : C30
     * arrivalDate : 2020-05-04T16:00:00.000+0000
     * brandCode : 31
     * batchNo : 000000202004266985
     * storageCode : RBL18BB
     * stockStorageCode : null
     * status : 1
     * statusChangeTime : 2020-06-19T03:57:51.000+0000
     * agvStatus : null
     * agvMemo : null
     * warehouseId : 111
     */

    private String versionNo;
    private String id;
    private String rcOrderDetailId;
    private String rcOrderId;
    private String rcOrderCode;
    private String goodsId;
    private String goodsCode;
    private String shipFrom;
    private String arrivalDate;
    private String brandCode;
    private String batchNo;
    //目标库位
    private String storageCode;
    // 备货区库位
    private Object stockStorageCode;
    private int status;
    private String statusChangeTime;
    private String agvStatus;
    private String agvMemo;
    private String warehouseId;
    private int row;
    private String nameCn;
    private float expectNum;
    private ReceiveOrder receiveOrder;

    private String inputStr;

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRcOrderDetailId() {
        return rcOrderDetailId;
    }

    public void setRcOrderDetailId(String rcOrderDetailId) {
        this.rcOrderDetailId = rcOrderDetailId;
    }

    public String getRcOrderId() {
        return rcOrderId;
    }

    public void setRcOrderId(String rcOrderId) {
        this.rcOrderId = rcOrderId;
    }

    public String getRcOrderCode() {
        return rcOrderCode;
    }

    public void setRcOrderCode(String rcOrderCode) {
        this.rcOrderCode = rcOrderCode;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getShipFrom() {
        return shipFrom;
    }

    public void setShipFrom(String shipFrom) {
        this.shipFrom = shipFrom;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    public Object getStockStorageCode() {
        return stockStorageCode;
    }

    public void setStockStorageCode(Object stockStorageCode) {
        this.stockStorageCode = stockStorageCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusChangeTime() {
        return statusChangeTime;
    }

    public void setStatusChangeTime(String statusChangeTime) {
        this.statusChangeTime = statusChangeTime;
    }

    public String getAgvStatus() {
        return agvStatus;
    }

    public void setAgvStatus(String agvStatus) {
        this.agvStatus = agvStatus;
    }

    public String getAgvMemo() {
        return agvMemo;
    }

    public void setAgvMemo(String agvMemo) {
        this.agvMemo = agvMemo;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public void setExpectNum(float expectNum) {
        this.expectNum = expectNum;
    }

    public float getExpectNum() {
        return expectNum;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setReceiveOrder(ReceiveOrder receiveOrder) {
        this.receiveOrder = receiveOrder;
    }

    public ReceiveOrder getReceiveOrder() {
        return receiveOrder;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }

    public String getInputStr() {
        return inputStr;
    }
}
