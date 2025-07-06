package com.aiteam.gatekeeper.controllers;

import com.aiteam.gatekeeper.dtos.requests.ClientAppRequestDTO;
import com.aiteam.gatekeeper.dtos.responses.ApiResponse;
import com.aiteam.gatekeeper.dtos.responses.ClientAppResponseDTO;
import com.aiteam.gatekeeper.services.IClientAppService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/clientapp", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class ClientAppController {
    private final IClientAppService clientAppService;

    @PostMapping("/test-connection")
    public ResponseEntity<ApiResponse<Boolean>> testConnection(@RequestBody ClientAppRequestDTO clientAppRequestDTO) {
        boolean result = clientAppService.testConnection(clientAppRequestDTO);
        ApiResponse<Boolean> response = new ApiResponse<>(HttpStatus.OK.value(), "Testing database connection", result);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<ClientAppResponseDTO>> register(@RequestBody ClientAppRequestDTO dto) {
        ClientAppResponseDTO app = clientAppService.registerClientApp(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse<>(HttpStatus.CREATED.value(), "App registered successfully", app)
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<List<ClientAppResponseDTO>>> getAllApps(@PathVariable int userId) {
        List<ClientAppResponseDTO> apps = clientAppService.getAllClientApps(userId);
        if(apps.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse<>(HttpStatus.NOT_FOUND.value(), "No apps found for userId " + userId, apps)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse<>(HttpStatus.OK.value(), "Apps retrieved successfully", apps)
        );
    }
}
