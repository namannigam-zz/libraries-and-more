package com.stackoverflow.nullpointer.config;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class StartupConfig {
    @NotNull
    private Integer schedulerInitialDelay;

    @NotNull
    private Integer schedulerRefreshPeriod;

    @NotNull
    private String namespace;
}