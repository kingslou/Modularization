package com.geen.module_net.bean;

import java.io.Serializable;

/***
 * @author 86153
 * 扫描 出库 批次号信息
 */
public class ScanOutTrayNoInfo implements Serializable {


    /**
     * id : 22
     * spOrderDetailId : 11217
     * spOrderId : 4410
     * spOrderCode : S20062211100014
     * goodsId : 89868
     * goodsCode : Dior003
     * shipTo : WHLOREAL
     * orderDate : 2020-07-15T16:00:00.000+0000
     * brandCode : null
     * storageCode : kuwei002
     * stockStorageCode : beihuo5
     * containerNo : null
     * expiryDate : 2020-06-17T16:00:00.000+0000
     * status : 4
     * agvStatus : null
     * agvMemo : null
     * row : 1
     */

    private String versionNo;
    private String id;
    private String spOrderDetailId;
    private String spOrderId;
    private String spOrderCode;
    private String goodsId;
    private String goodsCode;
    private String shipTo;
    private String orderDate;
    private String brandCode;
    private String storageCode;
    private String stockStorageCode;
    private String containerNo;
    private String expiryDate;
    private int status;
    private String agvStatus;
    private String agvMemo;
    private String row;
    private String nameCn;
    private float expectNum;
    private String areaCode;
    private String warehouseId;
    private String inputStr;

    private ShippingOrder shippingOrder;

    public void setShippingOrder(ShippingOrder shippingOrder) {
        this.shippingOrder = shippingOrder;
    }

    public ShippingOrder getShippingOrder() {
        return shippingOrder;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setExpectNum(float expectNum) {
        this.expectNum = expectNum;
    }

    public float getExpectNum() {
        return expectNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpOrderDetailId() {
        return spOrderDetailId;
    }

    public void setSpOrderDetailId(String spOrderDetailId) {
        this.spOrderDetailId = spOrderDetailId;
    }

    public String getSpOrderId() {
        return spOrderId;
    }

    public void setSpOrderId(String spOrderId) {
        this.spOrderId = spOrderId;
    }

    public String getSpOrderCode() {
        return spOrderCode;
    }

    public void setSpOrderCode(String spOrderCode) {
        this.spOrderCode = spOrderCode;
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

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getStorageCode() {
        return storageCode;
    }

    public void setStorageCode(String storageCode) {
        this.storageCode = storageCode;
    }

    public String getStockStorageCode() {
        return stockStorageCode;
    }

    public void setStockStorageCode(String stockStorageCode) {
        this.stockStorageCode = stockStorageCode;
    }

    public String getContainerNo() {
        return containerNo;
    }

    public void setContainerNo(String containerNo) {
        this.containerNo = containerNo;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }

    public String getInputStr() {
        return inputStr;
    }
}
