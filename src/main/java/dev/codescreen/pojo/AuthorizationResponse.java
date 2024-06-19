package dev.codescreen.pojo;

import dev.codescreen.pojo.ResponseCode.RESPONSE_CODE;

public class AuthorizationResponse {
    
    private TransactionAmount balance; 
  
    private String userId; 

    private String messageId; 

    private RESPONSE_CODE responsecode;


    public AuthorizationResponse(  String userId, String messageId, TransactionAmount balance,RESPONSE_CODE responsecode) 
    { 
  
        super(); 
  
        this.userId = userId; 
  
        this.messageId = messageId; 
  
        this.balance = balance; 

        this.responsecode=responsecode;
  
            
    } 


    public RESPONSE_CODE getResponseCode() 
    { 
  
         return responsecode; 
    } 
  
    public void setResponseCode(RESPONSE_CODE approved) 
    { 
  
         this.responsecode = approved; 
    } 
  
    public TransactionAmount getBalance() 
    { 
  
         return balance; 
    } 
  
    public void setBalance(TransactionAmount balance) 
    { 
  
         this.balance = balance; 
    } 

    public String getUserId() 
    { 
  
         return userId; 
    } 
  
    public void setUserId(String userId) 
    { 
  
         this.userId = userId; 
    } 

    public String getMessageId() 
    { 
  
         return messageId; 
    } 
  
    public void setMessageId(String messageId) 
    { 
  
         this.messageId = messageId; 
    } 

}
