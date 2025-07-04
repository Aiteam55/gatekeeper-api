package com.aiteam.gatekeeper.dtos.responses;

public record ClientAppResponseDTO(
        String name,
        String dbHost,
        String dbPort,
        String dbName,
        String dbUsername,
        int userId
) {}
