package com.aiteam.gatekeeper.dtos;

public record ClientAppDTO(
        String name,
        String dbHost,
        String dbPort,
        String dbName,
        String dbUsername,
        String dbPassword
) {}