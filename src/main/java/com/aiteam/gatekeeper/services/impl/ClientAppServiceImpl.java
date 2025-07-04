package com.aiteam.gatekeeper.services.impl;

import com.aiteam.gatekeeper.dtos.requests.ClientAppRequestDTO;
import com.aiteam.gatekeeper.dtos.responses.ClientAppResponseDTO;
import com.aiteam.gatekeeper.entities.ClientApp;
import com.aiteam.gatekeeper.mappers.ClientAppMapper;
import com.aiteam.gatekeeper.repositories.ClientAppRepository;
import com.aiteam.gatekeeper.services.IClientAppService;
import com.aiteam.gatekeeper.utils.DatabaseUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientAppServiceImpl implements IClientAppService {
    private final ClientAppRepository clientAppRepository;
    @Override
    public boolean testConnection(ClientAppRequestDTO clientAppRequestDTO) {
        String jdbcUrl = DatabaseUtil.buildJdbcUrl(clientAppRequestDTO.dbHost(), Integer.parseInt(clientAppRequestDTO.dbPort()), clientAppRequestDTO.dbName());

        try {
            Connection conn = DriverManager.getConnection(jdbcUrl, clientAppRequestDTO.dbUsername(), clientAppRequestDTO.dbPassword());
            return conn.isValid(2); // timeout = 2 seconds
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public ClientAppResponseDTO registerClientApp(ClientAppRequestDTO clientAppRequestDTO) {
        ClientApp newClientApp = ClientAppMapper.mapToClientApp(clientAppRequestDTO, null);
        ClientApp savedClientApp = clientAppRepository.save(newClientApp);
        return ClientAppMapper.mapToClientAppDTO(savedClientApp);
    }

    @Override
    public List<ClientAppResponseDTO> getAllClientApps(int userId) {
        List<ClientApp> clientApps = clientAppRepository.findAllByUserId(userId);
        return clientApps.stream()
                .map(ClientAppMapper::mapToClientAppDTO)
                .toList();
    }
}
