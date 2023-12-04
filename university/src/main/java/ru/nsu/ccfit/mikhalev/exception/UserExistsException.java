package ru.nsu.ccfit.mikhalev.exception;

public class UserExistsException extends IllegalStateException {

    public UserExistsException() {
        super("user with username is exists");
    }
}
