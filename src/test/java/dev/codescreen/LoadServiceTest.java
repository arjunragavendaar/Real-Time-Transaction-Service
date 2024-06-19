package dev.codescreen;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.codescreen.pojo.LoadRequest;
import dev.codescreen.pojo.LoadResponse;
import dev.codescreen.pojo.TransactionAmount;
import dev.codescreen.entity.userentity;
import dev.codescreen.pojo.DebitorCredit.DebitOrCredit;
import dev.codescreen.pojo.ResponseCode.RESPONSE_CODE;
import dev.codescreen.service.LoadService;
import dev.codescreen.repository.BankingRepository;
import dev.codescreen.repository.TransactionRepository;

@ExtendWith(MockitoExtension.class)  // Enables Mockito support
public class LoadServiceTest {

    @InjectMocks
    private LoadService loadService;  // Inject the LoadService being tested

    @Mock
    private BankingRepository bankingRepository;  // Mock the repository

    @Mock
    private TransactionRepository transactionRepository;  // Mock the repository

    @Test
    public void testLoadTransaction_Approved() throws Exception {
        // Mock user data with initial balance
        userentity mockUser = new userentity();
        mockUser.setBalance(200.00f);
        mockUser.setUserId("user123");

        // Define behavior for mocked repositories
        Mockito.when(bankingRepository.findByUserId("user123")).thenReturn(mockUser);

        TransactionAmount transactionAmount = new TransactionAmount();
        transactionAmount.setAmount("100.00");
        transactionAmount.setCurrency("USD");
        transactionAmount.setDebitOrCredit(DebitOrCredit.CREDIT);

        LoadRequest request = new LoadRequest("user123", "msg123", transactionAmount);

        // Test the service method
        LoadResponse response = loadService.Loadtransaction(request);

        // Assertions to ensure proper behavior
        assert response.getResponseCode() == RESPONSE_CODE.APPROVED;  // Should be approved
        assert response.getUserId().equals("user123");  // Correct user ID
        assert response.getMessageId().equals("msg123");  // Correct message ID
    }
}

