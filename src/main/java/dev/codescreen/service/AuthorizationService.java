package dev.codescreen.service;

import dev.codescreen.entity.transactionentity;
import dev.codescreen.entity.userentity;
import dev.codescreen.exception.CustomException;
import dev.codescreen.exception.Error.ERROR;
import dev.codescreen.pojo.AuthorizationRequest;
import dev.codescreen.pojo.AuthorizationResponse;
import dev.codescreen.pojo.TransactionAmount;
import dev.codescreen.pojo.ResponseCode.RESPONSE_CODE;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.codescreen.repository.BankingRepository;
import dev.codescreen.repository.TransactionRepository; 

@Service
public class AuthorizationService {
    
    @Autowired 
    BankingRepository bankingRepository;

    @Autowired 
    TransactionRepository transactionRepository;
    
    FileWriter myWriter;
   
    public  AuthorizationResponse Authorizetransaction(AuthorizationRequest request) throws CustomException{


        try {
             myWriter = new FileWriter("src/main/resources/transaction_log.txt",true);
        } catch (IOException e) {
            throw new CustomException(ERROR.SERVER_ERROR);
        }

        userentity userdata=bankingRepository.findByUserId(request.getUserId());
        transactionentity transactiondata= new transactionentity();
        float balance=userdata.getBalance();
        float debitAmount=Float.parseFloat(request.getTransactionAmount().getAmount());
        AuthorizationResponse response= new AuthorizationResponse(null,null,null,null);
        TransactionAmount newbalance =new TransactionAmount();

        // saving every debit transaction
        transactiondata.setMessageId(request.getMessageId());
        transactiondata.setUserId(request.getUserId());
        transactiondata.setAmount(debitAmount);
        transactiondata.setDebitcredit(request.getTransactionAmount().getDebitOrCredit().toString());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        transactiondata.setDatetimevalue(dateFormat.format(date));



        if(debitAmount<balance){
            balance=balance-debitAmount;
            userdata.setBalance(balance);
            bankingRepository.save(userdata);
            response.setResponseCode(RESPONSE_CODE.APPROVED);
            transactiondata.setStatus(RESPONSE_CODE.APPROVED.toString());
            transactionRepository.save(transactiondata);
        }else{
            response.setResponseCode(RESPONSE_CODE.DECLINED);
            transactiondata.setStatus(RESPONSE_CODE.DECLINED.toString());
            transactionRepository.save(transactiondata);

            try {
                String transactiondetails=request.getUserId()+" | "+request.getMessageId()+" | "+debitAmount+" | "+request.getTransactionAmount().getDebitOrCredit().toString()+" | "+dateFormat.format(date)+"\n";
                myWriter.write(transactiondetails);
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
           
        }
        
        newbalance.setAmount(Float.toString(balance));
        newbalance.setCurrency(request.getTransactionAmount().getCurrency());
        newbalance.setDebitOrCredit(request.getTransactionAmount().getDebitOrCredit());
        response.setBalance(newbalance);
        response.setUserId(request.getUserId());
        response.setMessageId(request.getMessageId());
        
        return response;

    }
}
