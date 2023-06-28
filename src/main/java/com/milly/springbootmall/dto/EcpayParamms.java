package com.milly.springbootmall.dto;public class EcpayParamms {


    // TotalAmount : 必填 交易金額
    private String totalAmount;
    // TradeDesc : 必填 交易描述 店家ID
    private String tradeDesc;

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTradeDesc() {
        return tradeDesc;
    }

    public void setTradeDesc(String tradeDesc) {
        this.tradeDesc = tradeDesc;
    }
}
