package com.finance_tracker.finance_tracker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.finance_tracker.finance_tracker.dto.CustomerDTO;
import com.finance_tracker.finance_tracker.dto.DTOObject;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer")
public class Customer implements DTOObject<CustomerDTO> {

    @Id
    @JsonProperty("Id")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("Username")
    @Column(name = "username")
    private String username;

    @JsonProperty("Password")
    @Column(name = "password")
    private String password;

    @JsonProperty("Salt")
    @Column(name = "salt", nullable = false, updatable = false)
    private String salt;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<MoneyTransfer> transfers;

    @PrePersist
    public void generateSalt(){
        if(salt == null){
            salt = UUID.randomUUID().toString();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSalt() {
        if(salt == null){
            generateSalt();
        }
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public List<MoneyTransfer> getTransfers() {
        return transfers;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public CustomerDTO convert() {
        CustomerDTO dto = new CustomerDTO(this.id, this.username);
        return dto;
    }
}
