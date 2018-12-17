package com.stackoverflow.nullpointer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.stackoverflow.nullpointer.config.ApplicationConfig;
import com.stackoverflow.nullpointer.filter.RequestFilter;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class MongoTransaction extends Application<ApplicationConfig> {

    /**
     * <p> Application entry point for treasury. </p>
     *
     * @param args args provided to the application
     * @throws Exception for any generic exception thrown by the application
     */
    public static void main(String[] args) throws Exception {
        new MongoTransaction().run(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(Bootstrap<ApplicationConfig> gameZoneConfigBootstrap) {
        gameZoneConfigBootstrap.addBundle(new SwaggerBundle<ApplicationConfig>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(ApplicationConfig applicationConfig) {
                return applicationConfig.getSwaggerBundleConfiguration();
            }
        });

    }

    /**
     * {@inheritDoc}
     */
    public void run(ApplicationConfig configuration, Environment environment) {
        // object mapper configuration
        environment.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // inject configuration
        Injector injector = createInjector(configuration, environment);

        // register filters and health checks
        environment.healthChecks().register("HealthCheck", new ApplicationHealthCheck());
        environment.servlets().addFilter("Request-Filter", injector.getInstance(RequestFilter.class))
                .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");

        // register resources
        environment.jersey().register(injector.getInstance(TransactionalResource.class));
    }

    /**
     * <p> Create injector for a custom module and configuration. </p>
     *
     * @param config      configuration meant for the Energy service
     * @param environment dropwizard environment
     * @return injector instance created using Guice
     */
    private static Injector createInjector(ApplicationConfig config, Environment environment) {
        return Guice.createInjector(new ApplicationModule(config, environment));
    }
}