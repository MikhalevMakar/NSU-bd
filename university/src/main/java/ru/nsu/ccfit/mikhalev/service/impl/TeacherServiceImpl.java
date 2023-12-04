package ru.nsu.ccfit.mikhalev.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.mikhalev.dao.entity.Teacher;
import ru.nsu.ccfit.mikhalev.dao.repository.TeacherRepository;
import ru.nsu.ccfit.mikhalev.dto.*;
import ru.nsu.ccfit.mikhalev.exception.*;
import ru.nsu.ccfit.mikhalev.mapper.TeacherMapper;
import ru.nsu.ccfit.mikhalev.service.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    @Qualifier("degreeServiceImpl")
    private final DegreeService degreeService;

    @Transactional
    @Override
    public void save(TeacherDto teacherDto) throws UserExistsException {
        log.info("save new teacher");
        this.checkIfTeacherExists(teacherDto);
        Teacher teacher = teacherMapper.mapToEntity(teacherDto);

        teacher.getDegreeEducation()
               .add(degreeService.findOrCreateDegree(teacherDto.degree()));
        teacherRepository.save(teacher);
    }


    @Override
    @Transactional(readOnly = true)
    public Teacher findTeacherDelivery(ScheduleDto scheduleDto) throws UserNotFoundException {

        if(teacherRepository.exitLessonDelivery(scheduleDto.lastName(),
                                                scheduleDto.middleName(),
                                                scheduleDto.firstName(),
                                                scheduleDto.lessonDelivery()))
            throw new UserNotFoundException();

        return teacherRepository.findByFullName(scheduleDto.lastName(),
                                                scheduleDto.middleName(),
                                                scheduleDto.firstName())
                                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<TeacherDto> listTeachers() {
        return teacherRepository.findAllTeacher().stream()
                                                 .map(teacherMapper::mapToDto)
                                                 .toList();
    }

    private void checkIfTeacherExists(TeacherDto teacherDto) throws UserExistsException {
        if (teacherRepository.existsTeacherByNames(teacherDto.firstName(),
                                                   teacherDto.middleName(),
                                                   teacherDto.lastName()))
            throw new UserExistsException();
    }
}
