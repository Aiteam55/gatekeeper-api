package com.aiteam.gatekeeper.services;

import com.aiteam.gatekeeper.dtos.ClientAppDTO;

public interface IClientAppService {
    boolean testConnection(ClientAppDTO clientAppDTO);
}
