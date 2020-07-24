package com.geen.module_net.bean;

import java.io.Serializable;

/***
 * @author 86153
 * 物料信息
 */
public class GoodsInfo implements Serializable {

    private String id;

    private String versionNo;
    //订单编号
    private String orderNo;
    //托盘No
    private String trayNo;
    //物料code
    private String goodsCode;
    private String goodsId;
    private String goodsName;
    private float goodsNumber;
    //备货位置
    private String bakStockLocation;
    //目标位置
    private String goodsTargetLocation;

    // 物所属的排位 位置
    private String goodsRow;

    private String areaCode;

    private String warehouseId;


    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setTrayNo(String trayNo) {
        this.trayNo = trayNo;
    }

    public String getTrayNo() {
        return trayNo;
    }


    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public float getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(float goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getBakStockLocation() {
        return bakStockLocation;
    }

    public void setBakStockLocation(String bakStockLocation) {
        this.bakStockLocation = bakStockLocation;
    }

    public String getGoodsTargetLocation() {
        return goodsTargetLocation;
    }

    public void setGoodsTargetLocation(String goodsTargetLocation) {
        this.goodsTargetLocation = goodsTargetLocation;
    }

    public void setGoodsRow(String goodsRow) {
        this.goodsRow = goodsRow;
    }

    public String getGoodsRow() {
        return goodsRow;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getVersionNo() {
        return versionNo;
    }
}
