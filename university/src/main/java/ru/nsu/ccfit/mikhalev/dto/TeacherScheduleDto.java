package ru.nsu.ccfit.mikhalev.dto;

import java.time.Instant;

public record TeacherScheduleDto(Instant lessonDelivery,
                                 String subject,
                                 Long numberGroup) {}
