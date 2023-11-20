package ru.nsu.ccfit.mikhalev.dto;

import java.time.Instant;
import java.util.Set;

public record ScheduleDto(Set<Long> groups,
                          String subject,
                          String firstName,
                          String middleName,
                          String lastName,
                          Instant lessonDelivery) {}