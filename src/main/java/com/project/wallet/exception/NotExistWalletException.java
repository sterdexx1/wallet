package com.project.wallet.exception;

public class NotExistWalletException extends RuntimeException{
    public NotExistWalletException(String message) {
        super(message);
    }
}
