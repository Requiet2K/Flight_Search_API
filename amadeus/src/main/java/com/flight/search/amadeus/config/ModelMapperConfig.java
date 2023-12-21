package com.flight.search.amadeus.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper getModelMapper(){
        ModelMapper theModelMapper = new ModelMapper();
        theModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return theModelMapper;
    }
}
