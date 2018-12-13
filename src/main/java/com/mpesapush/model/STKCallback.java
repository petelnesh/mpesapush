/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpesapush.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author techsavanna
 */
@Entity
@Table(name = "stk_call_back")
public class STKCallback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String merchantID;
     private String checkoutID;
    private String mpesaReceiptNumber;
    private String resultCode;
    private String resultDesc;
  private String amount;
    public STKCallback() {
    }

    public STKCallback(String merchantID, String checkoutID, String mpesaReceiptNumber, String resultCode, String resultDesc, String amount) {
        this.merchantID = merchantID;
        this.checkoutID = checkoutID;
        this.mpesaReceiptNumber = mpesaReceiptNumber;
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMerchantID() {
        return merchantID;
    }

    public void setMerchantID(String merchantID) {
        this.merchantID = merchantID;
    }

    public String getCheckoutID() {
        return checkoutID;
    }

    public void setCheckoutID(String checkoutID) {
        this.checkoutID = checkoutID;
    }

    public String getMpesaReceiptNumber() {
        return mpesaReceiptNumber;
    }

    public void setMpesaReceiptNumber(String mpesaReceiptNumber) {
        this.mpesaReceiptNumber = mpesaReceiptNumber;
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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

   
   
}