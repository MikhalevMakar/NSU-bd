package ru.nsu.ccfit.mikhalev.exception;

public class NoSuchGroupException extends Exception {

    public NoSuchGroupException(Long number) {
        super("api-university.exception.no-such-group.description" + number);
    }
}
