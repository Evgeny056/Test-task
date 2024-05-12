package com.javacode.testtask.handler;

import com.javacode.testtask.exception.ErrorChangeBalance;
import com.javacode.testtask.exception.ErrorMessage;
import com.javacode.testtask.exception.InsufficientFundsException;
import com.javacode.testtask.exception.WalletNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleWalletNotFoundException(WalletNotFoundException exception) {
        log.info("Wallet not found with id: {}", exception.getWalletId() );
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorMessage handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception) {
        log.warn("A request was made for an unusable method: {}", exception.getMessage());
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        log.warn("An unsupported operation type was requested: {}", exception.getMessage());
        return new ErrorMessage(Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage handleHttpMessageNotReadable(HttpMessageNotReadableException exception) {
        log.warn("Not Readable message: {}", exception.getMessage());
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(InsufficientFundsException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleInsufficientFunds(InsufficientFundsException exception) {
        log.info("Insufficient funds");
        return new ErrorMessage(exception.getMessage());
    }

    @ExceptionHandler(ErrorChangeBalance.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ErrorMessage handleErrorChangeBalance(ErrorChangeBalance exception) {
        log.error("Error change balance: {}", exception.getMessage());
        return new ErrorMessage(exception.getMessage());
    }

}
