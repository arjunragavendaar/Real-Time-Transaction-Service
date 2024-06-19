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
import dev.codescreen.pojo.LoadRequest;
import dev.codescreen.pojo.LoadResponse;
import dev.codescreen.service.LoadService;

@RestController
@RequestMapping(path = "/load") 
public class LoadController {
    
    @Autowired
    LoadService loadService;

    @PutMapping("/") 
  
    public ResponseEntity<?> getLoad(@RequestParam("messageId") String messageId, @RequestBody LoadRequest request) 
    { 
        
       try{
        LoadResponse response=loadService.Loadtransaction(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
       }
       
       catch(CustomException e){
        return ResponseEntity.status(HttpStatusCode.valueOf(e.getError().getErrorCode())).body(e.getError().getErrorMessage());
        // return null;
       }
        


    } 
    
}
