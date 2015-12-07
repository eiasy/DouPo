package com.huayi.doupo.base.util.sdk.uc.model;

/**
 * 支付回调信息
 * <br>
 * <br>========================== 
 * <br>公司：优视科技-游戏中心 
 * <br>开发：liusl@ucweb.com 
 * <br>创建时间：2015年6月15日
 * <br>==========================
 */
public class PayCallback {
    private String orderId;
    private int gameId;
    private String accountId;
    private String creator;
    private int payWay;
    private Double amount;
    private String callbackInfo;
    private String orderStatus;
    private String failedDesc="";
    private String cpOrderId;
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    public String getCreator() {
        return creator;
    }
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public int getPayWay() {
        return payWay;
    }
    public void setPayWay(int payWay) {
        this.payWay = payWay;
    }

    public String getCallbackInfo() {
        return callbackInfo;
    }
    public void setCallbackInfo(String callbackInfo) {
        this.callbackInfo = callbackInfo;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getFailedDesc() {
        return failedDesc;
    }
    public void setFailedDesc(String failedDesc) {
        this.failedDesc = failedDesc;
    }
    public String getCpOrderId() {
        return cpOrderId;
    }
    public void setCpOrderId(String cpOrderId) {
        this.cpOrderId = cpOrderId;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
}
