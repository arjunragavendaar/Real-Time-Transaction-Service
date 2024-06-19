package dev.codescreen;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;

import dev.codescreen.exception.CustomException;
import dev.codescreen.exception.Error.ERROR;
import dev.codescreen.pojo.AuthorizationRequest;
import dev.codescreen.pojo.AuthorizationResponse;
import dev.codescreen.pojo.TransactionAmount;
import dev.codescreen.pojo.DebitorCredit.DebitOrCredit;
import dev.codescreen.pojo.ResponseCode.RESPONSE_CODE;
import dev.codescreen.service.AuthorizationService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorizationControllerTest {

    @Autowired
    private MockMvc mockMvc;  

    @MockBean
    private AuthorizationService authorizationService;  

    @Autowired
    private ObjectMapper objectMapper;  

    @Test
    public void testGetAuthorization() throws Exception {
        
        AuthorizationResponse mockResponse = new AuthorizationResponse("user123", "msg123", null, RESPONSE_CODE.APPROVED);

        Mockito.when(authorizationService.Authorizetransaction(Mockito.any(AuthorizationRequest.class)))
               .thenReturn(mockResponse);

        TransactionAmount transactionAmount = new TransactionAmount();
        transactionAmount.setAmount("100.00");
        transactionAmount.setCurrency("USD");
        transactionAmount.setDebitOrCredit(DebitOrCredit.DEBIT);

        AuthorizationRequest request = new AuthorizationRequest("user123", "msg123", transactionAmount);
        AuthorizationRequest request1=new AuthorizationRequest(null,null,null);
        request1.setMessageId("msg123");
        request1.setUserId("user123");
        request1.setTransactionAmount(transactionAmount);
        System.out.println("hellio");
        // Perform the PUT request
        mockMvc.perform(MockMvcRequestBuilders.put("/authorization/")
                .param("messageId", "msg123")  
                .content(objectMapper.writeValueAsString(request)) 
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  
                .andExpect(MockMvcResultMatchers.jsonPath("$.messageId").value("msg123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value("user123"));
    }


    @Test
    public void testAuthorizationControllerCustomException() throws Exception {
        // Simulate the condition for throwing the exception
        Mockito.when(authorizationService.Authorizetransaction(Mockito.any(AuthorizationRequest.class)))
               .thenThrow(new CustomException(
                  ERROR.SERVER_ERROR
               ));

        // Create a LoadRequest object
        TransactionAmount transactionAmount = new TransactionAmount();
        transactionAmount.setAmount("100.00");
        transactionAmount.setCurrency("USD");
        transactionAmount.setDebitOrCredit(DebitOrCredit.DEBIT);

        AuthorizationRequest request = new AuthorizationRequest("user123", "msg123", transactionAmount);

        // Simulate HTTP PUT request to trigger the exception
        mockMvc.perform(MockMvcRequestBuilders.put("/authorization/")
                .param("messageId", "msg123")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Internal Server Error"));  // Check error message
    }

    
}
