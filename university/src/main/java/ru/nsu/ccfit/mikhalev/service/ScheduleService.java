package ru.nsu.ccfit.mikhalev.service;

import ru.nsu.ccfit.mikhalev.dto.*;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;

import java.util.List;

public interface ScheduleService {

    void save(ScheduleDto scheduleDto) throws UserExistsException;

    List<TeacherScheduleDto> findScheduleByTeacher(TeacherDto teacherDto);
}