package ru.nsu.ccfit.mikhalev.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.nsu.ccfit.mikhalev.api.ScheduleApi;
import ru.nsu.ccfit.mikhalev.dto.*;
import ru.nsu.ccfit.mikhalev.exception.UserExistsException;
import ru.nsu.ccfit.mikhalev.service.ScheduleService;

import static ru.nsu.ccfit.mikhalev.configuration.ApiHtml.*;

@Slf4j
@AllArgsConstructor
@Controller
public class ScheduleController implements ScheduleApi {

    @Qualifier("scheduleServiceImpl")
    private final ScheduleService scheduleService;

    @Override
    public String showForm(Model model, ScheduleDto scheduleDto) {
        log.info("show student form");
        model.addAttribute("schedule", scheduleDto);
        return HTML_SCHEDULE;
    }

    @Override
    public String save(ScheduleDto scheduleDto) throws UserExistsException {
        this.scheduleService.save(scheduleDto);
        return HTML_REDIRECT_SCHEDULE;
    }

    @Override
    public String teacherSchedule(Model model, @ModelAttribute TeacherDto teacher) {
        log.info("teacher schedule {}", teacher.lastName());
        model.addAttribute("teacherScheduleDtoList",
                            this.scheduleService.findScheduleByTeacher(teacher));
        log.info("get page");
        return "teacher-schedule";
    }
}
