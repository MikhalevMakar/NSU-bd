package ru.nsu.ccfit.mikhalev.dto;

import java.math.BigDecimal;

public record StudentDto (String firstName,
                          String middleName,
                          String lastName,
                          BigDecimal averageScore,
                          String numberPhone,

                          Long numberGroup) {}