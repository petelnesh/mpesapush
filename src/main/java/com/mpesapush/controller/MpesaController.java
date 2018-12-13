/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mpesapush.controller;

import com.mpesapush.model.STKCallback;
import com.mpesapush.model.STKPush;
import com.mpesapush.payload.RegisterUrl;
import com.mpesapush.payload.STKPushRequest;
import com.mpesapush.payload.STKResponse;
import com.mpesapush.repository.MpesaCallBack;
import com.mpesapush.repository.MpesaRepository;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ListIterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import org.springframework.web.bind.annotation.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import okhttp3.Request;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.parser.ParseException;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author techsavanna
 */
@RestController
@RequestMapping("/api")
public class MpesaController {

    @Autowired
    private MpesaRepository mRepository;
    @Autowired
    private MpesaCallBack mCallRepository;
    String nn;
    String nnn;

    private String responseType="Cancelled";
    private String confirmationURL="https://41.90.111.246:3000/Mpesa/confirm/v1";
    private String validationURL="https://41.90.111.246:3000/Mpesa/validate/v1";
    
    private String appKey = "iCZxJfHicVi5eYJkeWOyvqtYJA4MGgiS";
    private String appSecret = "TZHshDySCybLWxPw";
    private String businessNo = "715423";
    private String TransactionType = "CustomerPayBillOnline";
    private String callBackURL = "https://41.90.111.246:3000/mpesa/api/stkcallback/v1";
    private String queueTimeOutURL = "https://mpesa-requestbin.herokuapp.com/1i2t8yc1";
    private String accountReference = "Test";
    private String transactionDes = "Test";

    private String merchantID;
    private String resultCode;
    private String resultDesc;
    private String mpesaReceiptNumber;
    private String checkoutID;
    private String amount;
     HttpPost httpPost;
     HttpClient httpClient;
     private String completeUrl="http://192.168.1.243:8181/stkpush/example/confirm2.php";

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

    public static org.json.simple.JSONObject createCustomResponse(String desc, int code) {
        org.json.simple.JSONObject resp = new org.json.simple.JSONObject();
        resp.put("ResultCode", code);
        resp.put("ResultDesc", desc);
        return resp;
    }

    public static String getpassword(String bs, String webpass, String timestamp) throws IOException {
        String password = toBase64(bs + webpass + timestamp);

        return password;
    }

    public static String toBase64(String raw) {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(raw.getBytes(StandardCharsets.UTF_8));
    }

    public String STKPushSimulation(String businessShortCode, String password, String timestamp, String transactionType, Long amount, Long phoneNumber, Long partyA, String partyB, String callBackURL, String queueTimeOutURL, String accountReference, String transactionDesc) throws JSONException, IOException {

        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("BusinessShortCode", businessShortCode);
        jsonObject.put("Password", password);
        jsonObject.put("Timestamp", timestamp);
        jsonObject.put("TransactionType", transactionType);
        jsonObject.put("Amount", amount);
        //System.out.println(jsonObject.put("Amount", amount));
        jsonObject.put("PhoneNumber", phoneNumber);
        jsonObject.put("PartyA", partyA);
        jsonObject.put("PartyB", partyB);
        jsonObject.put("CallBackURL", callBackURL);
        jsonObject.put("AccountReference", accountReference);
        jsonObject.put("QueueTimeOutURL", queueTimeOutURL);
        jsonObject.put("TransactionDesc", transactionDesc);

        jsonArray.put(jsonObject);

        String requestJson = jsonArray.toString().replaceAll("[\\[\\]]", "");

        OkHttpClient client = new OkHttpClient();
        String url = "https://api.safaricom.co.ke/mpesa/stkpush/v1/processrequest";
        MediaType mediaType = MediaType.parse("application/json");
        okhttp3.RequestBody body = okhttp3.RequestBody.create(mediaType, requestJson);
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("authorization", "Bearer " + authenticate())
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
        String ii = response.body().string();
        JSONObject jsonmn = new JSONObject(ii);

        String MerchantRequestID = recurseKeys(jsonmn, "MerchantRequestID");
        String CheckoutRequestID = recurseKeys(jsonmn, "CheckoutRequestID");
        String ResponseCode = recurseKeys(jsonmn, "ResponseCode");
        String ResponseDescription = recurseKeys(jsonmn, "ResponseDescription");
        String CustomerMessage = recurseKeys(jsonmn, "CustomerMessage");
        // System.out.println(extract);

        // System.out.println(response.body().string());
        // String mac=response.body().get("MerchantRequestID");
        STKPush push = new STKPush(businessShortCode, password, timestamp, transactionType, amount, phoneNumber, partyA, partyB, callBackURL, queueTimeOutURL, accountReference, transactionDesc, MerchantRequestID, CheckoutRequestID, ResponseCode, null, null, null, null, CheckoutRequestID, null, null);
        mRepository.save(push);
System.out.println("to database:"+push);
        //call mfs endpoint
        
        
        return response.body().toString();

    }

