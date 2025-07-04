package com.aiteam.gatekeeper.services;

import com.aiteam.gatekeeper.dtos.requests.ClientAppRequestDTO;
import com.aiteam.gatekeeper.dtos.responses.ClientAppResponseDTO;

import java.util.List;

public interface IClientAppService {
    boolean testConnection(ClientAppRequestDTO clientAppRequestDTO);
    ClientAppResponseDTO registerClientApp(ClientAppRequestDTO clientAppRequestDTO);
    List<ClientAppResponseDTO> getAllClientApps(int id);
}
