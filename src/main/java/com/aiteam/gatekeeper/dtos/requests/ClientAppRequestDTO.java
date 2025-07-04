package com.aiteam.gatekeeper.dtos.requests;

public record ClientAppRequestDTO(
        String name,
        String dbHost,
        String dbPort,
        String dbName,
        String dbUsername,
        String dbPassword,
        int userId
) {}