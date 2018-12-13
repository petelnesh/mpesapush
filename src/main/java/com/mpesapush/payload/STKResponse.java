/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpesapush.payload;

/**
 *
 * @author techsavanna
 */

public class STKResponse {
    private Long id;
    private String merchantID;
    private String resultCode;
    private String resultDesc;
    private String mpesaReceiptNumber;
    private String checkoutID;
    private String phoneNumber;

    public STKResponse() {
    }

    public STKResponse(String merchantID, String resultCode, String resultDesc, String mpesaReceiptNumber, String checkoutID, String phoneNumber) {
        this.merchantID = merchantID;
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.mpesaReceiptNumber = mpesaReceiptNumber;
        this.checkoutID = checkoutID;
        this.phoneNumber = phoneNumber;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getMpesaReceiptNumber() {
        return mpesaReceiptNumber;
    }

    public void setMpesaReceiptNumber(String mpesaReceiptNumber) {
        this.mpesaReceiptNumber = mpesaReceiptNumber;
    }

    public String getCheckoutID() {
        return checkoutID;
    }

    public void setCheckoutID(String checkoutID) {
        this.checkoutID = checkoutID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
   
}
