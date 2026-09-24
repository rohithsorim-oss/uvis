package com.sorim.uvis.service.impl;

import com.sorim.uvis.generated.api.v1.model.ApiVersionResponse;
import com.sorim.uvis.service.EnvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EnvServiceImpl implements EnvService {

    private final String ciEnvironment;
    private final String ciBuildNumber;
    private final String ciVersion;
    private final String serviceName;

    @Autowired
    public EnvServiceImpl(@Value("${ci.environment}") String ciEnvironment, @Value("${ci.build-number}") String ciBuildNumber, @Value("${ci.version}") String ciVersion, @Value("${ci.service-name}") String serviceName) {
        this.ciEnvironment = ciEnvironment;
        this.ciBuildNumber = ciBuildNumber;
        this.ciVersion = ciVersion;
        this.serviceName = serviceName;
    }

    @Override
    public ApiVersionResponse getEnvDetails() {
        ApiVersionResponse apiVersionResponse = new ApiVersionResponse();
        apiVersionResponse.setFullString(serviceName + " v" + ciVersion + "." + ciBuildNumber + "-" + ciEnvironment);
        apiVersionResponse.setServiceName(serviceName);
        apiVersionResponse.setEnvironmentName(ciEnvironment);
        apiVersionResponse.setBuildNumber(ciBuildNumber);
        apiVersionResponse.setVersion(ciVersion);
        return apiVersionResponse;
    }
}
