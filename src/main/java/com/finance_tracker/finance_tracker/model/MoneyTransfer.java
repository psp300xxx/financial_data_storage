package com.finance_tracker.finance_tracker.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Entity
@Table(name = "money_transfer")
public class MoneyTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonProperty("Id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonProperty("CustomerId")
    private Customer customer;

    @JsonProperty("Amount")
    @Column(name = "amount", nullable = false)
    private double amount;

    @JsonProperty("Currency")
    @Column(name = "currency", nullable = false)
    private String currencyCode;

    @JsonProperty("ExecutionTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "execution_time", nullable = false)
    private ZonedDateTime executionTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public ZonedDateTime getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(ZonedDateTime executionTIme) {
        this.executionTime = executionTIme;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
