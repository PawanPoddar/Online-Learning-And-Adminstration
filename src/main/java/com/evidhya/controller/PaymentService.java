package com.evidhya.controller;

import java.util.*;

import com.paypal.api.payments.*;
import com.paypal.base.rest.*;
 
public class PaymentService {
    private static final String CLIENT_ID = "Your_PayPal_Client_ID";
    private static final String CLIENT_SECRET = "Your_PayPal_Client_Secret";
    private static final String MODE = "sandbox";
 
    public String authorizePayment(OrderDetail orderDetail)        
            throws PayPalRESTException {       
 
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();
        List<Transaction> listTransaction = getTransactionInformation(orderDetail);
         
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction);
        requestPayment.setRedirectUrls(redirectUrls);
        requestPayment.setPayer(payer);
        requestPayment.setIntent("authorize");
 
        APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
 
        Payment approvedPayment = requestPayment.create(apiContext);
 
        return getApprovalLink(approvedPayment);
 
    }
     
    private Payer getPayerInformation() {
        ...
    }
     
    private RedirectUrls getRedirectURLs() {
        ...
    }
     
    private List<Transaction> getTransactionInformation(OrderDetail orderDetail) {
        ...
    }
     
    private String getApprovalLink(Payment approvedPayment) {
        ...
    }
}
