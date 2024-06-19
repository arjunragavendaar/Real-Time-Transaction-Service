package dev.codescreen.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.codescreen.pojo.PingResponse;
import dev.codescreen.service.PingService;

@RestController
@RequestMapping(path = "/ping") 
public class PingController {

    @Autowired
    PingService pingservice;

    @GetMapping("/") 
  
    public Object getPing() 
    { 
        
       PingResponse Dateresponse= pingservice.pingmethod();

       return Dateresponse;
    } 
}
