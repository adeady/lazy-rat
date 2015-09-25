package com.iai

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SpringConfig {


    //jackson object mapper
    @Bean ObjectMapper objectMapper() {
        new ObjectMapper()
    }
}
