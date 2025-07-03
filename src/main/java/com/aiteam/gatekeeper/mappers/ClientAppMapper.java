package com.aiteam.gatekeeper.mappers;

import com.aiteam.gatekeeper.dtos.ClientAppDTO;
import com.aiteam.gatekeeper.entities.ClientApp;

public class ClientAppMapper {
    public static ClientAppDTO mapToClientAppDTO(ClientApp clientApp) {
        return new ClientAppDTO(
                clientApp.getName(),
                clientApp.getDbHost(),
                clientApp.getDbPort(),
                clientApp.getDbName(),
                clientApp.getDbUsername(),
                clientApp.getDbPassword()
        );
    }

    public static ClientApp mapToClientApp(ClientAppDTO clientAppDTO, Long id) {
        return new ClientApp(
                id,
                clientAppDTO.name(),
                clientAppDTO.dbHost(),
                clientAppDTO.dbPort(),
                clientAppDTO.dbName(),
                clientAppDTO.dbUsername(),
                clientAppDTO.dbPassword()
        );
    }
}
