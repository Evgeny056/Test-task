package com.javacode.testtask.exception;

import lombok.Getter;

import java.util.UUID;
@Getter
public class WalletNotFoundException extends RuntimeException {

    private UUID walletId;

    public WalletNotFoundException(String message, UUID walletId) {
        super (message);
        this.walletId = walletId;
    }
}
