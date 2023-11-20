package ru.nsu.ccfit.mikhalev.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.mikhalev.dto.*;
import ru.nsu.ccfit.mikhalev.exception.NoSuchGroupException;

import static ru.nsu.ccfit.mikhalev.configuration.PathApi.*;

@RequestMapping(value = PATH_UNIVERSITY)
public interface StudentApi {

    @GetMapping(PART_PATH_STUDENT)
    String showStudentForm(Model model, StudentDto studentDto);

    @PostMapping(PART_PATH_SAVE_STUDENT)
    String saveStudent(@ModelAttribute(PART_PATH_STUDENT) StudentDto studentDto) throws Exception;
}