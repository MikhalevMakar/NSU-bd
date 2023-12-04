package ru.nsu.ccfit.mikhalev.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
import ru.nsu.ccfit.mikhalev.dao.entity.DegreeEducation;
import ru.nsu.ccfit.mikhalev.dao.repository.DegreeRepository;
import ru.nsu.ccfit.mikhalev.service.DegreeService;

@Slf4j
@AllArgsConstructor
@Service
public class DegreeServiceImpl implements DegreeService {

    private final DegreeRepository degreeRepository;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public DegreeEducation findOrCreateDegree(String degree) {
        return degreeRepository.findByDegree(degree)
                               .orElse(DegreeEducation.builder()
                                                      .degree(degree)
                                                      .build());
    }
}
