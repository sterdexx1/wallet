package com.project.wallet.exception;

import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WalletGlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<InfoWalletException> handlerException(NotExistWalletException e){

        InfoWalletException data = new InfoWalletException();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<InfoWalletException> handlerException(NotEnoughMoneyException e){

        InfoWalletException data = new InfoWalletException();
        data.setInfo(e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.PAYMENT_REQUIRED);
    }

    @ExceptionHandler
    public ResponseEntity<InfoWalletException> handlerException(HttpMessageNotReadableException e){
        InfoWalletException data = new InfoWalletException();
        data.setInfo("Invalid JSON file");
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<InfoWalletException> handlerException(UnexpectedTypeException e){
        InfoWalletException data = new InfoWalletException();
        data.setInfo("Invalid JSON file");
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<InfoWalletException> handlerException(Exception e){

        InfoWalletException data = new InfoWalletException();
        data.setInfo(e.getClass().getName() + ": " + e.getMessage());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }


}
