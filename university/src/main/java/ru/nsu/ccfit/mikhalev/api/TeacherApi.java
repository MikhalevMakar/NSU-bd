package ru.nsu.ccfit.mikhalev.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.mikhalev.dto.TeacherDto;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;

import static ru.nsu.ccfit.mikhalev.configuration.PathApi.*;

@RequestMapping(value = PATH_UNIVERSITY)
public interface TeacherApi {
    @GetMapping(PART_PATH_TEACHER)
    String showTeacherForm(Model model, TeacherDto teacher);

    @PostMapping(PART_PATH_SAVE_TEACHER)
    String saveTeacher(@ModelAttribute(PART_PATH_TEACHER) TeacherDto teacher) throws UserExistsException;
}
