package com.aiteam.gatekeeper.services;

import com.aiteam.gatekeeper.dtos.requests.ClientAppRequestDTO;
import com.aiteam.gatekeeper.dtos.responses.ClientAppResponseDTO;
import com.aiteam.gatekeeper.entities.ClientApp;
import com.aiteam.gatekeeper.repositories.ClientAppRepository;
import com.aiteam.gatekeeper.services.impl.ClientAppServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ClientAppServiceTest {
    @Mock
    private ClientAppRepository clientAppRepository;

    @InjectMocks
    private ClientAppServiceImpl clientAppService;

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

    @Test
    void shouldRegisterClientAppSuccessfully() {
        // Given
        ClientApp clientApp = new ClientApp();
        clientApp.setName("ClientTest");
        clientApp.setDbHost("localhost");
        clientApp.setDbPort("3306");
        clientApp.setDbName("testdb");
        clientApp.setDbUsername("root");
        clientApp.setDbPassword("secret");

        ClientAppRequestDTO dto = new ClientAppRequestDTO(
                "ClientTest", "localhost", "3306", "testdb", "root", "secret"
        );

        when(clientAppRepository.save(any(ClientApp.class))).thenReturn(clientApp);

        // When
        ClientAppResponseDTO result = clientAppService.registerClientApp(dto);

        // Then
        assertEquals("ClientTest", result.name());
        verify(clientAppRepository).save(any(ClientApp.class));
    }
}
