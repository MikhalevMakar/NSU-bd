package ru.nsu.ccfit.mikhalev.api;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.nsu.ccfit.mikhalev.dto.ScheduleDto;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;

import static ru.nsu.ccfit.mikhalev.configuration.PathApi.*;

@RequestMapping(value = PATH_UNIVERSITY)
public interface ScheduleApi {
    @GetMapping(PART_PATH_SCHEDULE)
    String showForm(Model model, ScheduleDto scheduleDto);

    @PostMapping(PART_PATH_SAVE_SCHEDULE)
    String save(@ModelAttribute(PART_PATH_SAVE_SCHEDULE) ScheduleDto scheduleDto) throws UserExistsException;
}
