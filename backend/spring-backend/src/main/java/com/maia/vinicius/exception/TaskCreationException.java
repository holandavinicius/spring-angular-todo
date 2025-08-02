package com.maia.vinicius.exception;

public class TaskCreationException extends RuntimeException{
    public TaskCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
