package dev.codescreen.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.codescreen.exception.CustomException;
import dev.codescreen.pojo.AuthorizationRequest;
import dev.codescreen.pojo.AuthorizationResponse;
import dev.codescreen.service.AuthorizationService;
import jakarta.validation.Valid;


@RestController
@RequestMapping(path = "/authorization") 
public class AuthorizationController {
    
//  AuthorizationService authorizationservice= new AuthorizationService(); 
@Autowired
AuthorizationService authorizationservice;
   
        
   // Implementing a PUT method 
   @PutMapping("/") 
  
    public ResponseEntity<?> getAuthorization(@RequestParam("messageId") String messageId, @RequestBody @Valid AuthorizationRequest request) 
    { 
        
       try{
        AuthorizationResponse response=authorizationservice.Authorizetransaction(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
       }
       
       catch(CustomException e){
        return ResponseEntity.status(HttpStatusCode.valueOf(e.getError().getErrorCode())).body(e.getError().getErrorMessage());
      //   return null;
       }
        


    } 
}
