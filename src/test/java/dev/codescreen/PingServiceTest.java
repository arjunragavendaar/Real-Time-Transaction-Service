package dev.codescreen;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.codescreen.pojo.PingResponse;
import dev.codescreen.service.PingService;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)  // Enable Mockito support
public class PingServiceTest {

    @Test
    public void testPingMethod() {
        PingService pingService = new PingService();  

        PingResponse response = pingService.pingmethod();
        
        assertNotNull(response.getServerDateTime(), "ServerDateTime should not be null");
        assertTrue(response.getServerDateTime().matches("\\d{4}/\\d{2}/\\d{2} \\d{2}:\\d{2}:\\d{2}"), 
                   "ServerDateTime should be in the correct format");
    }
}

