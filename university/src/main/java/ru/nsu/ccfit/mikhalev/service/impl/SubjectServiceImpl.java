package ru.nsu.ccfit.mikhalev.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.ccfit.mikhalev.dao.entity.Subject;
import ru.nsu.ccfit.mikhalev.dao.repository.SubjectRepository;
import ru.nsu.ccfit.mikhalev.service.SubjectService;

@Slf4j
@AllArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Transactional
    public Subject findOrCreateSubject(String subject) {
        return this.subjectRepository.findByTitle (subject)
            .orElse(Subject.builder()
                            .title(subject)
                            .build());
    }
}
