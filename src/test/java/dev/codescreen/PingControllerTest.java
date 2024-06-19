package dev.codescreen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;

import dev.codescreen.pojo.PingResponse;
import dev.codescreen.service.PingService;

@SpringBootTest
@AutoConfigureMockMvc
public class PingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PingService pingService;  // Mock the service

    @Test
    public void testGetPing() throws Exception {
        // Define the expected response
        PingResponse expectedResponse = new PingResponse();
        expectedResponse.setServerDateTime("2023/12/01 12:00:00");

        // Define behavior for the mocked service
        Mockito.when(pingService.pingmethod()).thenReturn(expectedResponse);

        // Simulate the GET request to the controller
        mockMvc.perform(MockMvcRequestBuilders.get("/ping/"))  // The endpoint being tested
                .andExpect(MockMvcResultMatchers.status().isOk())  // Expect HTTP 200
                .andExpect(MockMvcResultMatchers.jsonPath("$.serverDateTime").value("2023/12/01 12:00:00"));  // Check expected output
    }
}

