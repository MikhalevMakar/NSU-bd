package ru.nsu.ccfit.mikhalev.context;

import ru.nsu.ccfit.mikhalev.exception.UtilityClassException;

public final class ValidationEntityContext {

    private ValidationEntityContext() throws UtilityClassException {
        throw new UtilityClassException();
    }

    public static final int MAX_SIZE_FIRST_NAME = 50;

    public static final int MAX_SIZE_MIDDLE_NAME = 50;

    public static final int MAX_SIZE_LAST_NAME = 50;

    public static final int SIZE_NUMBER_PHONE = 11;
    
    public static final int SIZE_VALID_GROUP = 5;

    public static final int MAX_SIZE_DEGREE = 20;

    public static final int MAX_SIZE_SUBJECT = 25;

    public static final String VALID_NAME = "^[a-zA-Zа-яА-Я]+$";
}