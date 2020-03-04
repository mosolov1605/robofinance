package ru.mosolov.robofinance.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {

        return builder -> builder
                .visibility(FIELD, ANY)
                .featuresToEnable(
                        // Ignore unknown enum values (isFront support)
                        DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL,
                        // Case insensitive mapping to enum (isFront support)
                        MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)
                .featuresToDisable(
                        // Disable adjust dates to time zone.
                        DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
    }
}
