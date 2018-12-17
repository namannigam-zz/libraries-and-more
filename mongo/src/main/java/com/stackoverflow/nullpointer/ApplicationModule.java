package com.stackoverflow.nullpointer;

import com.google.inject.AbstractModule;
import com.stackoverflow.nullpointer.config.ApplicationConfig;
import io.dropwizard.setup.Environment;

public class ApplicationModule extends AbstractModule {

    private final ApplicationConfig applicationConfig;
    private final Environment environment;

    public ApplicationModule(ApplicationConfig applicationConfig, Environment environment) {
        this.applicationConfig = applicationConfig;
        this.environment = environment;
    }

    @Override
    protected void configure() {
//        bind(MongoConfig.class).toInstance(applicationConfig.getEnergyMongoConfig());
    }
}