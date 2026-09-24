package com.sorim.uvis.controller;

import com.sorim.uvis.generated.api.v1.model.ApiVersionResponse;
import com.sorim.uvis.service.EnvService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VersionControllerTest {

    @Mock
    private EnvService envService;

    @InjectMocks
    private VersionController versionController;

    @Test
    void shouldReturnVersionDetails() {

        ApiVersionResponse versionResponse = new ApiVersionResponse()
                .serviceName("uvis")
                .environmentName("dev")
                .buildNumber("100")
                .version("1.0.0")
                .fullString("uvis v1.0.0.100-dev");

        when(envService.getEnvDetails()).thenReturn(versionResponse);

        ResponseEntity<ApiVersionResponse> response =
                versionController.getVersion();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        assertEquals("uvis", response.getBody().getServiceName());
        assertEquals("dev", response.getBody().getEnvironmentName());
        assertEquals("100", response.getBody().getBuildNumber());
        assertEquals("1.0.0", response.getBody().getVersion());

        verify(envService, times(1)).getEnvDetails();
    }

    @Test
    void shouldReturnOkForHeadVersion() {

        ResponseEntity<Void> response =
                versionController.getHeadVersion();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());

        verifyNoInteractions(envService);
    }
}