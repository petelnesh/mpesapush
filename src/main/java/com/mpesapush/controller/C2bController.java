/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpesapush.controller;

import com.mpesapush.payload.RegisterUrl;
import com.mpesapush.payload.SimulateUrl;
import java.io.IOException;
import java.util.Base64;
import javax.validation.Valid;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author techsavanna
 */
@RestController
@RequestMapping("/c2b")
public class C2bController {
    
    private String appKey = "iCZxJfHicVi5eYJkeWOyvqtYJA4MGgiS";
    private String appSecret = "TZHshDySCybLWxPw";
      
    @PostMapping("/confirm/v1")
    private void getConfirmation(@RequestBody String pBody) throws ParseException, JSONException {
         JSONParser ps = new JSONParser();
        org.json.simple.JSONObject request = (org.json.simple.JSONObject) ps.parse(pBody);
           String trxType=request.get("TransactionType")==null?"Buy Goods":request.get("TransactionType")+"",
                trxId=request.get("TransID")+"",
                trxTime=request.get("TransTime")+"",
                trxAmount=request.get("TransAmount")+"",
                shortcode=request.get("BusinessShortCode")+"",
                refNumber=request.get("BillRefNumber")+"",
                invoice=request.get("InvoiceNumber")==null?"Buy Goods":request.get("InvoiceNumber")+"",
                balance=request.get("OrgAccountBalance")+"",
                OurTrxId=request.get("ThirdPartyTransID")+"",
                mobile=request.get("MSISDN")+"",
                firstName=request.get("FirstName")+"",
                middleName=request.get("MiddleName")+"",
                lastName=request.get("LastName")+"";
    }
    
     @PostMapping("/validate/v1")
    private void getValidate(@RequestBody String pBody) throws ParseException, JSONException {
         JSONParser ps = new JSONParser();
        org.json.simple.JSONObject request = (org.json.simple.JSONObject) ps.parse(pBody);
        
         String trxType=request.get("TransactionType")==null?"Buy Goods":request.get("TransactionType")+"",
                trxId=request.get("TransID")+"",
                trxTime=request.get("TransTime")+"",
                trxAmount=request.get("TransAmount")+"",
                shortcode=request.get("BusinessShortCode")+"",
                refNumber=request.get("BillRefNumber")+"",
                invoice=request.get("InvoiceNumber")==null?"Buy Goods":request.get("InvoiceNumber")+"",
                balance=request.get("OrgAccountBalance")+"",
                OurTrxId=request.get("ThirdPartyTransID")+"",
                mobile=request.get("MSISDN")+"",
                firstName=request.get("FirstName")+"",
                middleName=request.get("MiddleName")+"",
                lastName=request.get("LastName")+"";
    }
   public String C2BSimulation( String shortCode, String commandID, String amount, String MSISDN, String billRefNumber) throws IOException, JSONException {
        JSONArray jsonArray=new JSONArray();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("ShortCode", shortCode);
        jsonObject.put("CommandID", commandID);
        jsonObject.put("Amount", amount);
        jsonObject.put("Msisdn", MSISDN);
        jsonObject.put("BillRefNumber", billRefNumber);

        jsonArray.put(jsonObject);

        String requestJson=jsonArray.toString().replaceAll("[\\[\\]]","");
        System.out.println(requestJson);
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url("https://api.safaricom.co.ke/safaricom/c2b/v1/simulate")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer "+authenticate())
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        return response.body().toString();
    }
    public String registerURL(String shortCode, String responseType, String confirmationURL, String validationURL) throws IOException, JSONException {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ShortCode", shortCode);
        jsonObject.put("ResponseType", responseType);
        jsonObject.put("ConfirmationURL", confirmationURL);
        jsonObject.put("ValidationURL", validationURL);

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");
        System.out.println(requestJson);

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, requestJson);
        Request request = new Request.Builder()
                .url("https://api.safaricom.co.ke/mpesa/c2b/v1/registerurl")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer " + authenticate())
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        return response.body().string();
    } 
    public String authenticate() throws IOException, JSONException {
        String app_key = appKey/*"GvzjNnYgNJtwgwfLBkZh65VPwfuKvs0V"*/;
        String app_secret = appSecret;
        String appKeySecret = app_key + ":" + app_secret;
        byte[] bytes = appKeySecret.getBytes("ISO-8859-1");
        String encoded = Base64.getEncoder().encodeToString(bytes);

        OkHttpClient client = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url("https://api.safaricom.co.ke/oauth/v1/generate?grant_type=client_credentials")
                .get()
                .addHeader("authorization", "Basic " + encoded)
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        JSONObject jsonObject = new JSONObject(response.body().string());
        System.out.println(jsonObject.getString("access_token"));
        return jsonObject.getString("access_token");
    }
    @PostMapping("/mpesa/register")
    private void confirmApp(@Valid @RequestBody RegisterUrl cRequest) throws IOException, JSONException {
        registerURL(cRequest.getShortcode(), "Cancelled", "https://41.90.111.246:8181/stkpush/example/confirmc2b.php", "https://41.90.111.246:8181/stkpush/example/validatec2b.php");
 C2BSimulation("174379","CustomerPayBillOnline","2","254708529798","petelnesh");
       
//       getResponse();
System.out.println(cRequest);
    }
    
    @PostMapping("/mpesa/simulate")
    private void simulateApp(@Valid @RequestBody SimulateUrl cRequest) throws IOException, JSONException {
      //  registerURL(cRequest.getShortcode(), "Cancelled", "https://41.90.111.246:3000/mpesa/c2b/confirm/v1", "https://41.90.111.246:3000/mpesa/c2b/validate/v1");
 C2BSimulation(cRequest.getShortCode(),cRequest.getCommandID(),cRequest.getAmount(),cRequest.getMSISDN(),cRequest.getBillRefNumber());
       
//       getResponse();
System.out.println(cRequest);
    }
}
