package ru.nsu.ccfit.mikhalev.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.mikhalev.dao.entity.Teacher;
import ru.nsu.ccfit.mikhalev.dao.repository.TeacherRepository;
import ru.nsu.ccfit.mikhalev.dto.ScheduleDto;
import ru.nsu.ccfit.mikhalev.dto.TeacherDto;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;
import ru.nsu.ccfit.mikhalev.mapper.TeacherMapper;
import ru.nsu.ccfit.mikhalev.service.*;

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

    @Transactional(readOnly = true)
    public Teacher findTeacher(ScheduleDto scheduleDto) throws UserExistsException {
        return teacherRepository.findTeacherByLessonDelivery(scheduleDto.lastName(), scheduleDto.middleName(),
                                                             scheduleDto.firstName(), scheduleDto.lessonDelivery())
                                 .orElseThrow(UserExistsException::new);
    }

    private void checkIfTeacherExists(TeacherDto teacherDto) throws UserExistsException {
        if (teacherRepository.existsTeacherByNames(teacherDto.firstName(),
                                                   teacherDto.middleName(),
                                                   teacherDto.lastName()))
            throw new UserExistsException();
    }
}
