package dev.codescreen.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class AuthorizationRequest {

     @NotNull(message = "TransactionAmount is required.")
     @JsonProperty("transactionAmount")
    private TransactionAmount transactionAmount; 
  
    @NotBlank(message = "User ID is required.")
    @Pattern(regexp = "^[^\\/:*?\\\"<>!|]+$", message = "Not valid")
    @JsonProperty("userId")
    private String userId; 
    
    @Pattern(regexp = "^[^\\/:*?\\\"<>!|]+$", message = "Not valid")
    @NotBlank(message = "Message ID is required.")
    @JsonProperty("messageId")
    private String messageId; 

    public AuthorizationRequest( 
        String userId, String messageId, 
        TransactionAmount transactionAmount) 
    { 
  
        super(); 
  
        this.userId = userId; 
  
        this.messageId = messageId; 
  
        this.transactionAmount = transactionAmount; 
  
            
    } 
  
    public TransactionAmount getTransactionAmount() 
    { 
  
         return transactionAmount; 
    } 
  
    public void setTransactionAmount(TransactionAmount transactionAmount) 
    { 
  
         this.transactionAmount = transactionAmount; 
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
