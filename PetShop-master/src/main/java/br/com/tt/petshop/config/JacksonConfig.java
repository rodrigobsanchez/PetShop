package br.com.tt.petshop.config;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper getObjectMapper(){
        ObjectMapper om = new ObjectMapper();
        om.configure(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS, true);
        return om;
    }
}
