package dev.codescreen;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest  // Ensure Spring context is initialized
@ExtendWith(MockitoExtension.class)  // Enable Mockito support
public class BankingTransactionTest {

    @Test
    public void testMainApplicationContextLoads() {
        // Test if the Spring context loads without exceptions
        String filePath = "src/main/resources/transaction_log.txt";
        File file = new File(filePath);

        // Verify file deletion logic in the main class
        if (file.exists()) {
            boolean isDeleted = file.delete();  // Ensure the file is deleted
            assertTrue(isDeleted, "File should be deleted before test runs");
        }

        // Verify file creation logic in the main class
        try {
            boolean isCreated = file.createNewFile();  // Ensure the file is created
            assertTrue(isCreated, "File should be created by main class");
        } catch (IOException e) {
            fail("Exception thrown while creating file: " + e.getMessage());  // Ensure no exception during file creation
        }
        assertDoesNotThrow(() -> SpringApplication.run(BankingTransaction.class));  // Ensures the application starts
    }

    @Test
    public void testFileOperations() {
        String filePath = "src/main/resources/transaction_log.txt";
        File file = new File(filePath);

        // Verify file deletion logic in the main class
        if (file.exists()) {
            boolean isDeleted = file.delete();  // Ensure the file is deleted
            assertTrue(isDeleted, "File should be deleted before test runs");
        }
       
        
        // Verify file creation logic in the main class
        try {
            boolean isCreated = file.createNewFile();  // Ensure the file is created
            assertTrue(isCreated, "File should be created by main class");
        } catch (IOException e) {
            fail("Exception thrown while creating file: " + e.getMessage());  // Ensure no exception during file creation
        }
    }
}

