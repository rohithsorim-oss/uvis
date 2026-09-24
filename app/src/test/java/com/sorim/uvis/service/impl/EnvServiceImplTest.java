package com.sorim.uvis.service.impl;

import com.sorim.uvis.generated.api.v1.model.ApiVersionResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnvServiceImplTest {

    @Test
    @DisplayName("Should return environment details successfully")
    void testGetEnvDetails() {

        // Arrange
        String environment = "DEV";
        String buildNumber = "123";
        String version = "1.0.0";
        String serviceName = "UVIS";

        EnvServiceImpl service = new EnvServiceImpl(
                environment,
                buildNumber,
                version,
                serviceName
        );

        // Act
        ApiVersionResponse response = service.getEnvDetails();

        // Assert
        assertNotNull(response);
        assertEquals(serviceName, response.getServiceName());
        assertEquals(environment, response.getEnvironmentName());
        assertEquals(buildNumber, response.getBuildNumber());
        assertEquals(version, response.getVersion());

        assertEquals(
                "UVIS v1.0.0.123-DEV",
                response.getFullString()
        );
    }
}
