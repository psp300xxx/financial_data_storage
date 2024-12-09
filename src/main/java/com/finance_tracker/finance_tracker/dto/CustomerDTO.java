package com.finance_tracker.finance_tracker.dto;

public class CustomerDTO {

    private long id;
    private String username;

    public CustomerDTO(long id, String username) {
        this.id = id;
        this.username = username;
    }


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
