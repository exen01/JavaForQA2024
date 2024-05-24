package ru.shop.exception;

public class BadProductReturnCountException extends RuntimeException {
    public BadProductReturnCountException(String message) {
        super(message);
    }
}
