package com.tang.zhen.film.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = FilmProperties.FILM_PREFIX)
@Data
public class FilmProperties {

    public static final String FILM_PREFIX ="film";

    private String imgPre;
}
