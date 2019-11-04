package com.tang.zhen.film.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = RestProperties.REST_PREFIX)
@Data
public class RestProperties {

    public static final String REST_PREFIX ="rest";

    private boolean authOpen;
}
