package com.stackoverflow.nullpointer.config;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Configuration for MongoDB
 * @author naman.nigam
 */
@Getter
public class MongoConfig {

    @NotNull
    @Size
    private List<String> servers;

    @NotNull
    @Min(10)
    @Max(100)
    private Integer maxConnections;

    @NotNull
    private Integer socketTimeout;

    @NotNull
    private Integer connectionTimeout;
}