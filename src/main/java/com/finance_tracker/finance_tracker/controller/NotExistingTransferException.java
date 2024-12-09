package com.finance_tracker.finance_tracker.controller;

public class NotExistingTransferException extends RuntimeException {
    public NotExistingTransferException(String message) {
        super(message);
    }
}
