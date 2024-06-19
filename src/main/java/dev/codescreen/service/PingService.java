package dev.codescreen.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

import dev.codescreen.pojo.PingResponse;


@Service
public class PingService {

    public PingResponse pingmethod(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        PingResponse pingresponse= new PingResponse();
        pingresponse.setServerDateTime(dateFormat.format(date));
        
        return pingresponse;

    }
    
}
