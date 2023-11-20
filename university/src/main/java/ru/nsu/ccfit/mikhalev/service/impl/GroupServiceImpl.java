package ru.nsu.ccfit.mikhalev.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nsu.ccfit.mikhalev.dao.entity.Group;
import ru.nsu.ccfit.mikhalev.dao.repository.GroupRepository;
import ru.nsu.ccfit.mikhalev.exception.NoSuchGroupException;
import ru.nsu.ccfit.mikhalev.service.GroupService;

@AllArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Override
    public Group findGroupByNumber(Long number) throws NoSuchGroupException {
        return groupRepository.findByNumber(number)
                              .orElseThrow(() -> new NoSuchGroupException(number));
    }
}
