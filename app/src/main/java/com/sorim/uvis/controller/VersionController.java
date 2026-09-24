package com.sorim.uvis.controller;

import com.sorim.uvis.generated.api.v1.controller.VersionApi;
import com.sorim.uvis.generated.api.v1.model.ApiVersionResponse;
import com.sorim.uvis.service.EnvService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VersionController implements VersionApi {

    private final EnvService envService;

    @Override
    public ResponseEntity<ApiVersionResponse> getVersion() {
        return ResponseEntity.ok(envService.getEnvDetails());
    }
    @Override
    public ResponseEntity<Void> getHeadVersion() {
        return ResponseEntity.ok().build();
    }
}