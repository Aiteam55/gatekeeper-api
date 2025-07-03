package com.aiteam.gatekeeper.services;

import com.aiteam.gatekeeper.dtos.requests.ClientAppRequestDTO;
import com.aiteam.gatekeeper.repositories.ClientAppRepository;
import com.aiteam.gatekeeper.services.impl.ClientAppServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class ClientAppServiceTest {
    private IClientAppService clientAppService;
    private ClientAppRepository clientAppRepository;

    @BeforeEach
    public void setup() {
        clientAppService = new ClientAppServiceImpl(clientAppRepository);
    }

    @Test
    void testConnectionShouldReturnTrueWithValidCredentials() {
        ClientAppRequestDTO clientAppRequestDTO = new ClientAppRequestDTO("Client App", "localhost", "3306", "testdb", "root", "root");
        boolean result = clientAppService.testConnection(clientAppRequestDTO);
        assertTrue(result, "Expected valid DB connection to return true");
    }

    @Test
    void testConnectionShouldReturnFalseWithInvalidCredentials() {
        ClientAppRequestDTO clientAppRequestDTO = new ClientAppRequestDTO("Client App", "localhost", "3306", "wrongdb", "root", "root");
        boolean result = clientAppService.testConnection(clientAppRequestDTO);
        assertFalse(result, "Expected valid DB connection to return false");
    }
}
