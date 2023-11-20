package ru.nsu.ccfit.mikhalev.service;

import ru.nsu.ccfit.mikhalev.dto.ScheduleDto;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;

public interface ScheduleService {
    void save(ScheduleDto scheduleDto) throws UserExistsException;
}