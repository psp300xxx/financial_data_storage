package com.finance_tracker.finance_tracker.controller.custom_deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.finance_tracker.finance_tracker.model.Customer;
import com.finance_tracker.finance_tracker.model.MoneyTransfer;
import com.finance_tracker.finance_tracker.repository.ICustomerRepository;
import com.finance_tracker.finance_tracker.repository.IMoneyTransferRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MoneyTransferDeserializer extends JsonDeserializer<MoneyTransfer> {

    private final ICustomerRepository customerRepository;

    public MoneyTransferDeserializer(ICustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public MoneyTransfer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);

        MoneyTransfer moneyTransfer = new MoneyTransfer();
        Long customerId = node.get("CustomerId").asLong();
        Customer customer = customerRepository.findById(customerId);

        moneyTransfer.setCustomer(customer);
        moneyTransfer.setAmount(node.get("Amount").asDouble());
        moneyTransfer.setCurrencyCode(node.get("Currency").asText());
        moneyTransfer.setExecutionTime(
                LocalDateTime.parse(
                        node.get("ExecutionTime").asText(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                ).atZone(ZoneId.systemDefault()) // Specifica il fuso orario
        );

        return moneyTransfer;
    }
}
