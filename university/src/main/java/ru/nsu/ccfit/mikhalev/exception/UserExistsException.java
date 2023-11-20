package ru.nsu.ccfit.mikhalev.exception;

public class UserExistsException extends Exception {

    public UserExistsException() {
        super("user with username is exists");
    }
}
