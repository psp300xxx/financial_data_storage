package com.finance_tracker.finance_tracker.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.finance_tracker.finance_tracker.controller.custom_deserializers.MoneyTransferDeserializer;
import com.finance_tracker.finance_tracker.model.MoneyTransfer;
import com.finance_tracker.finance_tracker.repository.ICustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    private final ICustomerRepository customerRepository;

    public JacksonConfig(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // Registra il deserializer personalizzato
        SimpleModule module = new SimpleModule();
        module.addDeserializer(MoneyTransfer.class, new MoneyTransferDeserializer(customerRepository));
        mapper.registerModule(module);
        mapper.registerModule( new JavaTimeModule());
        return mapper;
    }
}
