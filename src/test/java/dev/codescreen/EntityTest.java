package dev.codescreen;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import dev.codescreen.entity.transactionentity;
import dev.codescreen.entity.userentity;

public class EntityTest {

    @Test
    public void testTransactionEntity() {
        transactionentity transaction = new transactionentity();

        // Set values
        transaction.setUserId("user123");
        transaction.setMessageId("msg456");
        transaction.setAmount(100.0f);
        transaction.setDebitcredit("debit");
        transaction.setDatetimevalue("2023-04-29");
        transaction.setStatus("completed");
        transaction.setId(123);

        // Validate values
        assertEquals("user123", transaction.getUserId());
        assertEquals("msg456", transaction.getMessageId());
        assertEquals(100.0f, transaction.getAmount());
        assertEquals("debit", transaction.getDebitcredit());
        assertEquals("2023-04-29", transaction.getDatetimevalue());
        assertEquals("completed", transaction.getStatus());
        assertEquals(123, transaction.getId());

    }

    @Test
    public void testUserEntity() {
        userentity user = new userentity();

        // Set values
        user.setUserId("user123");
        user.setBalance(500.0f);
        user.setId(123);

        // Validate values
        assertEquals("user123", user.getUserId());
        assertEquals(500.0f, user.getBalance());
        assertEquals(123, user.getId());

    }
}