    public String recurseKeys(JSONObject jObj, String findKey) throws JSONException {
        String finalValue = "";
        if (jObj == null) {
            return "";
        }

        Iterator<String> keyItr = jObj.keys();
        Map<String, String> map = new HashMap<>();

        while (keyItr.hasNext()) {
            String key = keyItr.next();
            map.put(key, jObj.getString(key));
        }

        for (Map.Entry<String, String> e : (map).entrySet()) {
            String key = e.getKey();
            if (key.equalsIgnoreCase(findKey)) {
                return jObj.getString(key);
            }

            // read value
            Object value = jObj.get(key);

            if (value instanceof JSONObject) {
                finalValue = recurseKeys((JSONObject) value, findKey);
            }
        }

        // key is not found
        return finalValue;
    }

    public void processResult(org.json.simple.JSONObject result) {
        try {
            String code = "" + result.get("ResultCode"),
                    desc = "" + result.get("ResultDesc"),
                    merchantId = "" + result.get("MerchantRequestID"),
                    checkoutId = "" + result.get("CheckoutRequestID"),
                    trxid = "" + result.get("MpesaReceiptNumber"),
                    phone = "" + result.get("PhoneNumber");
            String sql = "update stk_push_requests set resultCode='" + code + "',"
                    + "resultDesc='" + desc + "',mpesatrxid='" + trxid + "',islocked=true "
                    + "where merchantID='" + merchantId + "' and checkoutID='" + checkoutId + "'"
                    + " and islocked=false";
            if (result.get("ResultCode").equals("0")) {
//               DatabaseWorker.update(sql);
//                String sql2 = "select account,amount from stk_push_requests where merchantID='" + merchantId + "' and checkoutID='" + checkoutId + "'";
//               org.json.simple.JSONObject info = DatabaseWorker.getFirstRowAsObject(sql2);
//                String owner = "" + info.get("account");
//                int amount = Integer.parseInt("" + info.get("amount"));
//                logActivity(phone, owner, "Mpesa LNMO", trxid, amount, desc);
//                // Stalls.creditStallAccount(owner,amount,phone);
            }
        } catch (Exception ex) {
            //   Logger.log(ex);
        }
    }

//    public  void logActivity(String debitParty, String creditParty, String channel, String receipt, double amount, String reason) {
//        String sqlInsert = "insert into account_activity(debitparty,creditparty,channel,receiptno,amount) "
//                + "values('" + debitParty + "','" + creditParty + "','" + channel + "','" + receipt + "',"
//                + "'" + "" + "')";
//        //DatabaseWorker.update(sqlInsert);
//    }
//    private void saveToDB(String businessShortCode, String password, String timestamp, String transactionType, String amount, String phoneNumber, String partyA, String partyB, String callBackURL, String queueTimeOutURL, String accountReference, String transactionDesc,String MerchantRequestID,String CheckoutRequestID,String ResponseCode,String ResponseDescription,String CustomerMessage) {
//        //System.out.println(businessShortCode+"\n"+password+"\n"+timestamp+"\n"+transactionType+"\n"+ amount+"\n"+ phoneNumber+"\n"+ partyA+"\n"+ partyB+"\n"+ callBackURL+"\n"+queueTimeOutURL+"\n"+accountReference+"\n"+transactionDesc+"\n");
//
//        String sql = "insert into stk_push_requests (businessShortCode,password,timestamp,transactionType,amount,phoneNumber,partyA,partyB,callBackURL,queueTimeOutURL,accountReference,transactionDesc,merchantID,checkoutID,responsecode,responsemsg)values( '" + businessShortCode + "','" + password + "','" + timestamp + "','" + transactionType + "','" + amount + "','" + phoneNumber + "','" + partyA + "','" + partyB + "','" + callBackURL + "','" + queueTimeOutURL + "','" + accountReference + "','" + transactionDesc + "','" + MerchantRequestID + "','" + CheckoutRequestID + "','" + ResponseCode + "','" + ResponseDescription + "')";
//
//        System.out.println(sql);
//       // DatabaseWorker.executeUpdate(sql);
//        //System.out.println(y);
//    }
    public String getStdTimeStamp() {
        String date = "";
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR),
                mon = cal.get(Calendar.MONTH) + 1,
                day = cal.get(Calendar.DAY_OF_MONTH),
                hr = cal.get(Calendar.HOUR_OF_DAY),
                min = cal.get(Calendar.MINUTE),
                sec = cal.get(Calendar.SECOND);
        date = year + append(mon) + append(day) + append(hr) + append(min) + append(sec);
        return date;
    }

    private String append(int val) {
        if (val < 10) {
            return "0" + val;
        }
        return "" + val;
    }

  
    /**
     *
     *  * @return @throws IOException
     * @throws JSONException
     */
    @PostMapping("/stkcallback/v1")
    private void getResponse(@RequestBody String pBody) throws ParseException, JSONException {
System.out.println(pBody);

   httpClient = new DefaultHttpClient();
    httpPost = new HttpPost(completeUrl);
    httpPost.setHeader("Content-type", "application/json");
    try {
        StringEntity stringEntity = new StringEntity(pBody);
        httpPost.getRequestLine();
        httpPost.setEntity(stringEntity);

        httpClient.execute(httpPost);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
        JSONParser ps = new JSONParser();
        org.json.simple.JSONObject jsonRequest = (org.json.simple.JSONObject) ps.parse(pBody);

        org.json.simple.JSONObject body = (org.json.simple.JSONObject) jsonRequest.get("Body"),
                callback = (org.json.simple.JSONObject) body.get("stkCallback"),
                prettyResult = new org.json.simple.JSONObject();

        merchantID = (String) callback.get("MerchantRequestID");
        checkoutID = (String) callback.get("CheckoutRequestID");

        prettyResult.put("MerchantRequestID", merchantID);
        prettyResult.put("CheckoutRequestID", checkoutID);

        prettyResult.put("ResultDesc", callback.get("ResultDesc"));
        prettyResult.put("ResultCode", callback.get("ResultCode"));

        resultDesc = (String) callback.get("ResultDesc");
        resultCode = (String) callback.get("ResultCode").toString();

            org.json.simple.JSONObject params = (org.json.simple.JSONObject) callback.get("CallbackMetadata");
System.out.println(params);
            org.json.simple.JSONArray kvmap = (org.json.simple.JSONArray) params.get("Item");
            amount=kvmap.toString();
           mpesaReceiptNumber=kvmap.toString();
//System.out.println(kvmap);
          //  ListIterator iterator = kvmap.listIterator();
            

//            while (iterator.hasNext()) {
//                org.json.simple.JSONObject next = (org.json.simple.JSONObject) iterator.next();
//                 System.out.println(next);
//                prettyResult.put(next.get("Name"), next.get("Value"));
//                mpesaReceiptNumber = (String) next.get("Value");
//                //amount = (String) next.get("Value").toString();
//                System.out.println(mpesaReceiptNumber);
//            }
//            while (iterator.hasPrevious()) {
//                org.json.simple.JSONObject previous = (org.json.simple.JSONObject) iterator.previous();
//
//                // prettyResult.put(next.get("Name"), next.get("Value")); 
//                amount = (String) previous.get("Value");
//               // System.out.println(amount);
//            }

        

        STKCallback stkcallback = new STKCallback(merchantID, checkoutID, mpesaReceiptNumber, resultCode, resultDesc, amount);
        mCallRepository.save(stkcallback);

        // STKCallback response = new STKCallback(merchantID, resultCode, resultDesc, mpesaReceiptNumber, checkoutID, phoneNumber);
        //STKResponse response=new STKResponse(merchantID, resultCode, resultDesc, mpesaReceiptNumber, checkoutID, phoneNumber);
//        String MerchantID = sRequest.getMerchantID();
//        String resultCode= sRequest.getResultCode();
//        String resultDesc = sRequest.getResultDesc();
//        String mpesaReceiptNumber = sRequest.getMpesaReceiptNumber();
//        String checkoutID = sRequest.getCheckoutID();
//        String phoneNumber = sRequest.getPhoneNumber();
//        
//     
//    return stkcallback;
    }


    @PostMapping("/mpesa/push")
    private void startApp(@Valid @RequestBody STKPushRequest sRequest) throws IOException, JSONException {
        //  MpesaController m = new MpesaController("iCZxJfHicVi5eYJkeWOyvqtYJA4MGgiS", "TZHshDySCybLWxPw");
        authenticate();
        String timestamp = getStdTimeStamp();
        String pass = getpassword(businessNo, "0a46258e4fbcdb1b8f766f667bf9394992d2b8552842185816c4aac23956faee", timestamp);

        // System.out.println(sRequest.getAmount());
        STKPushSimulation(businessNo, pass, timestamp, TransactionType, sRequest.getAmount(), sRequest.getPhone(), sRequest.getPhone(), businessNo, callBackURL, queueTimeOutURL, sRequest.getAccountReference(), sRequest.getTransactionDesc());
        // m.registerURL("715423","Cancelled","http://41.90.111.246:3000/Mpesa/confirm/v1","http://41.90.111.246:3000/Mpesa/validate/v1");
//       getResponse();
    }

    
}
