package dev.codescreen.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.codescreen.entity.transactionentity;
import dev.codescreen.entity.userentity;
import dev.codescreen.exception.CustomException;
import dev.codescreen.pojo.LoadRequest;
import dev.codescreen.pojo.LoadResponse;
import dev.codescreen.pojo.TransactionAmount;
import dev.codescreen.pojo.ResponseCode.RESPONSE_CODE;
import dev.codescreen.repository.BankingRepository;
import dev.codescreen.repository.TransactionRepository;

@Service
public class LoadService {
    @Autowired 
    BankingRepository bankingRepository;

    @Autowired 
    TransactionRepository transactionRepository;
   
    public  LoadResponse Loadtransaction(LoadRequest request) throws CustomException{



        userentity userdata=bankingRepository.findByUserId(request.getUserId());
        transactionentity transactiondata= new transactionentity();
        float balance=userdata.getBalance();
        float creditAmount=Float.parseFloat(request.getTransactionAmount().getAmount());
        LoadResponse response= new LoadResponse(null,null,null,null);
        TransactionAmount newbalance =new TransactionAmount();

         // saving every credit transaction
         transactiondata.setMessageId(request.getMessageId());
         transactiondata.setUserId(request.getUserId());
         transactiondata.setAmount(creditAmount);
         transactiondata.setDebitcredit(request.getTransactionAmount().getDebitOrCredit().toString());
         DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
         Date date = new Date();
         transactiondata.setDatetimevalue(dateFormat.format(date));
         transactiondata.setStatus(RESPONSE_CODE.APPROVED.toString());
            
        
            balance=balance+creditAmount;
            userdata.setBalance(balance);
            bankingRepository.save(userdata);
            response.setResponseCode(RESPONSE_CODE.APPROVED);
            transactionRepository.save(transactiondata);
        
        
        newbalance.setAmount(Float.toString(balance));
        newbalance.setCurrency(request.getTransactionAmount().getCurrency());
        newbalance.setDebitOrCredit(request.getTransactionAmount().getDebitOrCredit());
        response.setBalance(newbalance);
        response.setUserId(request.getUserId());
        response.setMessageId(request.getMessageId());
        
        return response;

    }
}
