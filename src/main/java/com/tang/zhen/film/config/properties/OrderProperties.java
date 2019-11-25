package com.tang.zhen.film.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = OrderProperties.ORDER_PREFIX)
@Data
public class OrderProperties {

    public static final String ORDER_PREFIX ="order";

    private String filePathPre;
}
