package ru.nsu.ccfit.mikhalev.service;

import ru.nsu.ccfit.mikhalev.dao.entity.Subject;

public interface SubjectService {

    Subject findOrCreateSubject(String subject);
}
