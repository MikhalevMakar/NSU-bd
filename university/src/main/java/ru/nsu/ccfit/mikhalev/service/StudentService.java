package ru.nsu.ccfit.mikhalev.service;

import ru.nsu.ccfit.mikhalev.dto.StudentDto;
import ru.nsu.ccfit.mikhalev.exception.NoSuchGroupException;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;

public interface StudentService {

    void save(StudentDto studentDto) throws NoSuchGroupException, UserExistsException;
}
