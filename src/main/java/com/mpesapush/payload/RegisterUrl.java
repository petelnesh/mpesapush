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
public class RegisterUrl {
     private String  shortcode;

    public RegisterUrl() {
    }

    public RegisterUrl(String shortcode) {
        this.shortcode = shortcode;
    }

    public String getShortcode() {
        return shortcode;
    }

    public void setShortcode(String shortcode) {
        this.shortcode = shortcode;
    }

   
}
