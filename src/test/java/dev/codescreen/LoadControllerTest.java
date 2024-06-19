package dev.codescreen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.codescreen.exception.CustomException;
import dev.codescreen.exception.Error.ERROR;
import dev.codescreen.pojo.LoadRequest;
import dev.codescreen.pojo.LoadResponse;
import dev.codescreen.pojo.TransactionAmount;
import dev.codescreen.pojo.DebitorCredit.DebitOrCredit;
import dev.codescreen.pojo.ResponseCode.RESPONSE_CODE;
import dev.codescreen.service.LoadService;

@SpringBootTest
@AutoConfigureMockMvc
public class LoadControllerTest {

    @Autowired
    private MockMvc mockMvc;  // For simulating HTTP requests

    @MockBean
    private LoadService loadService;  // Mock the service

    @Autowired
    private ObjectMapper objectMapper;  // To convert objects to JSON

    @Test
    public void testLoadController() throws Exception {
        // Create mock response
        TransactionAmount transactionAmount = new TransactionAmount();
        transactionAmount.setAmount("100.00");
        transactionAmount.setCurrency("USD");
        transactionAmount.setDebitOrCredit(DebitOrCredit.CREDIT);
        LoadResponse mockResponse = new LoadResponse(
            "user123",
            "msg123",
            transactionAmount,
            RESPONSE_CODE.APPROVED
        );

        // Define behavior for the mocked service
        Mockito.when(loadService.Loadtransaction(Mockito.any(LoadRequest.class)))
               .thenReturn(mockResponse);

        // Create LoadRequest object
        TransactionAmount transactionAmount1 = new TransactionAmount();
        transactionAmount1.setAmount("100.00");
        transactionAmount1.setCurrency("USD");
        transactionAmount1.setDebitOrCredit(DebitOrCredit.CREDIT);
        LoadRequest request = new LoadRequest("user123", "msg123", transactionAmount);
        LoadRequest request1=new LoadRequest(null,null,null);
        request1.setMessageId("msg123");
        request1.setUserId("user123");
        request1.setTransactionAmount(transactionAmount);

        // Simulate HTTP PUT request to the controller
        mockMvc.perform(MockMvcRequestBuilders.put("/load/")
                .param("messageId", "msg123")  // Request parameter
                .content(objectMapper.writeValueAsString(request))  // Request body in JSON
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())  // Expecting HTTP 200
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId").value("user123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.messageId").value("msg123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.responseCode").value("APPROVED"));  // Validate response
    }

    @Test
    public void testLoadControllerCustomException() throws Exception {
        // Simulate the condition for throwing the exception
        Mockito.when(loadService.Loadtransaction(Mockito.any(LoadRequest.class)))
               .thenThrow(new CustomException(
                  ERROR.SERVER_ERROR
               ));

        // Create a LoadRequest object
        TransactionAmount transactionAmount = new TransactionAmount();
        transactionAmount.setAmount("100.00");
        transactionAmount.setCurrency("USD");
        transactionAmount.setDebitOrCredit(DebitOrCredit.CREDIT);

        LoadRequest request = new LoadRequest("user123", "msg123", transactionAmount);

        // Simulate HTTP PUT request to trigger the exception
        mockMvc.perform(MockMvcRequestBuilders.put("/load/")
                .param("messageId", "msg123")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string("Internal Server Error"));  // Check error message
    }

}

   