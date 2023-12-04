package ru.nsu.ccfit.mikhalev.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.mikhalev.dao.entity.Schedule;
import ru.nsu.ccfit.mikhalev.dao.repository.*;
import ru.nsu.ccfit.mikhalev.dto.ScheduleDto;
import ru.nsu.ccfit.mikhalev.dto.TeacherDto;
import ru.nsu.ccfit.mikhalev.dto.TeacherScheduleDto;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;
import ru.nsu.ccfit.mikhalev.exception.UserNotFoundException;
import ru.nsu.ccfit.mikhalev.mapper.ScheduleMapper;
import ru.nsu.ccfit.mikhalev.service.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleMapper scheduleMapper;

    private final GroupRepository groupRepository;

    @Qualifier("teacherServiceImpl")
    private final TeacherService teacherService;

    @Qualifier("subjectServiceImpl")
    private final SubjectService subjectService;

    @Transactional
    @Override
    public void save(ScheduleDto scheduleDto) throws UserNotFoundException {
        log.info("save new schedule {}", scheduleDto.subject());

//        Schedule schedule1 = scheduleRepository.findByGroupsAndLessonDelivery(scheduleDto.groups(),
//                                                                             scheduleDto.lessonDelivery())
//                                              .orElse(scheduleMapper.mapToEntity(scheduleDto, groupRepository));

        Schedule schedule = scheduleMapper.mapToEntity(scheduleDto, groupRepository);
        schedule.setSubject(this.subjectService
                                .findOrCreateSubject(scheduleDto.subject()));

        schedule.setGroups(groupRepository.findBySetNumber(scheduleDto.groups()));
        schedule.setTeacher(this.teacherService.findTeacherDelivery(scheduleDto));
        scheduleRepository.save(schedule);
    }

    @Override
    public List<TeacherScheduleDto> findScheduleByTeacher(TeacherDto teacherDto) {
        return scheduleRepository.findAllScheduleByTeacher(teacherDto.firstName(),
                                                           teacherDto.middleName(),
                                                           teacherDto.lastName());
    }
}