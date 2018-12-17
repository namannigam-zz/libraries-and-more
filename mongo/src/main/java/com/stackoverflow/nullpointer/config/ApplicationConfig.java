package com.stackoverflow.nullpointer.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
public class ApplicationConfig extends Configuration {

    @NotNull @Valid
    private MongoConfig mongoConfig;

    @NotNull @Valid
    private StartupConfig startupConfig;

    private String healthCheckName = "MongoHealth";

    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    @NotNull
    public MongoConfig getMongoConfig() {
        return mongoConfig;
    }

    @NotNull
    public StartupConfig getStartupConfig() {
        return startupConfig;
    }

    public String getHealthCheckName() {
        return healthCheckName;
    }

    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }
}