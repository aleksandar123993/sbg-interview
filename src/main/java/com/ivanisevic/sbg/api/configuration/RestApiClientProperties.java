package com.ivanisevic.sbg.api.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static retrofit2.converter.jackson.JacksonConverterFactory.create;

@Getter
@Setter
public class RestApiClientProperties {

    private String baseUrl;

    public Retrofit restApiBuilderUsing(ObjectMapper objectMapper) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(create(objectMapper))
                .build();
    }
}
