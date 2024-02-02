package com.project.springproject.model;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class is responsible for configuring the ModelMapper bean in the Spring
 * application context.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Creates and configures the ModelMapper bean.
     *
     * @return The configured ModelMapper bean.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}