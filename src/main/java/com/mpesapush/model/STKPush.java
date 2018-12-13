/*
 * To change this license header; choose License Headers in Project Properties.
 * To change this template file; choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpesapush.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
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
@Table(name="stk_push_requests")
public class STKPush {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
    
        @Column(nullable = true)
           private String  businessShortCode;
           @Column(nullable = true)
             private String password;
             @Column(nullable = true)
            private String timestamp;
            @Column(nullable = true)
            private String  transactionType;
            @Column(nullable = true)
            private Long  amount;
            @Column(nullable = true)
            private Long  phoneNumber;
            @Column(nullable = true)
            private Long  partyA;
            @Column(nullable = true)
            private String  partyB; 
            @Column(nullable = true)
            private String  callBackURL;
            @Column(nullable = true)
            private String  queueTimeOutURL;
            @Column(nullable = true)
            private String  accountReference;
            @Column(nullable = true)
               private String  transactionDesc; 
               @Column(nullable = true)
              private String merchantID;
              @Column(nullable = true)
              private String checkoutID;
              
              @Column(nullable = true)
              private String  responsecode; 
              @Column(nullable = true)
              private String responsemsg;
              @Column(nullable = true)
             private String  resultCode;
              @Column(nullable = true)
             private String resultDesc;
              @Column(nullable = true)
              private String mpesatrxid;
              @Column(nullable = true)
              private String CheckoutRequestID;
              @Column(nullable = true)
              private String MpesaReceiptNumber; 
              @Column(nullable = true)
             private String  islocked; 
              

    public STKPush( ) {
        
    }
    
    public STKPush(String businessShortCode, String password, String timestamp, String transactionType, Long amount, Long phoneNumber, Long partyA, String partyB, String callBackURL, String queueTimeOutURL, String accountReference, String transactionDesc, String merchantID, String checkoutID, String responsecode, String responsemsg, String resultCode, String resultDesc, String mpesatrxid, String CheckoutRequestID, String MpesaReceiptNumber, String islocked) {
         this.businessShortCode = businessShortCode;
        this.password = password;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
        this.amount = amount;
        this.phoneNumber = phoneNumber;
        this.partyA = partyA;
        this.partyB = partyB;
        this.callBackURL = callBackURL;
        this.queueTimeOutURL = queueTimeOutURL;
        this.accountReference = accountReference;
        this.transactionDesc = transactionDesc;
        this.merchantID = merchantID;
        this.checkoutID = checkoutID;
        this.responsecode = responsecode;
        this.responsemsg = responsemsg;
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.mpesatrxid = mpesatrxid;
        this.CheckoutRequestID = CheckoutRequestID;
        this.MpesaReceiptNumber = MpesaReceiptNumber;
        this.islocked = islocked;
    }
             
             

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessShortCode() {
        return businessShortCode;
    }

    public void setBusinessShortCode(String businessShortCode) {
        this.businessShortCode = businessShortCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getPartyA() {
        return partyA;
    }

    public void setPartyA(Long partyA) {
        this.partyA = partyA;
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    public String getCallBackURL() {
        return callBackURL;
    }

    public void setCallBackURL(String callBackURL) {
        this.callBackURL = callBackURL;
    }

    public String getQueueTimeOutURL() {
        return queueTimeOutURL;
    }

    public void setQueueTimeOutURL(String queueTimeOutURL) {
        this.queueTimeOutURL = queueTimeOutURL;
    }

    public String getAccountReference() {
        return accountReference;
    }

    public void setAccountReference(String accountReference) {
        this.accountReference = accountReference;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
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

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    public String getResponsemsg() {
        return responsemsg;
    }

    public void setResponsemsg(String responsemsg) {
        this.responsemsg = responsemsg;
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

    public String getMpesatrxid() {
        return mpesatrxid;
    }

    public void setMpesatrxid(String mpesatrxid) {
        this.mpesatrxid = mpesatrxid;
    }

    public String getCheckoutRequestID() {
        return CheckoutRequestID;
    }

    public void setCheckoutRequestID(String CheckoutRequestID) {
        this.CheckoutRequestID = CheckoutRequestID;
    }

    public String getMpesaReceiptNumber() {
        return MpesaReceiptNumber;
    }

    public void setMpesaReceiptNumber(String MpesaReceiptNumber) {
        this.MpesaReceiptNumber = MpesaReceiptNumber;
    }

    public String getIslocked() {
        return islocked;
    }

    public void setIslocked(String islocked) {
        this.islocked = islocked;
    }
           
    
}
