package dev.codescreen;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import dev.codescreen.pojo.AuthorizationRequest;
import dev.codescreen.pojo.AuthorizationResponse;
import dev.codescreen.pojo.TransactionAmount;
import dev.codescreen.pojo.DebitorCredit.DebitOrCredit;
import dev.codescreen.entity.userentity;
import dev.codescreen.repository.BankingRepository;
import dev.codescreen.repository.TransactionRepository;
import dev.codescreen.service.AuthorizationService;
import dev.codescreen.pojo.ResponseCode.RESPONSE_CODE;


@ExtendWith(MockitoExtension.class)
public class AuthorizationServiceTest {

    @InjectMocks
    private AuthorizationService authorizationService;  // Inject the service being tested

    @Mock
    private BankingRepository bankingRepository;  // Mock the repository

    @Mock
    private TransactionRepository transactionRepository;  // Mock the repository

    @Test
    public void testAuthorizeTransaction_Approved() throws Exception {
        // Mock user data and expected balance
        userentity mockUser = new userentity();
        mockUser.setBalance(200.00f);
        mockUser.setUserId("user123");

        // Define behavior for the mocked repository
        Mockito.when(bankingRepository.findByUserId("user123")).thenReturn(mockUser);

        TransactionAmount transactionAmount = new TransactionAmount();
        transactionAmount.setAmount("100.00");
        transactionAmount.setCurrency("USD");
        transactionAmount.setDebitOrCredit(DebitOrCredit.DEBIT);

        AuthorizationRequest request = new AuthorizationRequest("user123", "msg123", transactionAmount);

        // Test the service method
        AuthorizationResponse response = authorizationService.Authorizetransaction(request);

        // Assertions to verify the expected outcomes
        assert response.getResponseCode() == RESPONSE_CODE.APPROVED;  // Should be approved
        assert response.getUserId().equals("user123");  // Correct user ID
        assert response.getMessageId().equals("msg123");  // Correct message ID
    }


    @Test
    public void testAuthorizeTransaction_Declined() throws Exception {
        // Mock user data and expected balance
        userentity mockUser = new userentity();
        mockUser.setBalance(200.00f);
        mockUser.setUserId("user123");

        // Define behavior for the mocked repository
        Mockito.when(bankingRepository.findByUserId("user123")).thenReturn(mockUser);

        TransactionAmount transactionAmount = new TransactionAmount();
        transactionAmount.setAmount("1000000.00");
        transactionAmount.setCurrency("USD");
        transactionAmount.setDebitOrCredit(DebitOrCredit.DEBIT);

        AuthorizationRequest request = new AuthorizationRequest("user123", "msg123", transactionAmount);

        // Test the service method
        AuthorizationResponse response = authorizationService.Authorizetransaction(request);

        // Assertions to verify the expected outcomes
        assert response.getResponseCode() == RESPONSE_CODE.DECLINED;  // Should be approved
        assert response.getUserId().equals("user123");  // Correct user ID
        assert response.getMessageId().equals("msg123");  // Correct message ID
    }
}

