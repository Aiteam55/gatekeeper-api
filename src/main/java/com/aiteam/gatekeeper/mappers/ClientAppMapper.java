package com.aiteam.gatekeeper.mappers;

import com.aiteam.gatekeeper.dtos.requests.ClientAppRequestDTO;
import com.aiteam.gatekeeper.dtos.responses.ClientAppResponseDTO;
import com.aiteam.gatekeeper.entities.ClientApp;

public class ClientAppMapper {
    public static ClientAppResponseDTO mapToClientAppDTO(ClientApp clientApp) {
        return new ClientAppResponseDTO(
                clientApp.getName(),
                clientApp.getDbHost(),
                clientApp.getDbPort(),
                clientApp.getDbName(),
                clientApp.getDbUsername()
        );
    }

    public static ClientApp mapToClientApp(ClientAppRequestDTO clientAppRequestDTO, Long id) {
        return new ClientApp(
                id,
                clientAppRequestDTO.name(),
                clientAppRequestDTO.dbHost(),
                clientAppRequestDTO.dbPort(),
                clientAppRequestDTO.dbName(),
                clientAppRequestDTO.dbUsername(),
                clientAppRequestDTO.dbPassword()
        );
    }
}
