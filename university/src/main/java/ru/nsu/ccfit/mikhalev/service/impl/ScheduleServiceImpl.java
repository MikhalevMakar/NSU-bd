package ru.nsu.ccfit.mikhalev.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.mikhalev.dao.entity.Schedule;
import ru.nsu.ccfit.mikhalev.dao.repository.GroupRepository;
import ru.nsu.ccfit.mikhalev.dao.repository.ScheduleRepository;
import ru.nsu.ccfit.mikhalev.dto.ScheduleDto;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;
import ru.nsu.ccfit.mikhalev.mapper.ScheduleMapper;
import ru.nsu.ccfit.mikhalev.service.*;

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
    public void save(ScheduleDto scheduleDto) throws UserExistsException {
        log.info("save new schedule {}", scheduleDto.subject());

      this.teacherService.findTeacher(scheduleDto);
      Schedule schedule = scheduleRepository.findByGroupsAndLessonDelivery(scheduleDto.groups(),
                                                                           scheduleDto.lessonDelivery())
                                            .orElse(scheduleMapper.mapToEntity(scheduleDto, groupRepository));

      schedule.setSubject(this.subjectService
                               .findOrCreateSubject(scheduleDto.subject()));
      scheduleRepository.save(schedule);
    }
}