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
public class SimulateUrl {
    private String shortCode;
     private String commandID;
     private String amount;
     private String MSISDN;
     private String billRefNumber;

    public SimulateUrl(String shortCode, String commandID, String amount, String MSISDN, String billRefNumber) {
        this.shortCode = shortCode;
        this.commandID = commandID;
        this.amount = amount;
        this.MSISDN = MSISDN;
        this.billRefNumber = billRefNumber;
    }

    public SimulateUrl() {
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getCommandID() {
        return commandID;
    }

    public void setCommandID(String commandID) {
        this.commandID = commandID;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMSISDN() {
        return MSISDN;
    }

    public void setMSISDN(String MSISDN) {
        this.MSISDN = MSISDN;
    }

    public String getBillRefNumber() {
        return billRefNumber;
    }

    public void setBillRefNumber(String billRefNumber) {
        this.billRefNumber = billRefNumber;
    }
     
     
}
