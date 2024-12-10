package com.finance_tracker.finance_tracker.controller;

public class AlreadyExistingTransferException extends RuntimeException {

    public AlreadyExistingTransferException(String message) {
        super(message);
    }
}
