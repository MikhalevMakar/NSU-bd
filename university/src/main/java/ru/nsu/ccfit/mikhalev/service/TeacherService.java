package ru.nsu.ccfit.mikhalev.service;

import ru.nsu.ccfit.mikhalev.dao.entity.Teacher;
import ru.nsu.ccfit.mikhalev.dto.ScheduleDto;
import ru.nsu.ccfit.mikhalev.dto.TeacherDto;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;
import ru.nsu.ccfit.mikhalev.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    Teacher findTeacherDelivery(ScheduleDto scheduleDto) throws UserNotFoundException;

    void save(TeacherDto teacherDto) throws UserExistsException;

    List<TeacherDto> listTeachers();
}
