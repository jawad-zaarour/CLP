package dev.profitisle.exceptions.handlers;

@FunctionalInterface
public interface ExceptionHandler {
    void handle(Exception e);
}
