package dev.codescreen;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import dev.codescreen.exception.CustomException;
import dev.codescreen.exception.Error;

public class CustomExceptionTest {

    @Test
    public void testCustomExceptionInitialization() {
        // Initialize CustomException with SERVER_ERROR
        CustomException customException = new CustomException(Error.ERROR.SERVER_ERROR);

        // Check if the correct error message is passed
        assertEquals("Internal Server Error", customException.getMessage(), 
            "Error message should be 'Internal Server Error'");

        // Verify the error object in the exception
        assertNotNull(customException.getError(), "Error object should not be null");
        assertEquals(Error.ERROR.SERVER_ERROR, customException.getError(), 
            "Error object should be SERVER_ERROR");

        // Check the error code within the enum
        assertEquals(500, customException.getError().getErrorCode(), 
            "Error code should be 500");
        assertEquals("Internal Server Error", customException.getError().getErrorMessage(), 
            "Error message within the enum should be 'Internal Server Error'");
    }

    @Test
    public void testExceptionIsThrowable() {
        // Test that the CustomException can be thrown and caught
        try {
            throw new CustomException(Error.ERROR.SERVER_ERROR);
        } catch (CustomException ex) {
            assertEquals("Internal Server Error", ex.getMessage(), 
                "Thrown exception should have the correct message");
        }
    }
}

