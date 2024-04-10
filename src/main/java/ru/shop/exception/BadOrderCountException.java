package ru.shop.exception;

public class BadOrderCountException extends Exception {
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param count the detail message. The detail message is saved for
     *              later retrieval by the {@link #getMessage()} method.
     */
    public BadOrderCountException(Long count) {
        super("Order count must be positive: " + count);
    }
}
