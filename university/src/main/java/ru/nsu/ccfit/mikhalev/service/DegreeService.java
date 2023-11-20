package ru.nsu.ccfit.mikhalev.service;

import ru.nsu.ccfit.mikhalev.dao.entity.DegreeEducation;

public interface DegreeService {

    DegreeEducation findOrCreateDegree(String degree);
}
