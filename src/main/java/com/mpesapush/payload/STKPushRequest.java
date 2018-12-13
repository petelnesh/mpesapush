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
public class STKPushRequest {
    
    private Long  phone;
    private Long amount;
    private String accountReference;

    public String getAccountReference() {
        return accountReference;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }
               private String  transactionDesc; 

    public void setAccountReference(String accountReference) {
        this.accountReference = accountReference;
    }

    public void setTransactionDesc(String transactionDesc) {
        this.transactionDesc = transactionDesc;
    }
            

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
    
}
