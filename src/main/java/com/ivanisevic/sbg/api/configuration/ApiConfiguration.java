package com.ivanisevic.sbg.api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ivanisevic.sbg.api.service.CGCApiRemote;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

    @Bean
    @ConfigurationProperties("api.client")
    public RestApiClientProperties cgcApiClientProperties() {
        return new RestApiClientProperties();
    }

    @Bean
    public CGCApiRemote cgcApiService(ObjectMapper mapper) {
        return cgcApiClientProperties()
                .restApiBuilderUsing(mapper)
                .create(CGCApiRemote.class);
    }
}
