package com.vktechnology.naagu.models;

import org.codehaus.jackson.annotate.JsonProperty;

public class Order {

	@JsonProperty("order-no")
    private int orderNo;
     
    @JsonProperty
    private String custmer;
     
    private String address;
     
    @JsonProperty("bill-amount")
    private String amount;
     
     
    public int getOrderNo() {
        return orderNo;
    }
    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }
     
    public String getCustmer() {
        return custmer;
    }
    public void setCustmer(String custmer) {
        this.custmer = custmer;
    }
     
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
     
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }
}
