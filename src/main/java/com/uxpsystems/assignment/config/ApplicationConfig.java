package com.uxpsystems.assignment.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages="com.uxpsystems.assignment")
public class ApplicationConfig {

}
