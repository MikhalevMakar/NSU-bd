package ru.nsu.ccfit.mikhalev.service;

import ru.nsu.ccfit.mikhalev.dao.entity.Teacher;
import ru.nsu.ccfit.mikhalev.dto.ScheduleDto;
import ru.nsu.ccfit.mikhalev.dto.TeacherDto;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;

public interface TeacherService {

    Teacher findTeacher(ScheduleDto scheduleDto) throws UserExistsException;

    void save(TeacherDto teacherDto) throws UserExistsException;
}
