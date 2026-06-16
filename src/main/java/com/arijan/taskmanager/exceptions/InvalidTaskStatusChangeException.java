package com.arijan.taskmanager.exceptions;

public class InvalidTaskStatusChangeException extends RuntimeException {
    public InvalidTaskStatusChangeException(String message) {
        super(message);
    }
}
