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
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(SpringExtension.class)
@Testcontainers
public class ClientAppServiceTest {
    @Mock
    private ClientAppRepository clientAppRepository;

    @InjectMocks
    private ClientAppServiceImpl clientAppService;

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("root")
            .withPassword("root");

    @Test
    void testConnectionShouldReturnTrueWithValidCredentials() {
        ClientAppRequestDTO clientAppRequestDTO = new ClientAppRequestDTO("Client App", mysql.getHost(), mysql.getFirstMappedPort().toString(), mysql.getDatabaseName(), mysql.getUsername(), mysql.getPassword(), 1);
        boolean result = clientAppService.testConnection(clientAppRequestDTO);
        assertTrue(result, "Expected valid DB connection to return true");
    }

    @Test
    void testConnectionShouldReturnFalseWithInvalidCredentials() {
        ClientAppRequestDTO clientAppRequestDTO = new ClientAppRequestDTO("Client App", mysql.getHost(), mysql.getFirstMappedPort().toString(), "invalid_db", mysql.getUsername(), mysql.getPassword(), 1);
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
                "ClientTest", "localhost", "3306", "testdb", "root", "secret", 1
        );

        when(clientAppRepository.save(any(ClientApp.class))).thenReturn(clientApp);

        // When
        ClientAppResponseDTO result = clientAppService.registerClientApp(dto);

        // Then
        assertEquals("ClientTest", result.name());
        verify(clientAppRepository).save(any(ClientApp.class));
    }

    @Test
    void shouldReturnClientAppsForGivenUserId() {
        // Given
        int userId = 1;

        ClientApp app1 = new ClientApp();
        app1.setId(1L);
        app1.setName("BlogApp");
        app1.setDbHost("localhost");
        app1.setDbPort("3306");
        app1.setDbName("blogdb");
        app1.setDbUsername("admin");
        app1.setDbPassword("secret");
        app1.setUserId((userId));

        List<ClientApp> mockApps = List.of(app1);

        when(clientAppRepository.findAllByUserId((userId))).thenReturn(mockApps);

        // When
        List<ClientAppResponseDTO> result = clientAppService.getAllClientApps(userId);

        // Then
        assertEquals(1, result.size());
        ClientAppResponseDTO dto = result.getFirst();
        assertEquals("BlogApp", dto.name());
        assertEquals("localhost", dto.dbHost());
        assertEquals("3306", dto.dbPort());
        assertEquals("blogdb", dto.dbName());
        assertEquals("admin", dto.dbUsername());
        assertEquals(userId, dto.userId());
    }
}
