package com.aiteam.gatekeeper.controllers;

import com.aiteam.gatekeeper.dtos.responses.ClientAppResponseDTO;
import com.aiteam.gatekeeper.services.IClientAppService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ClientAppControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IClientAppService clientAppService;

    @Test
    void testConnectionShouldReturnWrappedResponse() throws Exception {
        when(clientAppService.testConnection(any())).thenReturn(true);

        mockMvc.perform(post("/api/v1/clientapp/test-connection")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Client App\", \"host\": \"localhost\", \"port\": \"3306\", \"database\": \"testdb\", \"username\": \"root\", \"password\": \"root\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(200))
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.data").value(true));
    }

    @Test
    void registerAppShouldReturnWrappedResponse() throws Exception {
        when(clientAppService.registerClientApp(any())).thenReturn(
                new ClientAppResponseDTO("ClientTest", "localhost", "3306", "testdb", "root",1)
        );

        mockMvc.perform(post("/api/v1/clientapp/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"ClientTest\", \"host\": \"localhost\", \"port\": \"3306\", \"database\": \"testdb\", \"username\": \"root\", \"password\": \"root\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value(201))
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.data.name").value("ClientTest"))
                .andExpect(jsonPath("$.data.dbHost").value("localhost"))
                .andExpect(jsonPath("$.data.dbPort").value("3306"))
                .andExpect(jsonPath("$.data.dbName").value("testdb"))
                .andExpect(jsonPath("$.data.dbUsername").value("root"))
                .andExpect(jsonPath("$.data.userId").value(1));
    }
}
